import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int[]otto = new int[]{65,23,325,25,57453,2151,452362,21,-734563};
        mergeSort(otto);
        System.out.println(Arrays.toString(otto));
    }

//    public static int[]mergeSort(int[]a){
//        if(a==null)return null;
//        if(a.length==0||a.length==1)return a;
//        int mid = a.length/2;
//        int[]c = sortHelper(Arrays.copyOfRange(a, mid, a.length));
//        int[]b = sortHelper(Arrays.copyOfRange(a, 0, mid));
//        return merge(b,c);
//    }
//    private static int[]sortHelper(int[]c){
//        if(c.length==1)return c;
//        int mid = c.length/2;
//        int[]b = sortHelper(Arrays.copyOfRange(c, mid, c.length));
//        int[]a = sortHelper(Arrays.copyOfRange(c, 0, mid));
//        return merge(a,b);
//    }
//    private static int[]merge(int[]a, int[]b){
//        int first = 0;
//        int second = 0;
//        int third = 0;
//        int[]ergebnis = new int[a.length+b.length];
//        while(first<a.length&&second<b.length){
//            if(a[first]<=b[second])ergebnis[third++]=a[first++];
//            else ergebnis[third++]=b[second++];
//        }
//        while(first<a.length)ergebnis[third++]=a[first++];
//        while(second<b.length)ergebnis[third++]=b[second++];
//        return ergebnis;
//    }
    public static void mergeSort(int[]a){
        if(a==null)return;
        if(a.length==0||a.length==1)return;
        mergeSort(a, 0, a.length);
    }
    private static void mergeSort(int[]a, int start, int end){
        if(end-start==1)return;
        int mid = (end+start)/2;
        mergeSort(a, start, mid);
        mergeSort(a, mid, end);
        merge(a, start, end);
    }
    private static void merge(int[]a, int start, int end){
        int mid = (end+start)/2;
        int[]ergebnis = Arrays.copyOf(a, a.length);
        int first = start;
        int second = mid;
        int third = start;
        while(first<mid&&second<end){
            if(a[first]<=a[second])ergebnis[third++]=a[first++];
            else ergebnis[third++]=a[second++];
        }
        while(first<mid)ergebnis[third++]=a[first++];
        while(second<end)ergebnis[third++]=a[second++];

        for (int i = 0; i < ergebnis.length; i++) {
            a[i]=ergebnis[i];
        }
    }
}