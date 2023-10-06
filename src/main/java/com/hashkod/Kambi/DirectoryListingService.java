package com.hashkod.Kambi;


import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Actual business logic lives in this class.
 */
@Service
public class DirectoryListingService {

    /**
     * Executes external application, 'ls' and captures the output.
     * @param path The path of the directory whose contents need to be listed.
     * @return A list containing the files and subdirectories. Empty list if there
     * are no contents in the given directory. Null, if the given directory does
     * not exist.
     */
    public ArrayList<String> listContents(String path) {

        ArrayList<String> listing = new ArrayList<>();

        try {
            List<String> lsCommand = new ArrayList<>();
            lsCommand.add("ls");
            lsCommand.add(path);
            // Create a ProcessBuilder for the 'ls' lsCommand
            ProcessBuilder processBuilder = new ProcessBuilder(lsCommand);

            // Start the process
            Process process = processBuilder.start();

            // Get the input stream of the process (for reading the lsCommand's output)
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Read and print the output of the lsCommand
            String line;
            while ((line = reader.readLine()) != null) {
                listing.add(line);
            }

            // If result code is 1, it means directory does not exist
            return process.waitFor() == 0 ? listing : null;

        }
        catch (Exception e) {
            System.out.println("Exception while executing ls: " + e.getMessage());
            return null;
        }
    }

    /**
     * Wait for number of seconds before executing {@link DirectoryListingService#listContents} function.
     * @param path The path of directory.
     * @param delay Number of seconds to wait.
     * @return The value returned by {@link DirectoryListingService#listContents}
     */
    public ArrayList<String> blockingListContents(String path, long delay) {
        try {
            Thread.sleep(delay * 1000);
        } catch (InterruptedException e) {
            System.out.println("Server shutting down? " + e.getMessage());
        }
        return listContents(path);
    }

}
