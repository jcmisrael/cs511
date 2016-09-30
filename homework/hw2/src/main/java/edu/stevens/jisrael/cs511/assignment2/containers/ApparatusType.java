// ApparatusType.java

package edu.stevens.jisrael.cs511.assignment2.containers;

import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import java.util.Random;

/**
 * ApparatusType container implementation
 * Declares an enum containing the various types of apparatuses
 * Also provides a random function for generating a random apparatus
 * @author  Jake Israel
 * @version 1.0.0
 * @since   2016-09-30
 */
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

    /**
     * ApparatusType constructor
     * Applied when the enums are created
     * @param _index An index for the enum to correspond to
     */
    ApparatusType(int _index){
        this.index = _index;
    }

    public static final List<ApparatusType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    public static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * Return a random apparatus
     * Optimized by keeping static final versions of the enum contents
     * Sourced from stackoverflow
     * @return A randomly selected ApparatusType
     * @see https://stackoverflow.com/questions/1972392/java-pick-a-random-value-from-an-enum
     */
    public static ApparatusType randomApparatus() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
