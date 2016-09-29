// Gym.java
// Implementation for the Gym simulation

package edu.stevens.jisrael.cs511.assignment2;

import edu.stevens.jisrael.cs511.assignment2.containers.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.HashMap;
import java.util.Map;

public class Gym implements Runnable {
    private static final int GYM_SIZE = 30;
    private static final int GYM_REGISTERED_SIZE = 10000;
    private static final int NUM_APPARATUSES = 5;
    public static final int[] NUM_EACH_WEIGHT = {40, 30, 20};
    private static final int MUTEX = 1;

    private HashMap<WeightPlateSize, Integer> noOfWeights;
    private ExecutorService executor;
    public Semaphore[] apparatuses;
    private Semaphore[] weights;
    private Semaphore canGrabWeights;

    public void grabWeights(Map<WeightPlateSize, Integer> numWeights){
        int i;
        try{
            this.canGrabWeights.acquire();
            for(i = 0; i < numWeights.get(WeightPlateSize.SMALL_3KG); ++i)
                this.weights[WeightPlateSize.SMALL_3KG.index].acquire();
            for(i = 0; i < numWeights.get(WeightPlateSize.MEDIUM_5KG); ++i)
                this.weights[WeightPlateSize.MEDIUM_5KG.index].acquire();
            for(i = 0; i < numWeights.get(WeightPlateSize.LARGE_10KG); ++i)
                this.weights[WeightPlateSize.LARGE_10KG.index].acquire();
        } catch(InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        } finally {
            this.canGrabWeights.release();
        }
    }

    public void releaseWeights(Map<WeightPlateSize, Integer> numWeights){
        int i;
        for(i = 0; i < numWeights.get(WeightPlateSize.SMALL_3KG); ++i)
            this.weights[WeightPlateSize.SMALL_3KG.index].release();
        for(i = 0; i < numWeights.get(WeightPlateSize.MEDIUM_5KG); ++i)
            this.weights[WeightPlateSize.MEDIUM_5KG.index].release();
        for(i = 0; i < numWeights.get(WeightPlateSize.LARGE_10KG); ++i)
            this.weights[WeightPlateSize.LARGE_10KG.index].release();
    }

    public Gym(){
        int i;
        int numApparatusTypes = ApparatusType.SIZE;
        int numWeightTypes = WeightPlateSize.SIZE;

        this.executor = Executors.newFixedThreadPool(Gym.GYM_SIZE);
        this.apparatuses = new Semaphore[numApparatusTypes];
        for(ApparatusType a: ApparatusType.VALUES){
            this.apparatuses[a.index] = new Semaphore(Gym.NUM_APPARATUSES);
        }
        this.weights = new Semaphore[numWeightTypes];
        this.noOfWeights = new HashMap();
        for(WeightPlateSize w: WeightPlateSize.VALUES){
            noOfWeights.put(w, NUM_EACH_WEIGHT[w.index]);
            weights[w.index] = new Semaphore(Gym.NUM_EACH_WEIGHT[w.index]);
        }
        this.canGrabWeights = new Semaphore(1);
    }


    public void run(){
        int id = 0;
        while(id < Gym.GYM_REGISTERED_SIZE){
            Client c = Client.generateRandom(id, this);
            executor.execute(c);
            ++id;
        }
        executor.shutdown();
    }
}
