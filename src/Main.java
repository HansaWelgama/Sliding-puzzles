/*
 * Task 1,2,3,4
 * Name: H.H.Welgama
 * IIT Number: 20221109
 * UOW Number: w1998723
 * I confirm that I understand what plagiarism / collusion /
 * contract cheating is and have read and understood
 * the section on Assessment Offences in the Essential Information for Students.
 * The work that I have submitted is entirely my own.
 * Any work from other authors is duly referenced and acknowledged.
 */

import java.io.File;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        selectFolderAndFile(); // Call the method to select folder and file
    }

    static void selectFolderAndFile() {
        Scanner scanner = new Scanner(System.in); // Create a scanner object to read user input

        // Display welcome message and options
        System.out.println();
        System.out.println("********************************************************");
        System.out.println("**********************Welcome***************************");
        System.out.println();
        System.out.println("1. 'maze' folder");
        System.out.println("2. 'puzzle' folder");
        System.out.print("Please enter the name of the folder:");
        String folderName = scanner.nextLine(); // Read the folder name provided by the user

        // Check the selected folder
        if (folderName.equalsIgnoreCase("maze")) {
            // Display file options for the 'maze' folder
            System.out.println();
            System.out.println("select file");
            System.out.println();
            System.out.println("maze10_1, maze10_2, maze10_3, maze10_4, maze10_5");
            System.out.println("maze15_1, maze15_2, maze15_3, maze15_4, maze15_5");
            System.out.println("maze20_1, maze20_2, maze20_3, maze20_4, maze20_5");
            System.out.println("maze25_1, maze25_2, maze25_3, maze25_4, maze25_5");
            System.out.println("maze30_1, maze30_2, maze30_3, maze30_4, maze30_5");
            System.out.println();
        } else if (folderName.equalsIgnoreCase("puzzle")) {
            // Display file options for the 'puzzle' folder
            System.out.println();
            System.out.println("select file");
            System.out.println();
            System.out.println("puzzle_20, puzzle_40, puzzle_80, puzzle_160");
            System.out.println("puzzle_320, puzzle_640, puzzle_1280, puzzle_2560");
            System.out.println();
        } else {
            // Invalid folder selection
            System.out.println("Invalid folder selection. Exiting program.");
            return;
        }

        // Create a File object for the selected folder
        File folder = new File(folderName);

        // Check if the folder exists and is a directory
        if (folder.exists() && folder.isDirectory()) {
            System.out.print("Enter the name of the file");
            String fileName = scanner.nextLine(); // Read the file name provided by the user
            String filePath = folderName + File.separator + fileName + ".txt"; // Construct the file path
            File file = new File(filePath); // Create a File object for the selected file

            // Check if the file exists and is a regular file
            if (file.exists() && file.isFile()) {
                System.out.println("Reading the file " + file.getName() + "...");
                System.out.println();
                SlidingPuzzle puzzle = new SlidingPuzzle(filePath, folderName); // Create a SlidingPuzzle object
                System.out.println();
                System.out.println("===========================================================");
                System.out.println();
                System.out.println("Solving the puzzle...");
                System.out.println();
                Timer timer = new Timer(); // Create a Timer object to measure execution time
                timer.start(); // Start the timer
                puzzle.breadthFirstSearch(); // Solve the puzzle using breadth-first search algorithm
                timer.stop(); // Stop the timer
                System.out.println("===========================================================");
                System.out.println(timer); // Print the execution time
                System.out.println("===========================================================");
            } else {
                // Specified file does not exist or is not a regular file
                System.out.println("The specified file does not exist or is not a regular file.");
            }
        } else {
            // Specified folder does not exist or is not a directory
            System.out.println("The specified folder does not exist or is not a directory.");
        }
    }
}
