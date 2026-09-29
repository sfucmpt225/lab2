package io.github.sfucmpt225.lab2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * All file input/output for the program.
 */
public final class FileManager {

    // static fields
    public static final String WORKING_DIR = System.getProperty("user.dir");
    private static int linesWritten = 0;

    private FileManager() {
        // utility class, no instances
    }

    public static int getLinesWritten() {
        return linesWritten;
    }

    /**
     * Overwrites the file with the given lines.
     * The checked IOException is declared with "throws" so the caller must handle it.
     */
    public static void writeLines(String fileName, List<String> lines){
        if (fileName == null || fileName.trim().length() == 0) {
            throw new IllegalArgumentException("file name must not be empty");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
                linesWritten++;
            }
        } // writer is closed automatically here, even if an exception is thrown
    }

    /**
     * Appends one line to the end of the file (FileWriter in append mode).
     * Handles IOException itself instead of passing it on.
     */
    public static void appendLine(String fileName, String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
            writer.newLine();
            linesWritten++;
        } catch (IOException e) {
            System.out.println("Could not append to " + fileName + ": " + e.getMessage());
        }
    }

    /**
     * Reads every line of the file.
     * FileNotFoundException (a subclass of IOException) is thrown by new FileReader(...)
     * when the file does not exist.
     */
    public static List<String> readLines(String fileName) throws FileNotFoundException, IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    /**
     * Reads only the first line, closing the reader the "old" way with try/finally
     * instead of try-with-resources, for comparison.
     */
    public static String readFirstLine(String fileName) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            return reader.readLine();
        } 
            if (reader != null) {
                reader.close();
            }
            System.out.println("  (finally: reader closed manually)");
    }
}
