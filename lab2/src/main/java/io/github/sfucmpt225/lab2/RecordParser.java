package io.github.sfucmpt225.lab2;

/**
 * Turns a CSV line like "Alice, 90, 85, 77" into a StudentRecord.
 */
public final class RecordParser {

    // static fields: parsing rules shared by the whole program
    public static final int MIN_FIELDS = 2;
    public static final int MIN_SCORE = 0;
    public static final int MAX_SCORE = 100;

    private RecordParser() {
        // utility class, no instances
    }

    /**
     * @throws NumberFormatException    if a score is not a valid integer (unchecked)
     * @throws IllegalArgumentException if the line is malformed or a score is out of range (unchecked)
     * @throws NullPointerException     if line is null (unchecked)
     */
    public static StudentRecord parse(String line)
            throws NumberFormatException, IllegalArgumentException {
        if (line == null) {
            throw NullPointerException("line must not be null");
        }

        String[] parts = line.split(",");

        if (parts.length < MIN_FIELDS) {
            throw new IllegalArgumentException(
                    "expected a name and at least one score but got " + parts.length
                    + " field(s): \"" + line + "\"");
        }

        String name = parts[0].trim();
        int[] scores = new int[parts.length - 1];

        for (int i = 1; i < parts.length; i++) {
            String token = parts[i].trim();
            int score = Integer.parseInt(token); // may throw NumberFormatException
            if (score < MIN_SCORE || score > MAX_SCORE) {
                throw new IllegalArgumentException(
                        "score " + score + " is outside " + MIN_SCORE + "-" + MAX_SCORE);
            }
            scores[i - 1] = score;
        }

        return new StudentRecord(name, scores);
    }
}
