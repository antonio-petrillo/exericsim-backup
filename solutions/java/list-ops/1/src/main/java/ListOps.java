import java.util.List;
import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Collections;

class ListOps {

    static <T> List<T> append(List<T> list1, List<T> list2) {
        List<T> result = new ArrayList<>(list1);
        list2.stream().forEach(e -> result.add(e));
        return result;
    }

    static <T> List<T> concat(List<List<T>> listOfLists) {
        List<T> result = new ArrayList<>();
        for(var l : listOfLists) {
            result = append(result, l);
        }
        return result;
    }

    static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        return list.stream().filter(predicate).toList();
    }

    static <T> int size(List<T> list) {
        return list.size();
    }

    static <T, U> List<U> map(List<T> list, Function<T, U> transform) {
        return list.stream().map(transform).toList();
    }

    static <T> List<T> reverse(List<T> list) {
        var reversed = new ArrayList<T>();
        var iter = list.listIterator(list.size());
        while(iter.hasPrevious()) {
            reversed.add(iter.previous());
        }
        return reversed;
    }

    static <T, U> U foldLeft(List<T> list, U initial, BiFunction<U, T, U> f) {
        for(var el : list) {
            initial = f.apply(initial, el);
        }
        return initial;
    }

    static <T, U> U foldRight(List<T> list, U initial, BiFunction<T, U, U> f) {
        var iter = list.listIterator(list.size());
        while(iter.hasPrevious()) {
            initial = f.apply(iter.previous(), initial);
        }
        return initial;
    }

    private ListOps() {
        // No instances.
    }

}
