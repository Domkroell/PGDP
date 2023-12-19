import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryIterator<T> implements java.util.Iterator<T> {
    public static void main(String[] args) {
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        for (int i = 0; i < 400; i++) {
            first.add(i);
        }
        for (int i = 0; i < 10; i++) {
            second.add(i);
        }
        BinaryIterator<Integer> otto = new BinaryIterator<>(first, second);
        while (otto.hasNext()) {
            System.out.println(otto.next());
        }
    }

    Queue<T> otto;


    public BinaryIterator(List<T> first, List<T> second) {
        otto = new LinkedList<>();
        if (first == null && second == null) return;
        if (first == null) {
            this.otto.addAll(second);
            return;
        }
        if (second == null) {
            this.otto.addAll(first);
            return;
        }
        int firstPointer = 0;
        int secondPointer = 0;
        while (firstPointer < first.size() && secondPointer < second.size()) {
            otto.add(first.get(firstPointer++));
            otto.add(second.get(secondPointer++));
        }
        while (firstPointer < first.size()) {
            otto.add(first.get(firstPointer++));
        }
        while (secondPointer < second.size()) {
            otto.add(second.get(secondPointer++));
        }
    }

    @Override
    public boolean hasNext() {
        return !otto.isEmpty();
    }

    @Override
    public T next() {
        if (otto.isEmpty()) return null;
        return otto.poll();

    }
}
