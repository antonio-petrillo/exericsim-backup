import java.util.List;

/*

Since this exercise has a difficulty of > 4 it doesn't come
with any starter implementation.
This is so that you get to practice creating classes and methods
which is an important part of programming in Java.

Please remove this comment when submitting your solution.

*/

// TODO: do with generics, not only list of integer

public class BinarySearch {

    private List<Integer> list;

    public BinarySearch(List<Integer> sortedList) {
        list = sortedList;
    }

    public int indexOf(int target) throws ValueNotFoundException {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            int value = list.get(middle);
            if (value > target) {
                high = middle - 1;
            } else if (value < target) {
                low = middle + 1;
            } else {
                return middle;
            }
        }
        throw new ValueNotFoundException("Value not in array");
    }

}
