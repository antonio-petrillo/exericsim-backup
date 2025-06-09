import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

class SimpleLinkedList<T> {

    private Node head;
    private int size;

    private class Node {
        T value;
        Node next;

        Node(T value) {
            this.value = value;
            this.next = null;
        }

    }

    SimpleLinkedList() {
        this.head = null;
        this.size = 0;
    }

    SimpleLinkedList(T[] values) {
        this();
        for (int i = values.length - 1; i >= 0; i--) {
            push(values[i]);
        }
    }

    void push(T value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    T pop() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        T result = head.value;
        head = head.next;
        size--;
        return result;
    }

    void reverse() {
        if (size() > 0) {
            Node iter = head.next;
            head.next = null;
            while (iter != null) {
                Node next = iter.next;
                iter.next = head;
                head = iter;
                iter = next;
            }
        }
    }

    // why it's named clazz?
    T[] asArray(Class<T> clazz) {
        T[] result = (T[]) Array.newInstance(clazz, size());
        int i = 0;
        for (Node iter = head; iter != null; iter = iter.next) {
            result[i] = iter.value;
            i++;
        }
        return result;
    }

    int size() {
        return size;
    }

}
