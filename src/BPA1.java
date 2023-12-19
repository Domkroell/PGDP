import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BPA1 {
    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 1, 2, 5, 5, 7, 8, 8, 8};
        int[] b = new int[]{221, Integer.MIN_VALUE, 8, 4, 2, 6, 5, 4};
        System.out.println(Arrays.toString(toAscending(new int[]{1, 4, 1, 221, 3, 1})));
        System.out.println(greatestCommonElement(a, b));

        int[][] fieldPoints = new int[][]{{1, 2, 3}, {4, 5, 6}};
        int[][] hits = new int[][]{{1, 0, 2}, {0, 1, 0}};
        System.out.println(pinguballPoints(fieldPoints, hits));
        System.out.println(penguNum(40));
        System.out.println(isPenguNumPositive(penguNum(40)));
        System.out.println(penguNumIndex(penguNum(-40)));
        System.out.println(isPenguNum(penguNum(-50)));
    }

    public static int greatestCommonElement(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int ergebnis = -1;
        boolean found = false;
        for (int i = a.length - 1; i >= 0; i--) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j]) {
                    ergebnis = a[i];
                    found = true;
                    break;
                }
                if (found) break;
            }
        }
        return ergebnis;
    }

    public static int[] toAscending(int[] a) {
        if (a.length <= 1) return a;
        ArrayList<Integer> otto = new ArrayList<>();
        otto.add(a[0]);
        for (int i = 1; i < a.length; i++) {
            if (a[i] > otto.get(otto.size() - 1)) otto.add(a[i]);
        }
        if (!otto.contains(a[a.length - 1])) {
            if (otto.get(otto.size() - 1) < a[a.length - 1]) otto.add(a[a.length - 1]);
        }
        int[] ergebnis = new int[otto.size()];
        for (int i = 0; i < ergebnis.length; i++) {
            ergebnis[i] = otto.get(i);
        }
        return ergebnis;
    }

    public static int numberOfTeams(char[][] names) {
        HashMap<Character, Integer> otto = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            if (names[i].length != 0) {
                if (otto.containsKey(names[i][0])) {
                    otto.put(names[i][0], otto.get(names[i][0] + 1));
                } else otto.put(names[i][0], 1);
            }
        }
        int number = 0;
        for (Character key : otto.keySet()) number++;
        return number;
    }

    public static int pinguballPoints(int[][] fieldPoints, int[][] hits) {
        int normalPoints = 0;
        int würfe = 0;
        for (int i = 0; i < fieldPoints.length; i++) {
            for (int j = 0; j < fieldPoints[i].length; j++) {
                if (hits[i][j] != 0) {
                    normalPoints += fieldPoints[i][j];
                    würfe += hits[i][j];
                }
            }
        }
        if (würfe == 4) normalPoints += 10;
        for (int i = 0; i < fieldPoints.length; i++) {
            normalPoints += rowHits(fieldPoints, hits, i);
        }
        for (int i = 0; i < fieldPoints[0].length; i++) {
            normalPoints += columnHits(fieldPoints, hits, i);
        }
        return normalPoints;
    }

    private static int rowHits(int[][] fieldPoints, int[][] hits, int row) {
        boolean isTrue = true;
        for (int i = 0; i < fieldPoints[row].length; i++) {
            if (hits[row][i] == 0) {
                isTrue = false;
                break;
            }
        }
        if (isTrue) return 5;
        else return 0;
    }

    private static int columnHits(int[][] fieldPoints, int[][] hits, int column) {
        boolean isTrue = true;
        for (int i = 0; i < fieldPoints.length; i++) {
            if (hits[i][column] == 0) {
                isTrue = false;
                break;
            }
        }
        if (isTrue) return 5;
        else return 0;
    }

    public static boolean isOnOrInsideCircle(int r, int x, int y) {
        double distance = Math.sqrt(x * x + y * y);
        return distance <= r;
    }

    public static boolean isInRedArea(int x, int y) {
        if (!isOnOrInsideCircle(200, x, y)) return false;
        if (x > 0 && x < 100 && y > 0 && y < 100) return false;
        if (x < 0 && x > -100 && y < 0 && y > -100) return false;
        return true;
    }

    public static int countPointsInCircle(int n) {
        int points = 0;
        for (int i = n; i >= -n; i--) {
            for (int j = -n; j <= n; j++) {
                if (isOnOrInsideCircle(n, j, i)) points++;
            }
        }
        return points;
    }

    public static double approximatePi(int n) {
        double red = countPointsInCircle(n);
        double dots = (n * 2 + 1) * (n * 2 + 1);
        return (red / dots) * 4;
    }

    public static long penguNumPositive(int n) {
        if (n == 0) return 0;
        if (n <= 2) return 1;
        long first = 0;
        long second = 1;
        long third = 1;
        long ergebnis = 0;
        int index = 2;
        while (index < n) {
            ergebnis = third + second + first;
            first = second;
            second = third;
            third = ergebnis;
            index++;
        }
        return ergebnis;
    }

    public static long penguNumNegative(int n) {
        long num = penguNumPositive(Math.abs(n));
        if (n % 2 == 0) num = -num;
        return num;
    }

    public static long penguNum(int n) {
        if (n < 0) return penguNumNegative(n);
        else return penguNumPositive(n);
    }

    public static int penguNumIndex(long n) {
        if (n == 1) return 1;
        if (n == 0) return 0;
        if (n == -1) return -2;
        if (n == 2) return 3;
        long num = Math.abs(n);
        ArrayList<Long> otto = new ArrayList<>();
        otto.add(0L);
        otto.add(1L);
        otto.add(1L);
        while (otto.get(otto.size() - 1) != num) {
            long first = otto.get(otto.size() - 3);
            long second = otto.get(otto.size() - 2);
            long third = otto.get(otto.size() - 1);
            otto.add(first + second + third);
        }
        int ergebnis = otto.size() - 1;
        if (n < 0) {
            ergebnis = -ergebnis;
        }
        return ergebnis;
    }

    public static boolean isPenguNumPositive(long n) {
        if (n == 0 || n == 1 || n == 2) return true;
        ArrayList<Long> otto = new ArrayList<>();
        otto.add(0L);
        otto.add(1L);
        otto.add(1L);
        while (otto.get(otto.size() - 1) < n) {
            long first = otto.get(otto.size() - 3);
            long second = otto.get(otto.size() - 2);
            long third = otto.get(otto.size() - 1);
            otto.add(first + second + third);
        }
        if (otto.get(otto.size() - 1) == n) return true;
        return false;
    }

    public static boolean isPenguNum(long n) {
        long num = Math.abs(n);
        return isPenguNumPositive(num);
    }
}
