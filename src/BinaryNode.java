public class BinaryNode<T extends Comparable<T>>{
    private T value;
    private BinaryNode<T>left;
    private BinaryNode<T>right;
    public BinaryNode(T value){
        this.value = value;
        left = null;
        right = null;
    }

    public boolean isLeaf(){
        return left==null && right == null;
    }
    public void setValue(T value) {
        this.value = value;
    }

    public void setLeft(BinaryNode<T> left) {
        this.left = left;
    }

    public void setRight(BinaryNode<T> right) {
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public BinaryNode<T> getLeft() {
        return left;
    }

    public BinaryNode<T> getRight() {
        return right;
    }
}
