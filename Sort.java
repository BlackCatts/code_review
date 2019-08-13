package com.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Sort {
  public static void main(String args[]) throws IOException {
    int selectedNum = 0;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    do {
      System.out.println("\n\n\n");
      System.out.println("1.Bubble Sort.");
      System.out.println("2.Selection Sort.");
      System.out.println("3.Insertion Sort.");
      System.out.println("4.Quick Sort.");
      System.out.println("5.Merge Sort.");
      System.out.println("6.Exit.");
      
      try {
        selectedNum = Integer.parseInt(reader.readLine());
      } catch(Exception e) {
        // Exit!
        break;
      }
      if (selectedNum == 6) {
        return;
      }
      System.out.println("Enter n");
      
      int lineNum = 0;
      try {
        lineNum = Integer.parseInt(reader.readLine());
      } catch (Exception e) {
        // Exit!
        break;
      }
      
      int array[] = new int[lineNum];
      try {
        for (int i = 0; i < lineNum; i++) {
          array[i] = Integer.parseInt(reader.readLine());
        }
      } catch (Exception e) {
        // Exit!
        break;
      }
      switch (selectedNum) {
        case 1:
          doBinarySort(array, lineNum);
          break;
          
        case 2:
          doSelectionSort(array, lineNum);
          break;
          
        case 3:
          doInsertionSort(array, lineNum);
          break;
          
        case 4:
          doQuickSort(array, lineNum);
          break;
          
        case 5:
          doMergeSort(array, lineNum);
          break;

        default:
          break;
      }
    } while (selectedNum != 6);
  }

  public static void doQuickSort(int[] array, int lineNum) {
    int start = 0;
    int end = lineNum - 1;

    doQuickSortInner(array, start, end);
    print(array, lineNum);
  }

  public static void doBinarySort(int array[], int lineNum) {
    int temp;
    
    for (int i = 0; i < lineNum - 1; i++) {
      for (int j = 0; j < lineNum - 1; j++) {
        if (array[j] > array[(j + 1)]) {
          temp = array[j];
          array[j] = array[(j + 1)];
          array[(j + 1)] = temp;
        }
      }
    }
    print(array, lineNum);
  }

  public static void doSelectionSort(int array[], int lineNum) {
    for (int i = 0; i < lineNum - 1; i++) {
      int imin = i;
      int temp;
      
      for (int j = i + 1; j < lineNum; j++) {
        if (array[j] < array[imin]) {
          imin = j;
        }
      }
      temp = array[i];
      array[i] = array[imin];
      array[imin] = temp;
    }
    print(array, lineNum);
  }

  public static void doInsertionSort(int array[], int lineNum) {
    for (int i = 1; i < lineNum; i++) {
      int val = array[i];
      int hole = i;
      
      while (hole > 0 && array[hole - 1] > val) {
        array[hole] = array[hole - 1];
        hole = hole - 1;
      }
      array[hole] = val;
    }
    print(array, lineNum);
  }

  public static void doMergeSort(int array[], int lineNum) {
    if (lineNum <= 1) {
      return;
    }
    
    int mid = lineNum / 2;
    int left[] = new int[mid];
    int right[] = new int[lineNum - mid];
    
    for (int i = 0; i < mid; i++) {
      left[i] = array[i];
    }
    for (int i = mid; i < lineNum; i++) {
      right[i - mid] = array[i];
    }
    doMergeSort(left, mid);
    doMergeSort(right, lineNum - mid);
    Merge(left, right, array);

    print(array, lineNum);
  }

  private static void Merge(int left[], int right[], int array[]) {
    int nL = left.length;
    int nR = right.length;
    int i, j, k;
    i = j = k = 0;
    
    while (i < nL && j < nR) {
      if (left[i] <= right[j]) {
        array[k] = left[i];
        i++;
        k++;
      } else {
        array[k] = right[j];
        j++;
        k++;
      }
    }
    while (i < nL) {
      array[k] = left[i];
      i++;
      k++;
    }
    while (j < nR) {
      array[k] = right[j];
      j++;
      k++;
    }
  }

  private static void doQuickSortInner(int array[], int start, int end) {
    if (start < end) {
      int pIndex = QuickPartition(array, start, end);
      
      doQuickSortInner(array, start, pIndex - 1);
      doQuickSortInner(array, pIndex + 1, end);
    } else {
      return;
    }
  }

  private static int QuickPartition(int array[], int start, int end) {
    int temp;
    int pivot = array[end];
    int pIndex = start;
    
    for (int i = start; i < end; i++) {
      if (array[i] <= pivot) {
        // swap a[i],apindex
        temp = array[i];
        array[i] = array[pIndex];
        array[pIndex] = temp;
        pIndex++;
      }
    }
    temp = array[pIndex];
    array[pIndex] = array[end];
    array[end] = temp;
    return pIndex;
  }

  public static void print(int a[], int n) {
    System.out.println();
    for (int i = 0; i < n; i++) {
      System.out.print(a[i] + "\t");
    }
  }
}
