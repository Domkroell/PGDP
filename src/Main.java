import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    }

    public static int longestPalindrome(String s) {
        HashMap<Character, Integer> otto = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (otto.containsKey(c)) {
                otto.put(c, otto.get(c) + 1);
            } else otto.put(c, 1);
        }
        int evenSums = 0;
        boolean hasBeenTaken = false;
        for (Integer value : otto.values()) {
            if (value % 2 == 0) evenSums += value;
            else {
                if (hasBeenTaken == false) {
                    evenSums++;
                    hasBeenTaken = true;
                }
                evenSums += value - 1;
            }
        }

        return evenSums;
    }

    public List<String> fizzBuzz(int n) {
        List<String> ergebnis = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) ergebnis.add("FizzBuzz");
            else if (i % 3 == 0) ergebnis.add("Fizz");
            else if (i % 5 == 0) ergebnis.add("Buzz");
            else ergebnis.add(Integer.toString(i));
        }
        return ergebnis;
    }

    public String destCity(List<List<String>> paths) {
        for (int i = 0; i < paths.size(); i++) {
            List<String> current = paths.get(i);
            String city = current.get(1);
            if (hasNoOther(city, paths)) return city;
        }
        return null;
    }

    private boolean hasNoOther(String city, List<List<String>> paths) {
        for (int i = 0; i < paths.size(); i++) {
            if (paths.get(i).get(0).equals(city)) return false;
        }
        return true;
    }

    public int maxProduct(int[] nums) {
        int maxPro = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                maxPro = Math.max(maxPro, (nums[i] - 1) * (nums[j] - 1));
            }
        }
        return maxPro;
    }

    public int minTimeToVisitAllPoints(int[][] points) {
        int moves = 0;
        for (int i = 0; i < points.length - 1; i++) {
            moves += goMoves(points[i], points[i + 1], 0);
        }
        return moves;
    }

    private int goMoves(int[] first, int[] second, int moves) {
        while (first[0] < second[0] && first[1] < second[1]) {
            first[0]++;
            first[1]++;
            moves++;
        }
        while (first[0] > second[0] && first[1] > second[1]) {
            first[0]--;
            first[1]--;
            moves++;
        }
        while (first[0] > second[0] && first[1] < second[1]) {
            first[0]--;
            first[1]++;
            moves++;
        }
        while (first[0] < second[0] && first[1] > second[1]) {
            first[0]++;
            first[1]--;
            moves++;
        }
        while (first[0] < second[0]) {
            first[0]++;
            moves++;
        }
        while (first[0] > second[0]) {
            first[0]--;
            moves++;
        }
        while (first[1] < second[1]) {
            first[1]++;
            moves++;
        }
        while (first[1] > second[1]) {
            first[1]--;
            moves++;
        }
        return moves;
    }

    public static boolean[][] removeIslands(boolean[][] otto) {
        if (otto == null) return null;
        if (otto.length == 1 || otto[0].length == 1) return otto;
        boolean[][] kettner = new boolean[otto.length][otto[0].length];
        for (int i = 0; i < otto[0].length; i++) {
            addIslands(i, 0, otto, kettner);
            addIslands(i, otto.length - 1, otto, kettner);
        }
        for (int i = 0; i < otto.length; i++) {
            addIslands(0, i, otto, kettner);
            addIslands(otto[0].length - 1, i, otto, kettner);
        }
        return kettner;
    }

    public static void addIslands(int x, int y, boolean[][] otto, boolean[][] kettner) {
        if (x < 0 || x >= otto[0].length || y < 0 || y >= otto.length) return;
        if (!otto[y][x]) return;
        kettner[y][x] = true;
        otto[y][x] = false;
        addIslands(x + 1, y, otto, kettner);
        addIslands(x - 1, y, otto, kettner);
        addIslands(x, y + 1, otto, kettner);
        addIslands(x, y - 1, otto, kettner);
    }

    public boolean makeEqual(String[] words) {
        HashMap<Character, Integer> otto = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                char key = words[i].charAt(j);
                if (otto.containsKey(key)) {
                    otto.put(key, otto.get(key) + 1);
                } else otto.put(key, 1);

            }
        }
        int length = words.length;
        for (Integer value : otto.values()) {
            if (value % length != 0) return false;
        }
        return true;
    }

    public int[] findPeakGrid(int[][] mat) {
        return goFind(mat[0].length / 2, mat.length / 2, mat);
    }

    public int[] goFind(int x, int y, int[][] mat) {
        if (x < 0 || x >= mat[0].length || y < 0 || y >= mat.length) return null;
        if (mat[y][x] == -1) return null;
        if (mat[y + 1][x] < mat[y][x] && mat[y - 1][x] < mat[y][x] && mat[y][x + 1] < mat[y][x] && mat[y][x - 1] < mat[y][x])
            return new int[]{y, x};
        int[] otto;
        if (mat[y + 1][x] > mat[y][x]) {
            otto = goFind(x, y + 1, mat);
            if (otto != null) return otto;
        }
        if (mat[y - 1][x] > mat[y][x]) {
            otto = goFind(x, y - 1, mat);
            if (otto != null) return otto;
        }
        if (mat[y][x + 1] > mat[y][x]) {
            otto = goFind(x + 1, y, mat);
            if (otto != null) return otto;
        }
        if (mat[y][x - 1] > mat[y][x]) {
            otto = goFind(x - 1, y, mat);
            if (otto != null) return otto;
        }
        return null;
    }

    public static int[] mergeSort(int[] otto) {
        if (otto == null) return null;
        if (otto.length == 0) return otto;
        if (otto.length == 1) return otto;
        int[] links = new int[otto.length / 2];
        for (int i = 0; i < links.length; i++) {
            links[i] = otto[i];
        }
        int[] rechts = new int[otto.length - links.length];
        int index = links.length;
        for (int i = 0; i < rechts.length; i++) {
            rechts[i] = otto[index++];
        }
        links = mergeSort(links);
        rechts = mergeSort(rechts);
        return merge(links, rechts);
    }

    public static int[] merge(int[] links, int[] rechts) {
        int[] ergebnis = new int[links.length + rechts.length];
        int left = 0;
        int right = 0;
        int ganz = 0;
        while (left < links.length && right < rechts.length) {
            if (links[left] <= rechts[right]) {
                ergebnis[ganz++] = links[left++];
            } else ergebnis[ganz++] = rechts[right++];
        }
        while (left < links.length) {
            ergebnis[ganz++] = links[left++];
        }
        while (right < rechts.length) {
            ergebnis[ganz++] = rechts[right++];
        }
        return ergebnis;
    }

    public static String[] permutations(String up) {
        if (up.isEmpty()) return new String[0];
        ArrayList<String> otto = new ArrayList<>();
        permutations("", up, otto);
        String[] ergebnis = new String[otto.size()];
        for (int i = 0; i < ergebnis.length; i++) {
            ergebnis[i] = otto.get(i);
        }
        return ergebnis;
    }

    private static void permutations(String p, String up, ArrayList<String> otto) {
        if (up.isEmpty()) {
            otto.add(p);
            return;
        }
        String insert = up.substring(0, 1);
        for (int i = 0; i <= p.length(); i++) {
            String first = p.substring(0, i);
            String second = p.substring(i);
            permutations(first + insert + second, up.substring(1), otto);
        }
    }


    public static int[][] permutations(int[] a) {
        ArrayList<int[]> otto = new ArrayList<>();
        helperPermutations(new int[0], a, otto);
        int[][] ergebnis = new int[otto.size()][a.length];
        for (int i = 0; i < otto.size(); i++) {
            ergebnis[i] = otto.get(i);
        }
        return ergebnis;
    }

    private static void helperPermutations(int[] p, int[] up, ArrayList<int[]> otto) {
        if (up.length == 0) {
            otto.add(p);
            return;
        }
        int insert = up[0];
        for (int i = 0; i <= p.length; i++) {
            int[] links = Arrays.copyOfRange(p, 0, i);
            int[] rechts = Arrays.copyOfRange(p, i, p.length);
            int[] neu = new int[links.length + rechts.length + 1];
            int neuIndex = 0;
            for (int j = 0; j < links.length; j++) {
                neu[neuIndex++] = links[j];
            }
            neu[neuIndex++] = insert;
            for (int j = 0; j < rechts.length; j++) {
                neu[neuIndex++] = rechts[j];
            }
            helperPermutations(neu, Arrays.copyOfRange(up, 1, up.length), otto);
        }
    }


    public static int[][] potenzMenge(int[] a) {
        ArrayList<int[]> otto = new ArrayList<>();
        helperPotenzMenge(new int[0], a, otto);
        int[][] ergebnis = new int[otto.size()][a.length];
        for (int i = 0; i < otto.size(); i++) {
            ergebnis[i] = otto.get(i);
        }
        return ergebnis;
    }

    private static void helperPotenzMenge(int[] p, int[] up, ArrayList<int[]> otto) {
        if (up.length == 0) {
            otto.add(p);
            return;
        }

        int insert = up[0];
        int[] neuP = new int[p.length + 1];
        for (int i = 0; i < p.length; i++) {
            neuP[i] = p[i];
        }
        neuP[neuP.length - 1] = insert;

        helperPotenzMenge(neuP, Arrays.copyOfRange(up, 1, up.length), otto);
        helperPotenzMenge(p, Arrays.copyOfRange(up, 1, up.length), otto);
    }
}
