// Exercise.java
// Exercise implementation

package edu.stevens.jisrael.cs511.assignment2.containers;

import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import edu.stevens.jisrael.cs511.assignment2.Gym;

public class Exercise {
    public ApparatusType at;
    public Map<WeightPlateSize, Integer> weights;
    public int duration;

    public Exercise(ApparatusType _at, Map<WeightPlateSize, Integer> _weights, int _duration){
        this.at = _at;
        this.weights = _weights;
        this.duration = _duration;
    }

    private static final Random RANDOM = new Random();

    public static Exercise generateRandom(){
        ApparatusType at = ApparatusType.randomApparatus();
        int duration = RANDOM.nextInt(1000);
        HashMap numWeights = new HashMap();
        int count = 0;
        int smWeights;
        int mdWeights;
        int lgWeights;
        while(count == 0){
            smWeights = RANDOM.nextInt(Gym.NUM_EACH_WEIGHT[WeightPlateSize.SMALL_3KG.index]);
            count += smWeights;
            numWeights.put(WeightPlateSize.SMALL_3KG, smWeights);

            mdWeights = RANDOM.nextInt(Gym.NUM_EACH_WEIGHT[WeightPlateSize.MEDIUM_5KG.index]);
            count += mdWeights;
            numWeights.put(WeightPlateSize.MEDIUM_5KG, mdWeights);

            lgWeights = RANDOM.nextInt(Gym.NUM_EACH_WEIGHT[WeightPlateSize.LARGE_10KG.index]);
            count += lgWeights;
            numWeights.put(WeightPlateSize.LARGE_10KG, lgWeights);
        }
        Exercise e = new Exercise(at, numWeights, 0);
        return e;
    }

    public String toString(){
        String s = "";
        s += this.at.name() + " with";
        for(WeightPlateSize w: WeightPlateSize.VALUES)
            s += " " + weights.get(w) + " " + w.name();
        s += " for " + duration + " seconds";
        return s;
    }
}
