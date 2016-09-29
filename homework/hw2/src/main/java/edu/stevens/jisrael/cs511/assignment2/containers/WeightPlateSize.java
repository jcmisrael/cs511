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
    SMALL_3KG(0), MEDIUM_5KG(1), LARGE_10KG(2);


    public int index;
    WeightPlateSize(int _index){
        this.index = _index;
    }

    public static final List<WeightPlateSize> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    public static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static WeightPlateSize randomApparatus() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
