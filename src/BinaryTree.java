import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<T extends Comparable<T>> {
    BinaryNode<T> root;

    public BinaryTree(T value) {
        this.root = new BinaryNode<>(value);
    }

    public BinaryTree() {
        root = null;
    }

    public void insert(T value) {
        if (root == null) {
            root = new BinaryNode<>(value);
            return;
        }
        insert(value, root);
    }

    private void insert(T value, BinaryNode<T> current) {
        if (current.isLeaf()) {
            if (value.compareTo(current.getValue()) < 0) {
                current.setLeft(new BinaryNode<>(value));
            } else current.setRight(new BinaryNode<>(value));
        } else if (value.compareTo(current.getValue()) < 0) {
            if (current.getLeft() != null) insert(value, current.getLeft());
            else current.setLeft(new BinaryNode<>(value));
            return;
        } else {
            if (current.getRight() != null) insert(value, current.getRight());
            else current.setRight(new BinaryNode<>(value));
            return;
        }
    }

    public String toString() {
        StringBuilder ok = new StringBuilder();
        if (root == null) return "null";
        toString(root, ok);
        return ok.toString();
    }

    private void toString(BinaryNode<T> current, StringBuilder ok) {
        if (current == null) {
            ok.append("-");
            return;
        }
        ok.append(" ");
        ok.append(current.getValue());
        ok.append(" ");
        toString(current.getLeft(), ok);
        toString(current.getRight(), ok);
    }

    public void reverse() {
        if (root == null) return;
        reverse(root);
    }

    private void reverse(BinaryNode<T> current) {
        if (current == null) return;
        BinaryNode<T> otto = current.getLeft();
        current.setLeft(current.getRight());
        current.setRight(otto);
        reverse(current.getLeft());
        reverse(current.getRight());
    }

    public void delete() {
        delete(root);
    }

    private void delete(BinaryNode<T> current) {
        if (current == null) return;
        if (current.isLeaf()) {
            current.setValue(null);
            return;
        }
        current.setValue(null);
        delete(current.getLeft());
        delete(current.getRight());
        current.setLeft(null);
        current.setRight(null);
    }

    public void printTree() {
        if (this.root == null) {
            System.out.println("-");
            return;
        }
        Stack<BinaryNode<T>> otto = new Stack<>();
        otto.push(root);
        while (!otto.isEmpty()) {
            BinaryNode<T> kettner = otto.pop();
            System.out.println(kettner.getValue());
            if (kettner.getRight() != null) otto.push(kettner.getRight());
            if (kettner.getLeft() != null) otto.push(kettner.getLeft());

        }
    }

    public void printBreadthFirst() {
        Queue<BinaryNode<T>> otto = new LinkedList<>();
        otto.offer(root);
        while (!otto.isEmpty()) {
            BinaryNode<T> current = otto.poll();
            if (current.getLeft() != null) otto.offer(current.getLeft());
            if (current.getRight() != null) otto.offer(current.getRight());
            System.out.println(current.getValue() + " ");
        }
    }

    public int maxHeight() {
        if (root == null) return 0;
        if (root.isLeaf()) return 1;
        return 1 + Math.max(maxHeight(root.getLeft()), maxHeight(root.getRight()));
    }

    private int maxHeight(BinaryNode<T> current) {
        if (current == null) return 0;
        if (current.isLeaf()) return 1;
        return 1 + Math.max(maxHeight(current.getLeft()), maxHeight(current.getRight()));
    }

    public void printInOrder() {
        if (root == null) System.out.println("-");
        StringBuilder ok = new StringBuilder();
        builderInsert(root.getLeft(), ok);
        ok.append(root.getValue());
        ok.append(" ");
        builderInsert(root.getRight(), ok);
        System.out.println(ok.toString());
    }

    private void builderInsert(BinaryNode<T> current, StringBuilder ok) {
        if (current == null) return;
        if (current.isLeaf()) {
            ok.append(current.getValue());
            ok.append(" ");
            return;
        }
        builderInsert(current.getLeft(), ok);
        ok.append(current.getValue());
        ok.append(" ");
        builderInsert(current.getRight(), ok);
    }
}
