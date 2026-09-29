package io.github.sfucmpt225.lab2;

import java.util.Arrays;

/**
 * One student's name and list of scores.
 */
public class StudentRecord {

    // static field: shared by all instances, counts how many records have been created
    private static int recordCount = 0;

    private final String name;
    private final int[] scores;

    public StudentRecord(String name, int[] scores) {
        if (scores == null) {
            throw new NullPointerException("scores must not be null");
        }
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("name must not be empty");
        }
        this.name = name.trim();
        this.scores = Arrays.copyOf(scores);
        recordCount++;
    }

    public String getName() {
        return name;
    }

    public int[] getScores() {
        return Arrays.copyOf(scores, scores.length);
    }

    public static int getRecordCount() {
        return recordCount;
    }

    /** Converts this record back into a CSV line, e.g. "Alice,90,85,77". */
    public String toCsvLine() {
        StringBuilder sb = new StringBuilder(name);
        for (int s : scores) {
            sb.append(",").append(s);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return name + " " + Arrays.toString(scores);
    }
}

