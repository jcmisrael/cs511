// Exercise.java
// Exercise implementation

package edu.stevens.jisrael.cs511.assignment2.containers;

import java.util.Map;
import java.util.Random;

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

    public static Exercise generateRandom(Map<WeightPlateSize, Integer> weights){
        ApparatusType at = ApparatusType.randomApparatus();
        int duration = RANDOM.nextInt(1000);
        Exercise e = new Exercise(at, weights, duration);
        return e;
    }
}
