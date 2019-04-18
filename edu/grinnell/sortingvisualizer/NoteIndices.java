package grinnell.sortingvisualizer;

import grinnell.sortingvisualizer.sorts.*;

/**
 * A collection of indices into a Scale object. These indices are the subject of the various sorting
 * algorithms in the program.
 */
public class NoteIndices {

  Integer[] arr;
  boolean[] highlight;
  

  /**
   * @param n the size of the scale object that these indices map into
   */
  public NoteIndices(int n) {
    this.arr = new Integer[n];
    this.highlight = new boolean[n];
    for (int i = 0; i < n; i++) {
      this.arr[i] = i;
    } // for
  } // NoteIndices

  /**
   * Reinitializes this collection of indices to map into a new scale object of the given size. The
   * collection is also shuffled to provide an initial starting point for the sorting process.
   * 
   * @param n the size of the scale object that these indices map into
   */
  public void initializeAndShuffle(int n) {
    // cut or expand NoteIndices to length n
    this.arr = new Integer[n];
    this.highlight = new boolean[n];
    // populate the array
    for (int i = 0; i < n; i++) {
      this.arr[i] = i;
    } // for
    // shuffle the indices
    TestUtils.randomlyPermute(this.arr);
  } // initializeAndShuffle(int)

  /** @return the indices of this NoteIndices object */
  public Integer[] getNotes() {
    return arr;
  }

  /**
   * Highlights the given index of the note array
   * 
   * @param index the index to highlight
   */
  public void highlightNote(int index) {
    this.highlight[index] = true;
  }

  /** @return true if the given index is highlighted */
  public boolean isHighlighted(int index) {
    return this.highlight[index];
  }

  /** Clears all highlighted indices from this collection */
  public void clearAllHighlighted() {
    for (int i = 0; i < highlight.length; i++) {
      this.highlight[i] = false;
    }
  }
} // NoteIndices
