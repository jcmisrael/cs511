// WeightPlate.java

package edu.stevens.jisrael.cs511.assignment2.containers;

import java.util.List;
import java.util.Random;
import java.util.Collections;
import java.util.Arrays;

// Random code from
// https://stackoverflow.com/questions/1972392/java-pick-a-random-value-from-an-enum
/**
 * Implementation for the WeightPlateSize container class
 * @author Jake Israel
 * @version 1.0.0
 * @since 2016-09-30
 */
public enum WeightPlateSize {
    SMALL_3KG(0), MEDIUM_5KG(1), LARGE_10KG(2);

    public int index;

    /**
     * WeightPlateSize constructor
     * Constructs the enums with a given index
     * @param _index An int representing an index that ties to the enum
     */
    WeightPlateSize(int _index){
        this.index = _index;
    }

    public static final List<WeightPlateSize> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    public static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * Return a random WeightPlateSize
     * Optimized by keeping static final versions of the enum contents
     * Sourced from stackoverflow
     * @return A randomly selected WeightPlateSize
     * @see https://stackoverflow.com/questions/1972392/java-pick-a-random-value-from-an-enum
     */
    public static WeightPlateSize randomApparatus() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
