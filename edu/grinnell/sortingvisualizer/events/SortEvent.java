package grinnell.sortingvisualizer.events;

import java.util.List;

public interface SortEvent<T> {
  void apply(T[] arr);

  List<Integer> getAffectedIndices();

  boolean isEmphasized();


}
