package grinnell.sortingvisualizer.sorts;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import grinnell.sortingvisualizer.events.CompareEvent;
import grinnell.sortingvisualizer.events.CopyEvent;
import grinnell.sortingvisualizer.events.SortEvent;
import grinnell.sortingvisualizer.events.SwapEvent;

public class Sorts {

  public static <T extends Comparable<T>> List<SortEvent<T>> selectionSort(T[] arr) {
    List<SortEvent<T>> l = new ArrayList<SortEvent<T>>();
    for (int i = 0; i < arr.length - 1; i++) {
      int lowestIndex = i;
      for (int j = i; j < arr.length; j++) {
        l.add(new CompareEvent<T>(j, lowestIndex));
        if (arr[j].compareTo(arr[lowestIndex]) < 0) {
          lowestIndex = j;
        }
      }
      // swap
      T temp = arr[i];
      arr[i] = arr[lowestIndex];
      arr[lowestIndex] = temp;
      l.add(new SwapEvent<T>(i, lowestIndex));
    }
    return l;
  } // selectionSort

  public static <T extends Comparable<T>> List<SortEvent<T>> insertionSort(T[] arr) {
    List<SortEvent<T>> l = new ArrayList<SortEvent<T>>();
    for (int i = 1; i < arr.length; i++) {
      for (int j = i; j > 0; j--) {
        l.add(new CompareEvent<T>(j - 1, j));
        if (arr[j - 1].compareTo(arr[j]) > 0) {
          // swap
          T temp = arr[j];
          arr[j] = arr[j - 1];
          arr[j - 1] = temp;
          l.add(new SwapEvent<T>(j, j - 1));
        } //if
      } //for
    } //for
    return l;
  } // insertionSort

  public static <T extends Comparable<T>> List<SortEvent<T>> mergeSort(T[] arr) {
    List<SortEvent<T>> l = new ArrayList<SortEvent<T>>();
    mergeH(arr, 0, arr.length, l);
    return l;
  } // mergeSort

  public static <T extends Comparable<T>> List<SortEvent<T>> quickSort(T[] values) {
    List<SortEvent<T>> l = new ArrayList<SortEvent<T>>();
    return qSort(values, 0, values.length - 1, l);
  } // quickSort


  public static <T extends Comparable<T>> List<SortEvent<T>> shellSort(T[] arr) {
    return sSort(arr);
  } // shellSort

  public static <T extends Comparable<T>> void eventSort(T[] arr, List<SortEvent<T>> events) {
    for (int i = 0; i < events.size(); i++) {
    events.get(i).apply(arr);
    }
  } // eventSort(T[], List<SortEvent<T>>)

  /*********************************************
   ****************** Helpers*******************
   *********************************************/


  /**
   * Merge the values from positions [lo..mid) and [mid..hi) back into the same part of the array.
   *
   * Preconditions: Each subarray is sorted accorting to comparator.
   * 
   * @param <T>
   */
  public static <T extends Comparable<T>> void merge(T[] vals, int lo, int mid,
      int hi, List<SortEvent<T>> l) {

    T[] scratch = (T[]) new Object[hi - lo + 1];
    int k = 0;
    int i = lo;
    int j = mid;

    while (i < mid && j <= hi) {
      l.add(new CompareEvent<T>(i, j));
      if (vals[i].compareTo(vals[j]) <= 0) {
       // l.add(new CopyEvent<T>(k, vals[i]));
        scratch[k++] = vals[i];
        i++;
      } else {
      //  l.add(new CopyEvent<T>(k, vals[j]));
        scratch[k++] = vals[j];
        j++;
      } // else
    } // while

    // cleanup work
    while (i < mid) {
     // l.add(new CopyEvent<T>(k, vals[i]));
      scratch[k++] = vals[i++];
    }
    while (j < hi) {
     // l.add(new CopyEvent<T>(k, vals[j]));
      scratch[k++] = vals[j++];
    }

    // copying scratch back into values
    int count = 0;
    for (int n = lo; n <= hi; n++) {
      T val = scratch[count];
      vals[n] = val;
      count++;
      l.add(new CopyEvent<T>(n, val));
    } // for
  } // merge

  public static <T extends Comparable<T>> void mergeH(T[] values, int lo, int hi, List<SortEvent<T>> l) {
    if (hi - lo <= 1) {
      return;
    }
    int mid = lo + (hi - lo) / 2;
    // call helper on lo to mid
    mergeH(values, lo, mid, l);
    // call helper on mid to hi
    mergeH(values, mid, hi, l);
  } // mergeH

  public static <T extends Comparable<T>> int partition(T[] arr, int low, int high,
      List<SortEvent<T>> l) {
    T pivot = arr[high];
    int i = (low - 1); // index of smaller element
    for (int j = low; j < high; j++) {
      // If current element is smaller than or
      // equal to pivot
      l.add(new CompareEvent<T>(j, high));
      if (arr[j].compareTo(pivot) <= 0) {
        i++;

        // swap arr[i] and arr[j]
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        l.add(new SwapEvent<T>(i, j));
      }
    }

    // swap arr[i+1] and arr[high] (or pivot)
    T temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;
    l.add(new SwapEvent<T>(i + 1, high));

    return i + 1;
  }


  /*
   * The main function that implements QuickSort() arr[] --> Array to be sorted, low --> Starting
   * index, high --> Ending index
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> qSort(T[] arr, int low, int high, List<SortEvent<T>> l) {
   
    if (low < high) {
      /*
       * pi is partitioning index, arr[pi] is now at right place
       */
      int pi = partition(arr, low, high, l);

      // Recursively sort elements before
      // partition and after partition
      qSort(arr, low, pi - 1, l);
      qSort(arr, pi + 1, high, l);
    }
    return l;
  } // qSort

  /* function to sort arr using shellSort */
  public static <T extends Comparable<T>> List<SortEvent<T>> sSort(T[] arr) {
    List<SortEvent<T>> l = new ArrayList<SortEvent<T>>();

    int n = arr.length;

    // Start with a big gap, then reduce the gap
    for (int gap = n / 2; gap > 0; gap /= 2) {
      // Do a gapped insertion sort for this gap size.
      // The first gap elements a[0..gap-1] are already
      // in gapped order keep adding one more element
      // until the entire array is gap sorted
      for (int i = gap; i < n; i += 1) {
        // add a[i] to the elements that have been gap
        // sorted save a[i] in temp and make a hole at
        // position i
        T temp = arr[i];

        // shift earlier gap-sorted elements up until
        // the correct location for a[i] is found
        int j;
        for (j = i; j >= gap && arr[j - gap].compareTo(temp) > 0; j -= gap) {
          l.add(new CompareEvent<T>(j - gap, i));
          arr[j] = arr[j - gap];
          l.add(new CopyEvent<T>(j, arr[j - gap]));
        } // for
        // put temp (the original a[i]) in its correct
        // location
        arr[j] = temp;
        l.add(new CopyEvent<T>(j, temp));
      } // for
    } // for
    return l;
  } // sSort

}// Sorts
