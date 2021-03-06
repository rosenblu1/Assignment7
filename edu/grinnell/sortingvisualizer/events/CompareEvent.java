package grinnell.sortingvisualizer.events;

import java.util.ArrayList;
import java.util.List;

public class CompareEvent<T extends Comparable <T>> implements SortEvent<T> {

  int i;
  int j;
  
  public CompareEvent(int i, int j) {
    this.i = i;
    this.j = j;
  } // CompareEvent

  @Override
  public void apply(T[] arr) {} // apply(arr)

  @Override
  public List<Integer> getAffectedIndices() {
    List<Integer> l = new ArrayList<Integer>();
    l.add(i);
    l.add(j);
    return l;
  } // getAffectedIndices()

  @Override
  public boolean isEmphasized() {
    return false;
  } // isEmphasized()

} // SortEvent<T>
