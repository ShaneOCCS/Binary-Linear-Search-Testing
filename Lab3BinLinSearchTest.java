import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CET - CS Academic Level 3
 * Description: This class serves as a test application for the BinaryLinearSearch class.
 * It provides a menu-driven interface that allows users to initialize an array of random characters
 * and perform various search operations, such as iterative and recursive binary and linear searches.
 * The program also measures and displays the time taken to perform these searches.
 * <p>
 * Student Name: Shane O'Connell
 * Student Number: 041144343
 * Section #: 311
 * Course: CST8130 - Data Structures
 *
 * @author/Professor: James Mwangi PhD.
 */

public class Lab3BinLinSearchTest {

    /**
     * The Main method that serves as the entry point of the program.
     *
     * @param args ...
     * @throws Exception to handle potential exceptions.
     */
    public static void main(String[] args) throws Exception {
        //Scanner creation
        Scanner sc = new Scanner(System.in);
        //Input for menu.
        int decision = 0;
        //Creates a new instance of BinaryLinearSearch.
        BinaryLinearSearch bls = new BinaryLinearSearch();

        //Runs the menu continuously. If a number other than 1-4 is entered, it will throw a default case.
        while(decision != 4){
            //Calls mainMenu into the loop.
            mainMenu();
            //Checks the user's input to see if it is an integer.
            try {
                decision = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("***** Input Mismatch Exception *****.");
                System.out.println("      Please enter an integer        ");
                sc.next();
                continue;
            }

            //Beginning of switch.
            switch(decision){
                case 1: //Option to populate an array of 32 random characters.
                    try {
                        //Generates data.
                        bls.generateRandomChars();

                        char[] unsorted = bls.getCharArray();
                        System.out.println("Unsorted Array: " + Arrays.toString(unsorted));

                        char[] sorted = unsorted.clone();
                        Arrays.sort(sorted);
                        System.out.println("Sorted Array: " + Arrays.toString(sorted));
                    } catch (Exception e) {
                        //Catch any runtime exception and inform the user
                        System.out.println("Error generating the character array. Please try option 1 again.");
                    }
                    break;

                case 2: //Option to perform a recursive binary and linear search.
                    try {
                        if (bls.getCharArray().length == 0) {
                            System.out.println("Please populate the array before searching (select option 1).");
                            break;
                        }

                        //Prompt user to enter a keyValue.
                        System.out.print("Please enter a character to search: ");
                        char key = sc.next().charAt(0);
                        System.out.println();

                        //Sorts array.
                        char[] sortedArray = bls.getCharArray().clone();
                        Arrays.sort(sortedArray);

                        //RECURSIVE BINARY SEARCH:

                        System.out.println("Remaining elements during Recursive Binary Search: ");

                        long startNano = System.nanoTime();
                        long startMilli = System.currentTimeMillis();

                        //Performs recursiveBinarySearch on the character array.
                        int recursiveResult = bls.recursiveBinarySearch(sortedArray, 0, sortedArray.length - 1, key);

                        startNano = System.nanoTime() - startNano;
                        startMilli = System.currentTimeMillis() - startMilli;

                        //Always executes.
                        if (recursiveResult >= 0) {
                            System.out.println(
                                    key + " was found at index position " + recursiveResult + " : Recursive Binary Search"
                            );
                        } else {
                            System.out.println(
                                    key + " was not found : Recursive Binary Search"
                            );
                        }

                        //Print the time taken in nanoseconds and milliseconds for recursiveBinarySearch.
                        System.out.println("Time taken in nanoseconds: " + startNano);
                        System.out.println("Time taken in milliseconds: " + startMilli);
                        System.out.println();

                        //RECURSIVE LINEAR SEARCH:

                        System.out.println("Remaining elements during Recursive Linear Search: ");

                        startNano = System.nanoTime();
                        startMilli = System.currentTimeMillis();

                        int recursiveLinearResult = bls.recursiveLinearSearch(sortedArray, 0, key);

                        startNano = System.nanoTime() - startNano;
                        startMilli = System.currentTimeMillis() - startMilli;

                        if (recursiveLinearResult >= 0) {
                            System.out.println(
                                    key + " was found at index position " + recursiveLinearResult + " : Recursive Linear Search"
                            );
                        } else {
                            System.out.println(
                                    key + " was not found : Recursive Linear Search"
                            );
                        }
                        //Print the time taken in nanoseconds and milliseconds for recursiveLinearSearch.
                        System.out.println("Time taken in nanoseconds: " + startNano);
                        System.out.println("Time taken in milliseconds: " + startMilli);

                        //Handles user input error, placed at bottom of case so key is defined in the scope still.
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a single character to search.");
                        sc.next();
                        continue;
                        //Handles potential array initialization error.
                    } catch (NullPointerException e) {
                        System.out.println("There was an error with the character array. Please try option 1 again.");
                    }
                    break;

                case 3: //Option to perform an iterative binary and linear search.
                    try {
                        //Checks if the user populated the array first.
                        if (bls.getCharArray().length == 0) {
                            System.out.println("Please populate the array before searching (select option 1).");
                            break;
                        }
                        //Prompt the user to enter a key value to search in the array.
                        System.out.print("Please enter a character to search: ");
                        char key = sc.next().charAt(0);

                        //Calls setter for key.
                        bls.setSearchKey(key);

                        //ITERATIVE BINARY SEARCH:

                        long startNano  = System.nanoTime();
                        long startMilli = System.currentTimeMillis();

                        //Performs iterativeBinary search on the character array for the stored key.
                        int iterativeResult = bls.iterativeBinarySearch(bls.getCharArray());

                        startNano  = System.nanoTime() - startNano;
                        startMilli = System.currentTimeMillis() - startMilli;

                        //Always executes.
                        if (iterativeResult >= 0) {
                            System.out.println(
                                    key + " was found at index position " + iterativeResult + " : Iterative Binary Search"
                            );
                            //Executes if iterativeBinary returns -1.
                        } else {
                            System.out.println(
                                    key + " was not found : Iterative Binary Search"
                            );
                        }

                        //Print the time taken in nanoseconds and milliseconds for iterativeBinary search.
                        System.out.println("Time taken in nanoseconds: " + startNano);
                        System.out.println("Time taken in milliseconds: " + startMilli);
                        System.out.println();

                        //LINEAR BINARY SEARCH:

                        startNano  = System.nanoTime();
                        startMilli = System.currentTimeMillis();
                        //Performs iterative IterativeLinear search on the character array for the stored key.
                        int linearResult = bls.iterativeLinearSearch(bls.getCharArray());

                        startNano  = System.nanoTime() - startNano;
                        startMilli = System.currentTimeMillis() - startMilli;

                        //Always executes.
                        if (linearResult >= 0) {
                            System.out.println(
                                    key + " was found at index position " + linearResult + " : Iterative Linear Search"
                            );
                            //Executes if iterativeLinear returns -1.
                        } else {
                            System.out.println(
                                    key + " was not found : Iterative Linear Search"
                            );
                        }

                        //Print the time taken in nanoseconds and milliseconds for Linear Binary search.
                        System.out.println("Time taken in nanoseconds: " + startNano);
                        System.out.println("Time taken in milliseconds: " + startMilli);

                        //Handles user input error, placed at bottom of case so key is defined in the scope still.
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a single character to search.");
                        sc.next();
                        continue;
                        //Handles potential array initialization error.
                    } catch (NullPointerException e) {
                        System.out.println("There was an error with the character array. Please try option 1 again.");
                    }
                    break;

                case 4: //Option to exit the user from the program.
                    System.out.println("This program was created by: Shane O'Connell (#041144343)");
                    System.out.println();
                    System.out.println("Exiting program..." );
                    sc.close();
                    break;

                default: //Handles incorrect number input for menu.
                    System.out.println("Please enter an integer from: (1-4).");
                    break;
            }
        }
    }

    /**
     * Displays the main menu to the user.
     */
    public static void mainMenu(){
        System.out.println();
        System.out.println("------------------------------------------------------------------");
        System.out.println("Select your option in the menu: ");
        System.out.println("1. Initialize and populate an array of 32 random characters.");
        System.out.println("2. Perform recursive binary and linear search.");
        System.out.println("3. Perform iterative binary and linear search.");
        System.out.println("4. Exit.");
        System.out.println("------------------------------------------------------------------");
        System.out.print(">");
    }
}