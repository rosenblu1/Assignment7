package grinnell.sortingvisualizer.sorts;

import java.util.Comparator;

public class Sorts {
  public static <T extends Comparable<T>> void selectionSort(T[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      int lowestIndex = i;
      for (int j = i; j < arr.length; j++) {
        if (arr[j].compareTo(arr[lowestIndex]) < 0) {
          lowestIndex = j;
        }
      }
      swap(arr, i, lowestIndex);
    }
  }

  public static <T extends Comparable<T>> void insertionSort(T[] arr) {
    for (int i = 1; i < arr.length; i++) {
      for (int j = i; j > 0 && arr[j - 1].compareTo(arr[j]) > 0; j--) {
        swap(arr, j, j - 1);
      }
    }
  }

  public static <T extends Comparable<T>> void mergeSort(T[] arr) {
    mergeH(arr, 0, arr.length);
  }

  public static <T extends Comparable<T>> void quickSort(T[] values, int lb, int ub) {
    // Special case: 0 or 1 elements
    if (ub - lb <= 1) {
      return;
    } // if zero or one elements

    // Prepare bounds for DNF
    int r = lb; // The end of the red/small section
    int b = ub; // The start of the blue/greater section

    // Rearrange the values
    T pivot = values[(ub - lb) / 2 + lb];

    // Divide and sort
    while (r < b) {
      // Greater than pivot, place in 'b' section
      if (values[r].compareTo(pivot) > 0) {
        swap(values, r, b - 1);
        b--;
      } // if
      // Less than pivot, place in 'r' section
      else if (values[r].compareTo(pivot) < 0) {
        r++;
      } // if
    } // while unknown values remain

    // Recurse
    quickSort(values, lb, r);
    quickSort(values, b, ub);
  } // sort(T[], int, int)



  public static <T extends Comparable<T>> void otherSort(T[] arr) {

  }

  // Helpers
  /**
   * Swap the elements at positions i and j.
   */
  public static <T> void swap(T[] arr, int i, int j) {
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }



  /**
   * Merge the values from positions [lo..mid) and [mid..hi) back into the same part of the array.
   *
   * Preconditions: Each subarray is sorted accorting to comparator.
   * @param <T>
   */
  public static <T extends Comparable<T>> void merge(T[] vals, int lo, int mid, int hi) {

    T[] scratch = (T[]) new Object[hi-lo+1];
    int k = 0;
    int i = lo;
    int j = mid;

    while (i < mid && j <= hi) {
      if (vals[i].compareTo(vals[j]) <= 0) {
        scratch[k++] = vals[i];
        i++;
      } else {
        scratch[k++] = vals[j];
        j++;
      } // else
    } // while

    // cleanup work
    while (i < mid) {
      scratch[k++] = vals[i++];
    }
    while (j < hi) {
      scratch[k++] = vals[j++];
    }

    // copying scratch back into values
    int count = 0;
    for (int n = lo; n <= hi; n++) {
      T val = scratch[count];
      vals[n] = val;
      count++;
    } // for
  } // merge
  
  public static <T> void mergeH(T[] values, int lo, int hi, Comparator<? super T> comparator) {
    if (hi-lo <= 1) {
      return;
    }
    int mid = lo + (hi-lo)/2;
    //call helper on lo to mid
    mergeH(values, lo, mid, comparator);
    //call helper on mid to hi
    mergeH(values, mid, hi, comparator);
    //merge both
    merge(values, lo, mid, hi, comparator);
  }
}// Sorts
