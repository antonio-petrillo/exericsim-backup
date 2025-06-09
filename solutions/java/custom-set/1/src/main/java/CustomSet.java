import java.util.Collection;
import java.util.Iterator;

public class CustomSet<T> implements Iterable<T> {
    private static final int DEFAULT = 16;
    private static final float THRESHOLD = 0.75f; 
    private static final int BIG_PRIME = 115249;
    
    private Object[] container = new Object[DEFAULT];
    private int cap = 0;

    
    public CustomSet() { }

    public CustomSet(Collection<T> data) {
        for (var elem : data) {
            add(elem);
        }
    }
    
    // I don't implement shrink because no operation remove is requested
    @SuppressWarnings("unchecked")
    private void growth() {
        var old = container;
        container = new Object[container.length << 1];
        for (var o : old) {
            add((T) o);
        }
    }
    
    private static int getIndex(Object o, int i, int size) {
        return Math.floorMod(BIG_PRIME * i ^ o.hashCode(), size);
    } 
    
    public boolean add(T element) {
        for(int i = 0; i < container.length; i++) {
            var index = getIndex(element, i, container.length);
            if (container[index] == null) {
                container[index] = element;
                break;
            }
            if (element.equals(container[index])) {
                return false; // elem already present
            }
        }
        cap++;
        if (cap / (double) container.length >= THRESHOLD) {
            growth();
        }
        return true;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int found = 0, index = 0;
            
            @Override
            public boolean hasNext() {
                return found < cap;
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                while(true) {
                    if (container[index] != null) {
                        found++;
                        T next = (T)container[index];
                        index++;
                        return next;
                    }
                    index++;
                }
            }
        };
    }

    public CustomSet(CustomSet<T> set) {
        container = set.container.clone();
        cap = set.cap;
    }
    
    public boolean isEmpty() {
        return cap == 0;
    }

    public boolean contains(Object element) {
        for(var target : this) {
            if(target.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public boolean isDisjoint(CustomSet<T> other) {
        for (var elem : other) {
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
        if (cap != other.cap) {
            return false;
        }
        for (var elem : other) {
            if (!contains(elem)) {
                return false;
            }
        }
        return true;
    }

    public CustomSet<T> getIntersection(CustomSet<?> other) {
        CustomSet<T> intersection = new CustomSet<>();
        for (var elem : this) {
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
        for(var elem : other) {
            if(!contains(elem)) {
                return false;
            }
        }
        return true;
    }
}
