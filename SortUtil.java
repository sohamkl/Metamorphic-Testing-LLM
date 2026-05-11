import java.util.Arrays;

public class SortUtil {
    // Returns a new sorted array (ascending); original array unchanged.
    public static int[] sortArray(int[] arr) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        return copy;
    }

    public static void main(String[] args) {
        int[] a = {3, 1, 10, 2};
        System.out.println(Arrays.toString(sortArray(a))); // [1, 2, 3, 10]
        System.out.println(Arrays.toString(a));            // [3, 1, 10, 2]
    }
}
