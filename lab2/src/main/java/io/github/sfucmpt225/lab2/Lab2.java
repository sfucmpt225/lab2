package io.github.sfucmpt225.lab2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Entry point. Writes a CSV of student grades, appends to it, reads it back,
 * and parses it.
 */
public class Lab2 {

    private static final String FILE_NAME = "grades.csv";
    private static final String MISSING_FILE = "does_not_exist.csv";

    public static void main(String[] args) {
        System.out.println("Working directory: " + FileManager.WORKING_DIR);
        System.out.println();

        // 1. Write the initial file (checked IOException must be caught)
        System.out.println("== Writing " + FILE_NAME + " ==");
        List<String> initialLines = Arrays.asList(
                "Alice, 90, 85, 77",
                "Bob,72,88,95",
                "Carol, 100, abc, 60",   // bad number -> NumberFormatException
                "Dave",                  // too few fields -> IllegalArgumentException
                " , 50, 60",             // empty name -> IllegalArgumentException
                "Grace, 101, 90"         // out of range -> IllegalArgumentException
        );
        try {
            FileManager.writeLines(FILE_NAME, initialLines);
            System.out.println("Wrote " + initialLines.size() + " lines.");
        } catch (IOException e) {
            System.out.println("Write failed: " + e.getMessage());
            return;
        }

        // 2. Append more lines
        System.out.println();
        System.out.println("== Appending to " + FILE_NAME + " ==");
        FileManager.appendLine(FILE_NAME, "Eve, 88, 91, 79");
        FileManager.appendLine(FILE_NAME, "Frank,  65 ,70 , 99 ");
        System.out.println("Total lines written so far: " + FileManager.getLinesWritten());

        // 3. Read and parse (try / catch / finally around a method that "throws")
        System.out.println();
        System.out.println("== Reading and parsing ==");
        List<StudentRecord> records = new ArrayList<>();
        try {
            records = loadRecords(FILE_NAME);
        } catch (IOException e) {
            System.out.println("Read failed: " + e.getMessage());
        } finally {
            System.out.println("Parsing finished. Valid records: " + records.size()
                    + " (StudentRecord objects created: " + StudentRecord.getRecordCount() + ")");
        }

        // 4. Report
        System.out.println();
        System.out.println("== Report ==");
        for (StudentRecord r : records) {
            int[] scores = r.getScores();
            System.out.println(r.getName()
                    + " scores=" + Arrays.toString(scores)
                    + " highest=" + StatsCalculator.highest(scores)
                    + " average=" + StatsCalculator.average(scores));
        }

        // 5. Unchecked exceptions
        System.out.println();
        System.out.println("== Unchecked exceptions ==");
        demonstrateUncheckedExceptions();

        // 6. Checked exceptions: FileNotFoundException vs IOException
        System.out.println();
        System.out.println("== Checked exceptions ==");
        try {
            FileManager.readLines(MISSING_FILE);
        } catch (FileNotFoundException e) {   // more specific, so it must come first
            System.out.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

        // 7. Manual try/finally resource handling
        System.out.println();
        System.out.println("== try/finally without try-with-resources ==");
        try {
            String first = FileManager.readFirstLine(FILE_NAME);
            System.out.println("First line: \"" + first + "\" (length " + first.length() + ")");
        } catch (IOException e) {
            System.out.println("Could not read first line: " + e.getMessage());
        }
    }

    /**
     * Reads the file and parses each line, skipping bad ones.
     * Does not catch IOException itself; it passes it to the caller via "throws".
     */
    private static List<StudentRecord> loadRecords(String fileName) throws IOException {
        List<StudentRecord> records = new ArrayList<>();
        List<String> lines = FileManager.readLines(fileName);

        int lineNumber = 0;
        for (String line : lines) {
            lineNumber++;
            try {
                StudentRecord record = RecordParser.parse(line);
                records.add(record);
                System.out.println("  line " + lineNumber + " OK: " + record);
            } catch (NumberFormatException e) {
                // NumberFormatException extends IllegalArgumentException, so it must be caught first
                System.out.println("  line " + lineNumber + " skipped (bad number): " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("  line " + lineNumber + " skipped (invalid): " + e.getMessage());
            }
        }
        return records;
    }

    private static void demonstrateUncheckedExceptions() {
        int[] sample = {70, 80, 90};
        int[] empty = new int[0];

        // ArithmeticException
        try {
            StatsCalculator.average(empty);
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException: " + e.getMessage());
        }

        // ArrayIndexOutOfBoundsException
        try {
            StatsCalculator.scoreAt(sample, 10);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException: " + e.getMessage()
                    + " (array is " + Arrays.toString(sample) + ")");
        }

        // NullPointerException
        try {
            StatsCalculator.highest(null);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e.getMessage());
        }

        // NullPointerException from calling a method on a null reference
        String missing = null;
        try {
            System.out.println(missing.length());
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: called length() on a null String");
        }

        // NumberFormatException directly from Integer.parseInt
        try {
            Integer.parseInt("12x");
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: " + e.getMessage());
        }

        // IllegalArgumentException thrown by our own code, plus a finally block
        try {
            StatsCalculator.highest(empty);
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException: " + e.getMessage());
        } finally {
            System.out.println("(finally: unchecked exception demo complete)");
        }
    }
}
