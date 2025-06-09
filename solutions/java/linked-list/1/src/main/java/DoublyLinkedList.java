class DoublyLinkedList<T> {
    private Element<T> head;
    private Element<T> tail;

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    void push(T value) {
        if (tail == null) {
            tail = new Element<T>(value, null, null);
            head = tail;
        } else {
            tail = new Element<T>(value, tail, null);
            tail.prev.next = tail;
        }
    }

    T pop() {
        if (tail != null) {
            T value = tail.value;
            if (head == tail) {
                head = null;
            } else {
                tail.prev.next = null;
                tail = tail.prev;
                // the old tail now have no reference so it will be garbage collected
            }
            return value;
        }
        // the exercise don't require to throw an exception
        return null;
    }

    void unshift(T value) {
        if (head == null) {
            head = new Element<T>(value, null, null);
            tail = head;
        } else {
            head = new Element<T>(value, null, head);
            head.next.prev = head;
        }
    }

    T shift() {
        if (head != null) {
            T value = head.value;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head.next.prev = null;
                head = head.next;
            }
            return value;
        }
        return null;
    }

    private static final class Element<T> {
        private final T value;
        private Element<T> prev;
        private Element<T> next;

        Element(T value, Element<T> prev, Element<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
