import java.io.*;

/**
 * This class is in charge of creating a file,
 * where it writes all the errors occurred during runtime
 */

public class ErrorLogger {
    public void error(String message) {
        try {
            File errorFile = new File("errors.log");
            if (!errorFile.exists()) {
                errorFile.createNewFile();
            }
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(errorFile, true)));
            writer.write(message);
            writer.println();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: errorLogs file could not be opened");
        }
    }
}
