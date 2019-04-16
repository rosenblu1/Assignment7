package grinnell.sortingvisualizer.events;

import java.util.ArrayList;
import java.util.List;

public class CompareEvent<T> implements SortEvent<T>{

 List<Integer> l = new ArrayList<Integer>();

 public CompareEvent (int i, int j) {
  l.add(i);
  l.add(j);
 } // CompareEvent
 
  @Override
  public void apply(T[] arr) {
    } // apply(arr)

  @Override
  public List<Integer> getAffectedIndices() {
    return l;
  } // getAffectedIndices()

  @Override
  public boolean isEmphasized() {
    return false;
  } // isEmphasized()

} // SortEvent<T>
