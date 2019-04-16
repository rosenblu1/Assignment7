package grinnell.sortingvisualizer.events;

import java.util.ArrayList;
import java.util.List;

public class SwapEvent<T extends Comparable <T>> implements SortEvent<T> {

  private int i;
  private int j;

  public SwapEvent(int index1, int index2) {
    this.i = index1;
    this.j = index2;
  } // SwapEvent(int, int)

  @Override
  public void apply(T[] arr) {
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  } // apply(arr)

  @Override
  public List<Integer> getAffectedIndices() {
    List<Integer> l = new ArrayList<Integer>();
    l.add(i);
    l.add(j);
    return l;
  } // getAffectedIndices()

  @Override
  public boolean isEmphasized() {
    return true;
  } // isEmphasized()

} // SwapEvent<T>
