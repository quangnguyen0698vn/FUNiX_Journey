
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author quang
 */
public class SortApp {
    
    private static Algorithm algorithm = new Algorithm();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {        
        int choice = 0;
        
        do { 
            System.out.println("\nPlease choose one option to continue...\n");
            
            System.out.println("1. Input");
            System.out.println("2. Output");
            System.out.println("3. Bubble Sort");
            System.out.println("4. Selection Sort");
            System.out.println("5. Insertion Sort");
            System.out.println("6. Search > Value");
            System.out.println("7. Search = Value");
            System.out.println("8. Generate randomized input");
            System.out.println("9. Benchmarking the three sort algorithms above");

            System.out.println("0. Exit\n");
            System.out.print("Choice:");
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    handleInputArray();
                    break;
                case 2:
                    handleOutputArray();
                    break;
                case 3:
                    handleBubbleSort();
                    break;
                case 4:
                    handleSelectionSort();
                    break;
                case 5:
                    handleInsertionSort();
                    break;
                case 6:
                    handleSearchGreaterThanValue();
                    break;
                case 7:
                    handleSearchEqualTo();
                    break;
                case 8:
                    handleGenerateRandomizedInput();
                    break;
                case 9:
                    handleBenchmarking();
                    break;
                default:
                    break;
            }
        }
        while (choice != 0);
    }
    
    private static void handleInputArray() {        
        System.out.print("Please enter the number of elements: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        float[] arr = new float[n];
        for(int i = 0; i < n; i++) {
            System.out.print("arr[" + i + "]=");
            arr[i] = scanner.nextFloat();
            scanner.nextLine();
        }
        
        algorithm.writeFile("INPUT.TXT", arr);
    }
    
    private static void handleOutputArray() {
        float[] ret = algorithm.readFile("INPUT.TXT");
        System.out.println("Read from the INPUT.TXT file.");
        System.out.println("n = " + ret.length);
        System.out.println("A = " + Arrays.toString(ret));
    }
    
    private static void handleSearchGreaterThanValue() {
        System.out.println("Linear Search");
        System.out.print("Please input value:");
        float value = scanner.nextFloat();
        scanner.nextLine();
        algorithm.search(algorithm.readFile("INPUT.TXT"), value);
    }
    
    private static void handleSearchEqualTo() {
        System.out.println("Binary Search");
        System.out.print("Please input value:");
        float value = scanner.nextFloat();
        scanner.nextLine();
        algorithm.smallestIndexEqualValue(algorithm.readFile("INPUT.TXT"), value);
    }

    private static void handleBubbleSort() {
        System.out.println("Bubble Sort");
        algorithm.bubbleSort(algorithm.readFile("INPUT.TXT"));
    }

    private static void handleSelectionSort() {
        System.out.println("Selection Sort");
        algorithm.selectionSort(algorithm.readFile("INPUT.TXT"));
    }

    private static void handleInsertionSort() {
        System.out.println("Insertion Sort");
        algorithm.insertionSort(algorithm.readFile("INPUT.TXT"));
    }

    private static void handleGenerateRandomizedInput() {
        System.out.print("Please enter the number of elements: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        float[] arr = new float[n];
        
        int choice = 3; 
        
        System.out.println("\nPlease choose one option to continue...\n");
        System.out.println("1. Just random an array");
        System.out.println("2. Random a descending ordered array");
        System.out.println("3. Random a ascending ordered array (already sorted)");
        System.out.println("4. Special case: a random permutation");
        System.out.println("5. Special case: n n-1 n=2 ... 1");
        System.out.println("6. Special case: 1 2 3 ... n");
        System.out.println("7. 1 1 1 1 1 1 -> all are equal\n");

        System.out.print("Choice:");
        choice = scanner.nextInt();

            
        Random rand = new Random();
             
        if (choice > 3) {
            for(int i = 0; i < n; i++)
                arr[i] = (choice == 7) ? 1 : i + 1;
            choice -= 3;
        } else {
            for(int i = 0; i < n; i++) 
                arr[i] = rand.nextFloat() * 100000;
        }
        
        if (choice == 1) {
            for (int i = 0; i < n; i++) {
                algorithm.swap(arr,i,rand.nextInt(n));
            }
        }
        if (choice == 2) {
            Arrays.sort(arr);
            for (int i = 0; i < n/2; i++) 
                algorithm.swap(arr,i,n-i-1);
        }
        else if (choice == 3) Arrays.sort(arr);
        
        
        
        algorithm.writeFile("INPUT.TXT", arr);
    }

    private static void handleBenchmarking() {
        System.out.println("We will use the array from INPUT.TXT as the input source");
        float[] arr = algorithm.readFile("INPUT.TXT");
        
        long start = System.currentTimeMillis();
        algorithm.bubbleSort(arr, false);
        long end = System.currentTimeMillis();
//        System.out.println(Arrays.toString(arr));
        System.out.println("Bubble Sort: " + (end - start) + "ms");
        
        start = System.currentTimeMillis();
        algorithm.selectionSort(arr, false);
        end = System.currentTimeMillis();
//        System.out.println(Arrays.toString(arr));
        System.out.println("Selection Sort: " + (end - start) + "ms");
        
        start = System.currentTimeMillis();
        algorithm.insertionSort(arr, false);
        end = System.currentTimeMillis();
//        System.out.println(Arrays.toString(arr));
        System.out.println("Insertion Sort: " + (end - start) + "ms");
    }
}
