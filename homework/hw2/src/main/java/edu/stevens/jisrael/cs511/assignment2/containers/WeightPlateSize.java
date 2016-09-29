// WeightPlate.java
// Declaration of weight plates

package edu.stevens.jisrael.cs511.assignment2.containers;

import java.util.List;
import java.util.Random;
import java.util.Collections;
import java.util.Arrays;

// Random code from
// https://stackoverflow.com/questions/1972392/java-pick-a-random-value-from-an-enum
public enum WeightPlateSize {
    SMALL_3KG, MEDIUM_5KG, LARGE_10KG;

    private static final List<WeightPlateSize> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static WeightPlateSize randomApparatus() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
