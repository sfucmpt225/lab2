package io.github.sfucmpt225.lab2;

/**
 * Simple statistics on int arrays. Each method can raise a different unchecked exception.
 */
public final class StatsCalculator {

    private StatsCalculator() {
        // utility class, no instances
    }

    /**
     * Highest value, found with Math.max().
     * @throws NullPointerException     if values is null
     * @throws IllegalArgumentException if values is empty
     */
    public static int highest(int[] values) {
        if (values == null) {
            throw new NullPointerException("values must not be null");
        }
        if (values.length == 0) {
            throw new IllegalArgumentException("cannot find the max of an empty array");
        }
        int best = values[0];
        for (int v : values) {
            best = Math.max(best, v);
        }
        return best;
    }

    /**
     * Integer average.
     * @throws ArithmeticException if values is empty (integer division by zero)
     */
    public static int average(int[] values) {
        int sum = 0;
        for (int v : values) {
            sum += v;
        }
        return sum / values; // "/ by zero" when length is 0
    }

    /**
     * Score at a given position.
     * @throws ArrayIndexOutOfBoundsException if index is out of range
     */
    public static int scoreAt(int[] values, int index) {
        return values[index];
    }
}
