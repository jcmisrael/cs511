// Exercise.java

package edu.stevens.jisrael.cs511.assignment2.containers;

import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import edu.stevens.jisrael.cs511.assignment2.Gym;

/**
 * Class Exercise
 * Implementation for the Exercise container
 * An exercise consists of an ApparatusType, a number of weights of varying types
 * and a duration the exercise must be done for
 * @author  Jake Israel
 * @version 1.0.0
 * @since   2016-09-30
 */
public class Exercise {
    public ApparatusType at;
    public Map<WeightPlateSize, Integer> weights;
    public int duration;

    /**
     * Exercise constructor
     * @param _at A valid ApparatusType
     * @param _weights A valid map of weight plate sizes and quanities
     * @param _duration a non-negative integer representing a duration in milliseconds
     */
    public Exercise(ApparatusType _at, Map<WeightPlateSize, Integer> _weights, int _duration){
        this.at = _at;
        this.weights = _weights;
        this.duration = _duration;
    }

    private static final Random RANDOM = new Random();
    private static final int EXERCISE_DURATION_RANGE = 2;
    /**
     * Return a random Exercise
     * Optimized by keeping static final versions of the enum contents
     * Sourced from stackoverflow
     * @return A randomly selected Exercise
     * @see https://stackoverflow.com/questions/1972392/java-pick-a-random-value-from-an-enum
     */
    public static Exercise generateRandom(){
        // Generate a random apparatus
        ApparatusType at = ApparatusType.randomApparatus();
        // Generate a random exercise duration
        int duration = RANDOM.nextInt(Exercise.EXERCISE_DURATION_RANGE) + 1; // Minimum of 1
        // Create a hashmap to contain weights of WeightPlateSize and random quantities
        HashMap numWeights = new HashMap();
        int count = 0;
        int smWeights;
        int mdWeights;
        int lgWeights;
        // Generate random weights until there is a nonzero amount
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
        Exercise e = new Exercise(at, numWeights, duration);
        return e;
    }

    /**
     * toString implementation
     * @return A String representation of an exercise
     */
    @Override
    public String toString(){
        String s = "";
        s += this.at.name() + " with";
        for(WeightPlateSize w: WeightPlateSize.VALUES)
            s += " " + weights.get(w) + " " + w.name();
        s += " for " + duration + " seconds";
        return s;
    }
}
