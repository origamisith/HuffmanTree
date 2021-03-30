public class PriorityQueue<T extends Comparable<T>> extends ArrayHeap {

    public PriorityQueue(int capacity) {
        super(capacity);
    }

    public void addElement(T object) {
        add(object);
    }

    public T removeNext() {
        return (T) removeMin();
    }

}
