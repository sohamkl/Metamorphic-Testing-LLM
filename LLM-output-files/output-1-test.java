import java.util.Arrays;

/**
 
Write metamorphic tests for the Java class `SortUtil` and its method `sortArray(int[] arr)`, which returns a new ascending-sorted copy and leaves the input unchanged.

Requirements:
- Target: `public static int[] sortArray(int[] arr)` in `SortUtil` (uses `Arrays.copyOf` then `Arrays.sort` on the copy).
- Use metamorphic testing: define relations between inputs and expected outputs (e.g. permutation of input yields same sorted result; sorting twice equals sorting once; concatenating sorted halves and re-sorting; scaling/shifting properties if you state them clearly and only where they hold for ascending integer sort).
- Output complete, runnable Java test code (JUnit 5 preferred; if no JUnit, use plain `main` with assertions or manual checks with clear pass/fail).
- Cover edge cases: empty array, single element, duplicates, negatives, already sorted, reverse sorted.
- Do not modify `SortUtil`; only produce the test class(es) and brief comments naming each metamorphic relation.

 */
class Output1Test {

    private static void assertArrayEquals(String message, int[] expected, int[] actual) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError(
                    message + ": expected " + Arrays.toString(expected) + ", got " + Arrays.toString(actual));
        }
    }

    /** Concatenate sorted left and sorted right halves (MR: re-sort equals sort of whole). */
    private static int[] concatenated(int[] input) {
        int mid = input.length / 2;
        int[] left = Arrays.copyOfRange(input, 0, mid);
        int[] right = Arrays.copyOfRange(input, mid, input.length);
        int[] leftSorted = SortUtil.sortArray(left);
        int[] rightSorted = SortUtil.sortArray(right);
        int[] out = new int[leftSorted.length + rightSorted.length];
        System.arraycopy(leftSorted, 0, out, 0, leftSorted.length);
        System.arraycopy(rightSorted, 0, out, leftSorted.length, rightSorted.length);
        return out;
    }

    static void sortArrayEmptyArray() {
        int[] input = {};
        int[] expected = {};
        int[] sortedInput = SortUtil.sortArray(input);
        assertArrayEquals("sortArrayEmptyArray", expected, sortedInput);
    }

    static void sortArraySingleElement() {
        int[] input = {5};
        int[] expected = {5};
        int[] sortedInput = SortUtil.sortArray(input);
        assertArrayEquals("sortArraySingleElement", expected, sortedInput);
    }

    static void sortArrayDuplicates() {
        int[] input = {5, 4, 2, 9, 7, 2};
        int[] expected = {2, 2, 4, 5, 7, 9};
        int[] sortedInput = SortUtil.sortArray(input);
        assertArrayEquals("sortArrayDuplicates", expected, sortedInput);
    }

    static void sortArrayNegatives() {
        int[] input = {4, -2, 0, 1, -3};
        int[] expected = {-3, -2, 0, 1, 4};
        int[] sortedInput = SortUtil.sortArray(input);
        assertArrayEquals("sortArrayNegatives", expected, sortedInput);
    }

    static void sortArrayAlreadySorted() {
        int[] input = {0, 1, 2, 3};
        int[] expected = {0, 1, 2, 3};
        int[] sortedInput = SortUtil.sortArray(Arrays.copyOf(input, input.length));
        assertArrayEquals("sortArrayAlreadySorted", expected, sortedInput);
    }

    static void sortArrayReverseSorted() {
        int[] input = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        int[] sortedInput = SortUtil.sortArray(input);
        assertArrayEquals("sortArrayReverseSorted", expected, sortedInput);
    }

    /** MR: sort(concat(sort(left), sort(right))) equals sort(whole). */
    static void sortArrayConcatenatedAndReSorted() {
        int[] input = {5, 4, 2, 9, 7};
        int[] concatenatedInput = SortUtil.sortArray(concatenated(input));
        int[] expectedFullSort = SortUtil.sortArray(Arrays.copyOf(input, input.length));
        assertArrayEquals("sortArrayConcatenatedAndReSorted", expectedFullSort, concatenatedInput);
    }

    public static void main(String[] args) {
        sortArrayEmptyArray();
        sortArraySingleElement();
        sortArrayDuplicates();
        sortArrayNegatives();
        sortArrayAlreadySorted();
        sortArrayReverseSorted();
        sortArrayConcatenatedAndReSorted();
        System.out.println("All output-1-test (LLM-shaped) cases passed.");
    }
}
