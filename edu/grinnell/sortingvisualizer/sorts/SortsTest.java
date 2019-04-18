package grinnell.sortingvisualizer.sorts;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import grinnell.sortingvisualizer.events.SortEvent;
import grinnell.sortingvisualizer.sorts.*;


class SortsTest {

  // +-----------+---------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Test Quicksort, given an expected result and an input array.
   * 
   * @param <T>
   * @param <T>
   */
  <T> void checkSort(Integer expected[], Integer[] values) {
    // selectionSort
    // Create two clones, one to sort and one to eventSort
    Integer[] copy = values.clone();
    Integer[] copy2 = values.clone();
    // Record events and sort both copies
    List<SortEvent<Integer>> events = new ArrayList<SortEvent<Integer>>();
    events = Sorts.selectionSort(copy);
    Sorts.eventSort(copy2, events);
    // Check equals
    assertArrayEquals(expected, copy);
    assertArrayEquals(expected, copy2);

    // Repeat for later sort methods
    // insertionSort
    copy = values.clone();
    copy2 = values.clone();
    events = Sorts.insertionSort(copy);
    Sorts.eventSort(copy2, events);
    assertArrayEquals(expected, copy);
    assertArrayEquals(expected, copy2);

    // mergeSort
    copy = values.clone();
    copy2 = values.clone();
    events = Sorts.mergeSort(copy);
    Sorts.eventSort(copy2, events);
    assertArrayEquals(expected, copy);
    assertArrayEquals(expected, copy2);

    // quickSort
    copy = values.clone();
    copy2 = values.clone();
    events = Sorts.quickSort(copy);
    Sorts.eventSort(copy2, events);
    assertArrayEquals(expected, copy);
    assertArrayEquals(expected, copy2);

    // shellSort
    copy = values.clone();
    copy2 = values.clone();
    events = Sorts.shellSort(copy);
    Sorts.eventSort(copy2, events);
    assertArrayEquals(expected, copy);
    assertArrayEquals(expected, copy2);

  } // checkQuicksort

  /**
   * fail("Not yet implemented"); Permute an already sorted array,
   * 
   * @param <T>
   */
  void checkSortRnd(Integer[] sorted) {
    Integer[] copy = sorted.clone();
    TestUtils.randomlyPermute(copy);
    checkSort(sorted, copy);
  } // checkQuicksort

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  @Test
  <T> void testEmpty() {
    checkSortRnd(new Integer[0]);
  } // testEmpty

  @Test
  <T> void testOrdered() {
    for (int size = 1; size < 20; size++) {
      Integer[] sorted = new Integer[size];
      for (int i = 0; i < size; i++) {
        sorted[i] = i;
      } // for
      checkSort(sorted, sorted.clone());
    } // for
  } // testOrdered

  @Test
  void testBackwards() {
    for (int size = 1; size < 20; size++) {
      Integer[] sorted = new Integer[size];
      Integer[] backwards = new Integer[size];
      for (int i = 0; i < size; i++) {
        backwards[i] = size - i;
        sorted[i] = i + 1;
      } // for
      checkSort(sorted, backwards);
    } // for
  } // testBackwards

  @Test
  void testRandom() {
    for (int trials = 0; trials < 5; trials++) {
      // Some comparatively small ones
      for (int size = 1; size < 20; size++) {
        checkSortRnd(TestUtils.randomIntegers(size));
      } // for size
      // Some larger ones
      for (int size = 30; size < 1000; size = size * 2 + 1) {
        checkSortRnd(TestUtils.randomIntegers(size));
      } // for size
    } // for trials
  } // testRandom()
} // SamQuicksortTests
