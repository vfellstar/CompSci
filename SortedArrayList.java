import java.util.ArrayList;

public class SortedArrayList extends ArrayList {
/*  This method compares what is to be added with what is currently in the arraylist
    It then inserts the Element to be added in the correct place
    and then returns the sortedList */

public static <E extends Comparable<? super E>> ArrayList<E> sortList(ArrayList<E> arrayList, E toAdd){
    ArrayList<E> sortedList;
    Boolean added = false;
    if (arrayList.toArray().length <= 0) {
        arrayList.add(toAdd);
    }
    else {
        for (int i=0; i < arrayList.toArray().length; i++) {
            if(toAdd.compareTo(arrayList.get(i)) <=0) {
                arrayList.add(i, toAdd);
                added = true;
                break;
            }
        }
        if (!added) {
            if (toAdd.compareTo(arrayList.get(arrayList.toArray().length - 1)) <=0) {
                arrayList.add(arrayList.toArray().length - 1, toAdd);
            } else {
                arrayList.add(toAdd);
            }
        }
    }
    sortedList = arrayList;
    return sortedList;
}
}