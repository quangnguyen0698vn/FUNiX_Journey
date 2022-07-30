
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author quang
 */
public class Algorithm {

    /**
     * Writing the array read from input array arr to file
     *
     * @param fileName The file name of file to write value
     * @param arr      The input float array
     *
     */
    
    public void writeFile(String fileName, float[] arr) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            
            fileWriter.write(arr.length + "\n");
            for (int i = 0; i < arr.length; i++) {
                if (i==0) 
                    fileWriter.write(Float.toString(arr[i]));
                else 
                    fileWriter.write(" " + Float.toString(arr[i]));
            }
              
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    
     /**
     * Writing the output (an ArrayList of String) into an output file
     *
     * @param fileName The file name of file to write value
     * @param lines    lines in the output file
     *
     */

    public void writeFile(String fileName, List<String> lines) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (String item : lines) {
                fileWriter.write(item);
                fileWriter.write("\n");
            }
              
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    
    /**
     * Writing the steps of the sort algorithms (or other output) into Console
     * @param lines 
     */
    public void writeConsole(List<String> lines) {
        for (String s : lines)
            System.out.println(s);
    }



    /**
     * Reading the file then input to the array arr
     *
     * @param fileName The file name of file to read
     * @return Returning a array read from the file
     */

    public float[] readFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            int n = scanner.nextInt();
            float[] arr = new float[n];
            scanner.nextLine();
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextFloat();
            }
            return arr;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * this function is to generate a string describes the step (i+1) of the 
     * sort algorithms
     * @param i
     * @param ret
     * @return a string describes the step (i+1) of the sort algorithms 
     */
    private String generateMessage(int i, float[] ret) {
        StringBuilder message = new StringBuilder();
        message.append("Step " + (i+1) + ": ");
        message.append(Arrays.toString(ret));
        return message.toString();
    }


    /**
     * Sorting the input array arr using Bubble Sort algorithm.
     *
     * @param arr Input array using for sorting
     * @return Returning a sorted array by using the Bubble Sort algorithm
     *
     */

    public float[] bubbleSort(float arr[]) {
        return bubbleSort(arr, true);
    }
    
    /**
     * Sorting the input array arr using Bubble Sort algorithm.
     *
     * @param arr Input array using for sorting
     * @param display enable/disable write to file/ show to console the step and
     * result of the algorithm
     * @return Returning a sorted array by using the Bubble Sort algorithm
     *
     */
    
    public float[] bubbleSort(float arr[], boolean display) {
        int n = arr.length;
        float[] ret = Arrays.copyOf(arr, n);
        ArrayList<String> lines = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            boolean flag = false;
            /* Bien flag theo doi xem sau khi ket thuc vong lap ben duoi
            co 2 phan tu nao duoc doi cho hay khong
            Neu khong co 2 phan tu duoc doi cho => mang da duoc sap xep
            => ket thuc thuat toan
            */
            for (int j = 0; j+1 < n; j++)
                if (ret[j] > ret[j+1]) { //Thuc hien viec so sanh 2 phan tu lien tiep trong mang
                    swap(ret, j, j+1);
                    flag = true;
                }
            
            if (display) {
                String message = generateMessage(i, ret);
                lines.add(message);
            }
            
            if (!flag) break;
        }
        
        if (display) {
            lines.add("Bubble Sort Results:");
            lines.add(Arrays.toString(ret));
            writeConsole(lines);
            writeFile("OUTPUT1.TXT", lines);
        }
        
        return ret;
    }



    /**
     * Sorting the input array arr using Selection Sort algorithm.
     *
     * @param arr Input array using for sorting
     * @return Returning a sorted array by using the Selection Sort algorithm
     *
     */

    public float[] selectionSort(float arr[]) {
        return selectionSort(arr, true);
    }
    
    /**
     * Sorting the input array arr using Selection Sort algorithm.
     *
     * @param arr Input array using for sorting
     * @param display enable/disable write to file/ show to console the step and
     * result of the algorithm
     * @return Returning a sorted array by using the Selection Sort algorithm
     *
     */
    
    public float[] selectionSort(float arr[], boolean display) {
        int n = arr.length;
        float[] ret = Arrays.copyOf(arr, n);
        ArrayList<String> lines = new ArrayList<>();

        for(int i = 0; i < n-1; i++) {
            /*
            ret[0..i-1] da duoc sap xep
            Thuc hien viec tim minIndex cua subarray[i..n-1] va dua phan tu co
            gia tri be nhat do ve vi tri i
            ret[0..i] duoc sap xep
            */
            int minIndex = i;
            for (int j = i+1; j < n; j++)
                if (ret[j] < ret[minIndex]) {
                    minIndex = j;
                }
            swap(ret, i, minIndex);
            
            if (display) {
                String message = generateMessage(i, ret);
                lines.add(message);       
            }
        }
        
        if (display) {
            lines.add("Selection Sort Results:");
            lines.add(Arrays.toString(ret));
            writeConsole(lines);
            writeFile("OUTPUT2.TXT", lines);
        
        }
        return ret;
    }



    /**
     * Sorting the input array arr using Insertion Sort algorithm.
     *
     * @param arr Input array using for sorting
     * @return Returning a sorted array by using the Insertion Sort algorithm
     *
     */
    
    public float[] insertionSort(float arr[]) {
        return insertionSort(arr, true);
    }
    
    /**
     * Sorting the input array arr using Insertion Sort algorithm.
     *
     * @param arr Input array using for sorting
     * @param display enable/disable write to file/ show to console the step and
     * result of the algorithm
     * @return Returning a sorted array by using the Insertion Sort algorithm
     *
     */

    public float[] insertionSort(float arr[], boolean display) {
        int n = arr.length;
        float[] ret = Arrays.copyOf(arr, n);
        ArrayList<String> lines = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            /*
            ret[0..i-1] da duoc sap xep
            Tim vi tri de chen ret[i] vao mang ret[0..i-1] de co mang ret[0..i]
            duoc sap xep, vong lap duoc break ngay khi tim duoc vi tri j lon nhat ma
            key = ret[i] >= ret[j], best case la O(1) khi mang duoc sap xep
            */
            int insertPosition = 0;
            float key = ret[i];
            for (int j=i-1; j >= 0; j--) 
                if (key >= ret[j]) {
                    insertPosition = j+1;
                    break;
                }
            
            /*
            Thuc hien viec don cac phan tu qua phai de co cho chen them 
            1 gia tri key
            */
            for(int j=i; j > insertPosition; j--)
                ret[j] = ret[j-1];
            
            ret[insertPosition] = key;
            
            if (display) {
                String message = generateMessage(i, ret);
                lines.add(message);
            }
        }
        if (display) {
            lines.add("Insertion Sort Results:");
            lines.add(Arrays.toString(ret));
            writeConsole(lines);
            writeFile("OUTPUT3.TXT", lines);
        }
        return ret;
    }



    /**
     * Searching the indices of elements in array [arr] greater than value. Printing
     * and writing all indices to the console screen and file OUTPUT4.TXT separated by space.
     *
     * @param arr   Input array using for searching
     * @param value The value for searching
     *
     */

    public void search(float arr[], float value) {
        List<Integer> ret = new ArrayList<>();
        ArrayList<String> lines = new ArrayList<>();

        /*Thuat toan linear search voi do phuc tap O(N)*/
        for (int i = 0; i < arr.length; i++) 
        if (arr[i] > value)
            ret.add(i);
        
        
        if (ret.isEmpty()) {
            String firstLine = "All elements are less than or equal to " 
                    + Float.toString(value) + ".\nNo Index Found!";
            lines.add(firstLine);
        } 
        else {
            String firstLine = "Found " + ret.size() + " greater than " + Float.toString(value);
            lines.add(firstLine);
            
            StringBuilder message = new StringBuilder("Index List: ");
            message.append(ret.get(0));
            for (int i = 1; i < ret.size(); i++)
                message.append(" " + ret.get(i));
            lines.add(message.toString());
        }
       
        writeConsole(lines);
        writeFile("OUTPUT4.TXT", lines);
        
    }


     /**
     * Searching the smallest index of elements in sorted array of orginal array [arr] 
     * that equal to value. Printing the output to the console screen and 
     * file OUTPUT5.TXT.
     *
     * @param arr   Input array using for searching
     * @param value The value for searching
     *
     */
   

    public int smallestIndexEqualValue(float arr[], float value) {
        float[] sortedArr = selectionSort(arr, false);
        int idx = binarySearch(sortedArr, 0, sortedArr.length-1, value);
        List<String> lines = new ArrayList<>();
        if (idx == -1) lines.add("Not found any value " + Float.toString(value) + " in the array.");
        else lines.add("Index of first element equal to " + Float.toString(value) + " in the sorted array is: " + idx);
        
        
        writeConsole(lines);
        writeFile("OUTPUT5.txt", lines);
        return idx;
    }
    
     /**
     * Searching by using the Binary Search algorithm. Returning the first index of
     * value if it is present in array arr, otherwise, return -1. The index also
     * written to file OUTPUT5.TXT and shown on the console screen.
     *

     * @param arr   Input array using for searching
     * @param left  The left index
     * @param right The right index
     * @param value The value for searching
     * @return The index of the element if found, otherwise, return -1
     *
     */
    public int binarySearch(float arr[], int left, int right, float value) {
        int ret = -1;
        
        while (left <= right) {
            int middle = (left + right) / 2;
            if (arr[middle] == value) {
                ret = middle;
                right = middle - 1;
                /*
                Min Index hien tai la middle
                Tiep tuc tim trong khoang [left..middle-1] xem con co phan tu
                nao bang gia tri value can tim khong
                */
            }
            else if (arr[middle] > value)
                /*
                Tat ca phan tu arr[middle..right] deu lon hon value
                Tim tiep trong khoang [left..middle-1]
                */
                right = middle - 1;
            else
                /*
                Tat ca phan tu arr[left..middle] deu nho hon value
                Tim tiep trong khoang [middle+1..right]
                */
                left = middle + 1;
        }
        return ret;
    }

    public void swap(float[] ret, int i, int j) {
        float temp = ret[i];
        ret[i] = ret[j];
        ret[j] = temp;
    }

}
