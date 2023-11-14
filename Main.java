import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
    }


    //sortiert Array auf- oder absteigend
    public static int[] basicSort(int[] eingabe, int nullabeinsauf) {
        if (eingabe.length == 0) return eingabe;
        int[] ergebnis = new int[eingabe.length];
        ergebnis[0] = eingabe[0];
        if (nullabeinsauf == 1) {
            //für jedes element von eingabe ab 1
            for (int i = 1; i < eingabe.length; i++) {

                if (eingabe[i] > ergebnis[i - 1]) {
                    ergebnis[i] = eingabe[i];
                }
                //links ist größer
                else {
                    int index = i;
                    while (eingabe[i] < ergebnis[index - 1]) {
                        ergebnis[index] = ergebnis[index - 1];
                        ergebnis[index - 1] = eingabe[i];
                        index--;
                        if (index == 0) break;
                    }
                }
            }

        } else if (nullabeinsauf == 0) {
            for (int i = 1; i < eingabe.length; i++) {

                if (eingabe[i] < ergebnis[i - 1]) {
                    ergebnis[i] = eingabe[i];
                }
                //links ist größer
                else {
                    int index = i;
                    while (eingabe[i] > ergebnis[index - 1]) {
                        ergebnis[index] = ergebnis[index - 1];
                        ergebnis[index - 1] = eingabe[i];
                        index--;
                        if (index == 0) break;
                    }
                }
            }
        }
        return ergebnis;
    }

    //binarySearch in Array
    public static int advancedSearch(int[] eingabe, int zahl) {
        // {-1,0,1,2,3,4,5}
        if (eingabe.length == 0) return 0;
        int ergebnisIndex = eingabe.length / 2;
        if (eingabe[ergebnisIndex] == zahl) return ergebnisIndex;

        int neuesLinks = 0;
        int neuesRechts = eingabe.length-1;
        while (eingabe[ergebnisIndex] != zahl) {
            if (ergebnisIndex == 0 || ergebnisIndex == eingabe.length) return -1;
            else if (eingabe[ergebnisIndex - 1] >= zahl) {
                neuesRechts = ergebnisIndex;
                ergebnisIndex = (ergebnisIndex + neuesLinks) / 2;
            } else if (eingabe[ergebnisIndex + 1] <= zahl) {
                neuesLinks = ergebnisIndex;
                ergebnisIndex = (ergebnisIndex + neuesRechts) / 2;

            }
        }

        return ergebnisIndex;

        //sucht den Index einer Zahl
        //fängt in der Mitte an
        //wenn links größer, dann mitte links
        //wenn rechts kleiner, dann mitte rechts
        //wenn rechts/links nur eine Zahl, dann die Zahl
        //wenn index = 0 oder index = länge-1 ist, dann return -1
    }

    //schaut ob number = power^x
    public static boolean isPowerOf(long number, int power) {
        if (power == 0) return false;
        if (number == 0) return false;
        while (true) {
            long temp = number;
            number = number / power;
            if (number * power != temp) return false;
            else if (number == 1) {
                return true;
            }
        }
    }

    //gibt true zurück, wenn eine Zahl in einem Array ist
    public static boolean isContainedInArray(int[] array, int number) {
        for (int i = 0; i < array.length; i++) {
            if (number == array[i]) return true;
        }
        return false;
    }

    //zusammen mit isContainedInArray
    //gibt den Index einer gesuchten Nummer im Array
    public static int findNumberIndexInArray(int[] array, int number) {
        if (!isContainedInArray(array, number)) return -1;
        int returnVariable = 0;
        for (int i = 0; i < array.length; i++) {
            if (number == array[i]) {
                returnVariable = i;
                break;
            }
        }
        return returnVariable;
    }

    //gibt an, wie oft eine Zahl in einem Array vorkommt
    public static int numberOccurenceInArray(int[] array, int number) {
        if (array.length == 0) return 0;
        int zahl = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) zahl++;
        }
        return zahl;
    }

    //zusammen mit differentNumbersInArray & numberOccurenceInArray
    //gibt einen 2D-Array mit allen Zahlen und deren Vorkommen zurück
    public static int[][] getAllNumberOccurencesinArray(int[] array) {
        if (array.length == 0) {
            int[][] ok = new int[0][];
            return ok;
        }

        int[] zwischen = differentNumbersInArray(array);

        Arrays.sort(zwischen);

        int[][] ergebnis = new int[zwischen.length][2];
        for (int i = 0; i < zwischen.length; i++) {
            ergebnis[i][0] = zwischen[i];
            ergebnis[i][1] = numberOccurenceInArray(array, ergebnis[i][0]);
        }
        return ergebnis;
    }

    //zusammen mit isContainedInArray
    //gibt einen Array zurück, indem sich nur unterschiedliche Zahlen befinden
    public static int[] differentNumbersInArray(int[] array) {
        if (array.length == 0) return array;
        int[] zwischen = new int[array.length];
        zwischen[0] = array[0];
        int index = 1;
        for (int i = 1; i < array.length; i++) {
            if (!isContainedInArray(zwischen, array[i])) {
                zwischen[index] = array[i];
                index++;
            }
        }
        int[] ergebnis = new int[index];
        for (int i = 0; i < ergebnis.length; i++) {
            ergebnis[i] = zwischen[i];
        }
        return ergebnis;
    }


    //source: https://bitbucket.ase.in.tum.de/projects/PGDP2324W03H01/repos/pgdp2324w03h01-solution/browse
    //schaut, ob eine Zahl eine Primzahl ist
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    //source: https://bitbucket.ase.in.tum.de/projects/PGDP2324W03H01/repos/pgdp2324w03h01-solution/browse
    //berechnet die Summe aller Quadrate in einem Array ohne Long Overflow
    public static long sumOfSquares(int[] array) {
        long sumOfSquares = 0;
        for (int i = 0; i < array.length; i++) {
            long square = (long) array[i] * (long) array[i];
            if (Long.MAX_VALUE - sumOfSquares < square) {
                System.out.println("Overflow!");
                return -1;
            }

            sumOfSquares += square;
        }

        return sumOfSquares;
    }

    //source: https://bitbucket.ase.in.tum.de/projects/PGDP2324W03H01/repos/pgdp2324w03h01-solution/browse
    //braucht Math library
    //macht zwei Arrays zu einem großen Array zusammen in Abwechslung
    public static int[] zip(int[] a, int[] b) {
        int[] zippedArray = new int[a.length + b.length];
        int minLength = Math.min(a.length, b.length);

        for (int i = 0; i < minLength; i++) {
            zippedArray[2 * i] = a[i];
            zippedArray[2 * i + 1] = b[i];
        }

        if (a.length < b.length) {
            for (int i = minLength; i < b.length; i++) {
                zippedArray[minLength + i] = b[i];
            }
        } else {
            for (int i = minLength; i < a.length; i++) {
                zippedArray[minLength + i] = a[i];
            }
        }

        return zippedArray;
    }

    //source: https://bitbucket.ase.in.tum.de/projects/PGDP2324W03H01/repos/pgdp2324w03h01-solution/browse
    //zippt einen 2D-Array zusammen
    public static int[] zipMany(int[][] arrays) {
        int maxLength = 0;
        int sumOfLengths = 0;
        for (int i = 0; i < arrays.length; i++) {
            sumOfLengths += arrays[i].length;
            if (arrays[i].length > maxLength) {
                maxLength = arrays[i].length;
            }
        }

        int[] zippedArray = new int[sumOfLengths];
        int nextPosition = 0;
        for (int j = 0; j < maxLength; j++) {
            for (int i = 0; i < arrays.length; i++) {
                if (j < arrays[i].length) {
                    zippedArray[nextPosition] = arrays[i][j];
                    nextPosition++;
                }
            }
        }

        return zippedArray;
    }

    //source: https://bitbucket.ase.in.tum.de/projects/PGDP2324W03H01/repos/pgdp2324w03h01-solution/browse
    //rotiert einen Array
    public static void rotate(int[] array, int amount) {
        int length = array.length;

        if (length == 0) {
            return;
        }

        // Get rotation amount into range [0, array.length - 1]
        amount = amount % length;
        if (amount < 0) {
            amount += length;
        }

        // Rotate into new array
        int[] rotated = new int[length];
        for (int i = 0; i < length; i++) {
            rotated[(i + amount) % length] = array[i];
        }

        // Copy from this new array back into old one
        for (int i = 0; i < length; i++) {
            array[i] = rotated[i];
        }
    }

    //gibt alle n Fibonaccizahlen zurück
    public static int[] fibonacciArray(int n){
        if(n<=0)return new int[0];
        if(n==1)return new int[]{1};
        int[] fibonacciArray = new int[n];
        fibonacciArray[0]=1;
        fibonacciArray[1]=1;
        for (int i = 2; i < n; i++) {
            fibonacciArray[i]=fibonacciArray[i-1]+fibonacciArray[i-2];
        }
        return fibonacciArray;
    }

    public static int[][]multiplyMatrices(int[][]a, int[][]b) {
        if (a[0].length != b.length) return new int[0][0];
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != a[0].length) return new int[0][0];
        }
        for (int i = 0; i < b.length; i++) {
            if (b[0].length != b[i].length) return new int[0][0];
        }
        if (a.length == 0 || b.length == 0) return new int[0][0];
        //test cases


        int[][] ergebnis = new int[a.length][b[0].length];

        //multiply rows in a with colums of b
        //for each row of a
        for (int i = 0; i < a.length; i++) {
            //multiply the row in a with all colums of b
            //for each column of b
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    ergebnis[i][j] = ergebnis[i][j] + a[i][k] * b[k][j];
                }
            }
        }
        return ergebnis;
    }

    //zusammen mit getAllNumerOccurencesInArray
    //maximale Distanz zwischen zwei gleichen Zahlen
    public static int maxDistance(int[]a){
        if(a.length<=1)return -1;
        //finde alle elemente, die mind. 2 mal vorkommen
        int[][]nummerVorkommen = getAllNumberOccurencesinArray(a);
        boolean weitermachen = true;
        for (int i = 0; i < nummerVorkommen.length; i++) {
            if(nummerVorkommen[i][1]!=1)weitermachen=false;

        }
        if(weitermachen)return -1;

        int biggestSpace = 0;
        for (int i = 0; i < nummerVorkommen.length; i++) {
            for (int j = 0; j <= a.length/2; j++) {
                int parallelIndex=a.length-1-j;
                if(parallelIndex-j>biggestSpace && a[j]==a[parallelIndex]){
                    biggestSpace = parallelIndex-j;
                }
            }
        }
        return biggestSpace;
    }

    public static double median(int[]a){
        if(a.length==0)return -1;
        if(a.length==1)return a[0];
        Arrays.sort(a);
        if(a.length%2!=0){
            return a[a.length/2];
        }
        else{
            int mitte = a.length/2;
            return ((double)a[mitte]+(double)a[mitte-1])/2;
        }
    }

    public static int[] bubbleSort(int[]a){
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length-i-1; j++) {
                if(a[j]>a[j+1]){
                    int tmp = a[j];
                    a[j]=a[j+1];
                    a[j+1]=tmp;
                }
            }
        }
        return a;
    }

    public static boolean isPalindrome(String wort){
        String verkehrt = "";
        wort = wort.toLowerCase();
        for (int i = 0; i < wort.length(); i++) {
            verkehrt = verkehrt + wort.charAt(wort.length()-1-i);
        }

        for (int i = 0; i < wort.length(); i++) {
            if(verkehrt.charAt(i)!=wort.charAt(i))return false;
        }
        return true;
    }

    //man braucht ArrayList dafür
    //gibt einen Array mit allen Primfaktoren einer Zahl zurück
    public static int[] primeFactorization(int number){
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= number; i++) {
            while(number%i==0){
                primes.add(i);
                number = number/i;
            }
        }
        int[]primeFactors = new int[primes.size()];
        for (int i = 0; i < primeFactors.length; i++) {
            primeFactors[i]=primes.get(i);
        }
        return primeFactors;
    }

    public static boolean isAnagram(String wort1, String wort2){
        if(wort1.length()!=wort2.length())return false;
        wort1 = wort1.toLowerCase();
        wort2 = wort2.toLowerCase();
        char[]wort1Chars = wort1.toCharArray();
        char[]wort2Chars = wort2.toCharArray();
        Arrays.sort(wort1Chars);
        Arrays.sort(wort2Chars);
        for (int i = 0; i < wort1Chars.length; i++) {
            if(wort1Chars[i]!=wort2Chars[i])return false;
        }
        return true;
    }

    //gibt die n-te fibonaccizahl zurück
    public static int fibonacciZahl(int n){
        if(n<=0)return -1;
        if(n==1)return 0;
        if(n==2)return 1;
        int defaultLinks = 0;
        int defaultRechts = 1;
        int ergebnis=1;
        for (int i = 3; i <= n; i++) {
            defaultLinks = defaultRechts;
            defaultRechts = ergebnis;
            ergebnis = defaultLinks + defaultRechts;

        }

        return ergebnis;
    }

    //Zusammen mit isPalindrome und charArrayToString
    public static String longestPalindromeInSubstring(String eingabe){
        if(eingabe.isEmpty())return eingabe;
        if(eingabe.length()==1)return eingabe;

        char[]zwischen = eingabe.toCharArray();
        String ergebnis ="";
        String dazwischen = "";

        for (int i = 0; i < zwischen.length; i++) {
            for (int j = zwischen.length-1; j >= i; j--) {
                dazwischen = charArrayToString(zwischen, i, j);
                if(isPalindrome(dazwischen)){
                    if(dazwischen.length()>=ergebnis.length()){
                        ergebnis = dazwischen;
                    }
                }
            }
        }

        return ergebnis;
    }

    public static String charArrayToString(char[]array, int startIndex, int inclusiveEndIndex){
        String ergebnis = "";
        for (int i = startIndex; i <= inclusiveEndIndex; i++) {
            ergebnis = ergebnis + array[i];
        }
        return ergebnis;
    }

    //benutzt java.util.Scanner
    public static void checkIfNumberFitsInDatatypes(){
        Scanner ok = new Scanner(System.in);
        try{
            long number = ok.nextLong();
            if(number<=Byte.MAX_VALUE&& number >=Byte.MIN_VALUE){
                System.out.println(number + " fits in Byte.");
            }
            if(number<=Short.MAX_VALUE&& number >=Short.MIN_VALUE){
                System.out.println(number + " fits in Short.");
            }
            if(number<=Integer.MAX_VALUE&& number >=Integer.MIN_VALUE){
                System.out.println(number + " fits in Integer.");
            }
            if(number<=Long.MAX_VALUE&& number >=Long.MIN_VALUE){
                System.out.println(number + " fits in Long.");
            }

        }catch(Exception e){
            System.out.println("This number does not fit in any Datatype!");
        }
    }
    public static boolean containsSubString(String eingabe, String substring){
        if(eingabe.equals(substring))return true;
        if(substring.isEmpty())return true;
        int index = 0;
        for (int i = 0; i <= eingabe.length()-substring.length(); i++) {
            index = 0;
            for (int j = i; j < substring.length()+i; j++) {
                if(eingabe.charAt(j)!=substring.charAt(index))break;
                index++;
                if(index == substring.length())return true;
            }
        }
        return false;
    }

    public static long twoSum(int[]eingabe){
        if(eingabe.length<2)return -1;
        long ergebnis = eingabe[0]+eingabe[1];
        for (int i = 0; i < eingabe.length-1; i++) {
            for (int j = i+1; j < eingabe.length; j++) {
                if((long)eingabe[i]+(long)eingabe[j]>ergebnis)ergebnis = (long)eingabe[i]+(long)eingabe[j];
            }
        }
        return ergebnis;
    }

    public static int[]removeNumberInArray(int[]eingabe, int zahl){
        int zahlen = 0;

        for (int i = 0; i < eingabe.length; i++) {
            if(eingabe[i]==zahl)zahlen++;
        }
        int[]ergebnis = new int[eingabe.length-zahlen];
        int ergebnisIndex = 0;
        for (int i = 0; i < eingabe.length; i++) {
            if(eingabe[i]!=zahl){
                ergebnis[ergebnisIndex]=eingabe[i];
                ergebnisIndex++;
            }
        }
        return ergebnis;
    }

    public static int[]removeNumbersLessThanInArray(int[]eingabe, int zahl){
        int zahlen = 0;

        for (int i = 0; i < eingabe.length; i++) {
            if(eingabe[i]<zahl)zahlen++;
        }
        int[]ergebnis = new int[eingabe.length-zahlen];
        int ergebnisIndex = 0;
        for (int i = 0; i < eingabe.length; i++) {
            if(eingabe[i]>=zahl){
                ergebnis[ergebnisIndex]=eingabe[i];
                ergebnisIndex++;
            }
        }
        return ergebnis;
    }
}
