public class Otto {
    private int value;
    private Otto prev;
    private Otto next;

    public Otto(int value){
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setPrev(Otto prev) {
        this.prev = prev;
    }

    public void setNext(Otto next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public Otto getPrev() {
        return prev;
    }

    public Otto getNext() {
        return next;
    }
}
