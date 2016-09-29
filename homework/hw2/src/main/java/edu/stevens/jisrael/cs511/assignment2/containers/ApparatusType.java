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
    LEGPRESSMACHINE,
    BARBELL,
    HACKSQUATMACHINE,
    LEGEXTENSIONMACHINE,
    LEGCURLMACHINE,
    LATPULLDOWNMACHINE,
    PECDECKMACHINE,
    CABLECROSSOVERMACHINE;

    private static final List<ApparatusType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static ApparatusType randomApparatus() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
