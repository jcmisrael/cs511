// Apparatus.java
// Declaration for apparatus data type

package edu.stevens.jisrael.cs511.assignment2.containers;

import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import java.util.Random;

// Random code from
// https://stackoverflow.com/questions/1972392/java-pick-a-random-value-from-an-enum
public enum ApparatusType {
    LEGPRESSMACHINE(0),
    BARBELL(1),
    HACKSQUATMACHINE(2),
    LEGEXTENSIONMACHINE(3),
    LEGCURLMACHINE(4),
    LATPULLDOWNMACHINE(5),
    PECDECKMACHINE(6),
    CABLECROSSOVERMACHINE(7);

    public int index;
    ApparatusType(int _index){
        this.index = _index;
    }

    public static final List<ApparatusType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    public static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static ApparatusType randomApparatus() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
