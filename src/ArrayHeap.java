//------------------NOTE------------------------------------------------
//----------FOR UP-TO-DATE PROJECT ZIP, SEE LINK BELOW-----------------
// https://drive.google.com/drive/u/1/folders/1vR9kZc_ygT2nJVTnDTGHf1kLKlew-V4M

import java.lang.reflect.Array;
import java.util.Arrays;

//Minheap
public class ArrayHeap<T extends Comparable<T>> {
    T[] array;
    int capacity;
    int lastNode;
    Class<T> type;
    public ArrayHeap(int capacity) {
        this.capacity = capacity;
        lastNode = -1;
    }

    public void add(T element) {
        if(lastNode == -1) {
            type = (Class<T>) element.getClass();
            array = (T[]) Array.newInstance(type, capacity);
        }
        ensureCapacity();
        array[lastNode+1] = element;
        lastNode++;
//        System.out.println(toString());
        heapifyAdd();
    }

    public T removeMin() {
        T result = array[0];
        swap(0, lastNode);
        array[lastNode] = null;
        lastNode--;
        heapifyRemove();
        return result;

    }

    public T peek() {
        return array[0];
    }

    private void ensureCapacity() {
        if(lastNode < array.length-1) return;
        System.out.println("+150% capacity");
        capacity = capacity + capacity /2;
        T[] newArray = (T[]) Array.newInstance(type, capacity);
        System.arraycopy(array, 0, newArray, 0, array.length);

        array = newArray;

    }

    private void heapifyAdd() {
        if(lastNode == 0) return;
        int current = lastNode;
        int parent = (current%2 == 0) ? current/2-1 : (current-1)/2;
//        System.out.println("Current " + array[current]);
//        System.out.println("Parent " + array[parent]);
        while (parent >=0 && array[current].compareTo(array[parent]) <= 0) {
//            System.out.println("Current " + array[current]);
//            System.out.println("Parent " + array[parent]);
            swap(current, parent);
//            System.out.println("Swapped " + array[current] + " and "  + array[parent]);
            current = parent;
            parent = (current%2 == 0) ? current/2-1 : (current-1)/2;
        }
    }

    private void heapifyRemove() {
        if(lastNode == 0) return;
        int current = 0;
        int left = 1;
        int right = 2;
        while (left <= lastNode && (array[current].compareTo(array[left]) > 0 || array[current].compareTo(array[right]) > 0)) {
            int compare = array[left].compareTo(array[right]);
            if(compare <= 0 || lastNode == 1) {
                swap(current, left);
                current =left;
            }
            else {
                swap(current, right);
                current = right;
            }
            left = current * 2 + 1;
            right = current * 2+2;
        }
    }

    private void swap(int first, int second) {
        T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public int size() {
        return lastNode+1;
    }

    public boolean verifyInvariant(int i) {
        int left = 2*i+1;
        int right = 2*i+2;
        if ( right > lastNode) return true;
        boolean current = (array[i].compareTo(array[right]) <= 0 && array[i].compareTo(array[left]) <= 0);
        if(!current) {
            System.out.println("Error at " + i);
        }
        boolean leftVerify = verifyInvariant(left);
        boolean rightVerify = verifyInvariant(right);
        return current && leftVerify && rightVerify;
    }

    public boolean isEmpty() {
        return lastNode <= 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for(int i = 0; i < lastNode+1; i++) {
            sb.append(array[i] + " ");
            //Next line makes line breaks between levels for readability
            if(Math.log(i+2)/Math.log(2)%1.0 == 0) sb.append("\n");
        }
        sb.append("}");

        return sb.toString();
    }
}
