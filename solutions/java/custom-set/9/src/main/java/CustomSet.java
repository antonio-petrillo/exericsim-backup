import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;

// Just for fun
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Consumer;

public final class CustomSet<T> extends AbstractSet<T> {
    private static final int DEFAULT = 16;
    private static final float THRESHOLD = 0.75f;
    private static final float DEL_THRESHOLD = 0.10f;
    private static final int BIG_PRIME = 115249;
    private static final Object TOMBSTONE = new Object(); // used to mark the removed object in the underlying array container
    
    private Object[] container = new Object[DEFAULT];
    private int size = 0;

    private class SetIterator<T> implements Iterator<T> {
        int numberOfElementsFounds = 0;
        int index = 0;

        @Override
        public boolean hasNext() {
            return numberOfElementsFounds < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            while (container[index] == null || container[index] == TOMBSTONE) {
                index++;
            }
            T next = (T) container[index]; // current element
            index++; // prepare the iterator for the next iteration
            numberOfElementsFounds++; // increment the number of seen elements
            return next;
        }
    }

    public CustomSet() { }

    public CustomSet(Collection<T> data) {
        for (T elem : data) {
            add(elem);
        }
    }

    private CustomSet(CustomSet<T> set) {
        container = set.container.clone();
        size = set.size;
    }

    @SuppressWarnings("unchecked")
    private void growth() {
        Object[] old = container;
        container = new Object[container.length << 1];
        size = 0;
        for (Object obj : old) {
            if (obj != null && obj != TOMBSTONE) {
                add((T) obj);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void shrink() {
        Object[] old = container;
        container = new Object[container.length >> 1];
        size = 0;
        for (Object obj : old) {
            if (obj != null && obj != TOMBSTONE) {
                add((T) obj);
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    private static int getIndex(Object o, int i, int size) {
        return Math.floorMod(BIG_PRIME * i ^ o.hashCode(), size);
    }

    @Override
    public boolean add(T element) {
        for(int i = 0; i < container.length; i++) {
            int index = getIndex(element, i, container.length);
            if (container[index] == null || container[index] == TOMBSTONE) {
                container[index] = element;
                break;
            }
            if (element.equals(container[index])) {
                return false; // elem already present
            }
        }
        size++;
        if (size / (double) container.length >= THRESHOLD) {
            growth();
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int oldCap = size;
        for(int i = 0; i < container.length; i++) {
            int index = getIndex(o, i, container.length);
            if (container[index] != null && container[index].equals(o)) {
                container[index] = TOMBSTONE;
                size--;
                if (size / (double) container.length <= DEL_THRESHOLD && container.length > DEFAULT) {
                    shrink();
                }
                break;
            }
        }
        return size < oldCap;
    }

    @Override
    public Iterator<T> iterator() {
        return new SetIterator<>();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object target) {
        for(int i = 0; i < container.length; i++) {
            int index = getIndex(target, i, container.length);
            if (container[index] == null) {
                break;
            }
            if (target.equals(container[index])) {
                return true;
            }
        }
        return false;
    }

    public boolean isDisjoint(CustomSet<?> other) {
        for (Object elem : other) {
            if (contains(elem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CustomSet<?>)) {
            return false;
        }
        CustomSet<?> other = (CustomSet<?>) obj;
        if (size != other.size) {
            return false;
        }
        for (Object elem : other) {
            if (!contains(elem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
       int hash = 0;
       boolean first = true;
       for (T elem : this) {
           if (first) {
               first = false;
               hash = elem.hashCode();
           } else {
               hash ^= elem.hashCode();
           }
       }
       return hash;
    }
    
    public CustomSet<T> getIntersection(CustomSet<?> other) {
        CustomSet<T> intersection = new CustomSet<>();
        for (T elem : this) {
            if (other.contains(elem)) {
                intersection.add(elem);
            }
        }
        return intersection;
    }
    
    public CustomSet<T> getUnion(CustomSet<? extends T> other) {
        CustomSet<T> union = new CustomSet<>(this);
        for (T elem : other) {
            union.add(elem);
        }
        return union;
    }
    
    public CustomSet<T> getDifference(CustomSet<? extends T> other) {
        CustomSet<T> difference = new CustomSet<>();
        for (T elem : this) {
            if (!other.contains(elem)) {
                difference.add(elem);    
            }
        }
        return difference;
    }
    
    public boolean isSubset(CustomSet<?> other) {
        for(Object elem : other) {
            if(!contains(elem)) {
                return false;
            }
        }
        return true;
    }

    // Can't generalize the type of other
    public CustomSet<T> getSymmetricalDifference(CustomSet<T> other) {
        CustomSet<T> diff = new CustomSet<>();
        for (T elem : this) {
            if (!other.contains(elem)) {
                diff.add(elem);
            }
        }
        for (T elem : other) {
            if (!contains(elem)) {
                diff.add(elem);
            }
        }
        return diff;
    }


    // My implementation of a custom "functional" API
    // It is more an exercise in style on the use generics types in java, the stream api is far better than this.

    public <U> CustomSet<U> myMap(Function<? super T, ? extends U> f) {
        CustomSet<U> mapped = new CustomSet<>();
        for (T elem : this) {
            mapped.add(f.apply(elem));
        }
        return mapped;
    }

    public CustomSet<T> myFilter(Predicate<? super T> p) {
        CustomSet<T> filtered = new CustomSet<>();
        for (T elem : this) {
            if(p.test(elem)) {
                filtered.add(elem);    
            }
        }
        return filtered;
    }

    public <U> U myReduce(BiFunction<? super T, ? super U, ? extends U> f, U initial) {
        for (T elem : this) {
            initial = f.apply(elem, initial);
        }
        return initial;
    }

    public void myForEach(Consumer<? super T> f) {
        for (T elem : this) {
            f.accept(elem);
        }
    }
}
