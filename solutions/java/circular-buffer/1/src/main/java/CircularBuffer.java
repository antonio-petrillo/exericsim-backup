import java.util.ArrayList;

/*

Since this exercise has a difficulty of > 4 it doesn't come
with any starter implementation.
This is so that you get to practice creating classes and methods
which is an important part of programming in Java.

Please remove this comment when submitting your solution.

*/

public class CircularBuffer<T> {

    private ArrayList<T> buffer;
    private int indexPush;
    private int indexPop;
    private int size;
    private int capacity;

    public CircularBuffer(int size) {
        buffer = new ArrayList<>(size);
        indexPush = 0;
        indexPop = 0;
        this.size = size;
        capacity = 0;
    }

    public void clear() {
        indexPop = 0;
        indexPush = 0;
        capacity = 0;
    }

    public void write(T t) throws BufferIOException {
        write(t, false);
    }

    public void overwrite(T t) throws BufferIOException {
        write(t, true);
    }

    private void write(T t, boolean overwrite) throws BufferIOException {
        if (!overwrite && capacity == size) {
            throw new BufferIOException("Tried to write to full buffer");
        }
        if (buffer.size() < size) {
            buffer.add(indexPush, t);
        } else {
            buffer.set(indexPush, t);
        }
        indexPush = (indexPush + 1) % size;
        if (overwrite && capacity == size) {
            indexPop = indexPush;
        }
        capacity = capacity == size ? capacity : capacity + 1;
    }

    public T read() throws BufferIOException {
        if (capacity == 0) {
            throw new BufferIOException("Tried to read from empty buffer");
        }
        T readed = buffer.get(indexPop);
        indexPop = (indexPop + 1) % size;
        capacity--;
        return readed;
    }

}
