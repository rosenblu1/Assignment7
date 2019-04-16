package grinnell.sortingvisualizer.events;

import java.util.ArrayList;
import java.util.List;

public class CopyEvent<T extends Comparable <T>> implements SortEvent<T> {

  int i;
  T val;
  
  public CopyEvent(int i, T val) {
    this.i = i;
    this.val = val;
  } // CopyEvent(int)
  
  @Override
  public void apply(T[] arr) {
    arr[this.i] = val;
  } // apply(arr)

  @Override
  public List<Integer> getAffectedIndices() {
    List<Integer> l = new ArrayList<Integer>();
    l.add(i);
    return l;
  } // getAffectedIndices()

  @Override
  public boolean isEmphasized() {
    return true;
  } // isEmphasized

} // CopyEvent
