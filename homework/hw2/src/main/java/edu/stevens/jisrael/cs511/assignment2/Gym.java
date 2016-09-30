// Gym.java

package edu.stevens.jisrael.cs511.assignment2;

import edu.stevens.jisrael.cs511.assignment2.containers.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Implementation of the gym class
 * Has a set of semaphores representing weights, apparatuses, and
 * an executor service
 * @author: Jake Israel
 * @version: 1.0.0
 * @since 2016-09-30
 */
public class Gym implements Runnable {
    // Final variables
    private static final int GYM_SIZE = 30;
    private static final int GYM_REGISTERED_SIZE = 10000;
    private static final int NUM_APPARATUSES = 5;
    public static final int[] NUM_EACH_WEIGHT = {40, 30, 20};
    private static final int MUTEX = 1;

    // Instance variables
    private HashMap<WeightPlateSize, Integer> noOfWeights;
    private ExecutorService executor;
    public Semaphore[] apparatuses;
    private Semaphore[] weights;
    private Semaphore canGrabWeights;
    private Set<Integer> ids;

    /**
     * Grabs a given amount of weights, blocking until it has acquired
     * every weight semaphore it needs
     * @param numWeights A map containing WeightPlateSize and quantity pairs
     */
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

    /**
     * Release weight semaphores, not requiring canGrabWeights semaphore
     * @param numWeights A map containing WeightPlateSize and quantity pairs
     */
    public void releaseWeights(Map<WeightPlateSize, Integer> numWeights){
        int i;
        for(i = 0; i < numWeights.get(WeightPlateSize.SMALL_3KG); ++i)
            this.weights[WeightPlateSize.SMALL_3KG.index].release();
        for(i = 0; i < numWeights.get(WeightPlateSize.MEDIUM_5KG); ++i)
            this.weights[WeightPlateSize.MEDIUM_5KG.index].release();
        for(i = 0; i < numWeights.get(WeightPlateSize.LARGE_10KG); ++i)
            this.weights[WeightPlateSize.LARGE_10KG.index].release();
    }

    /**
     * Gym Constructor
     * Initializes all variables based off given constants
     */
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

        this.ids = new HashSet<Integer>();
    }

    /**
     * Overrides Threads run method
     * Starts creating random clients and adds them to the gym
     * to simulate a gym
     */
    @Override
    public void run(){
        int id;
        Random r = new Random();
        int i = 0;
        System.out.println("The Gym is now open!");
        while(i < Gym.GYM_REGISTERED_SIZE){
            do {
                id = r.nextInt(Integer.MAX_VALUE);
            } while (!ids.add(id));
            Client c = Client.generateRandom(id, this);
            executor.execute(c);
            ++i;
        }
        System.out.println("The Gym is now closed!");
        executor.shutdown();
    }
}
