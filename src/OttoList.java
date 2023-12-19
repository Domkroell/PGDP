public class OttoList {
    Otto first;
    Otto last;
    int size;

    public OttoList(){
    }
    public void add(Otto otto){
        size++;
        if(first==null){
            first = otto;
            last = otto;
            return;
        }
        last.setNext(otto);
        Otto tmp = last;
        last = last.getNext();
        last.setPrev(tmp);
    }

    public String toString(){
        Otto otto = first;
        StringBuilder ok = new StringBuilder();
        while(otto!=null){
            ok.append(otto.getValue());
            ok.append(" <-> ");
            otto = otto.getNext();
        }
        return ok.toString();
    }
    public void reverse(){
        Otto small = null;
        Otto big = first;
        last = first;
        while(big!=null){
            first = big;
            Otto tmp = big.getNext();
            big.setNext(small);
            big.setPrev(tmp);
            small = big;
            big = tmp;
        }
    }
}
