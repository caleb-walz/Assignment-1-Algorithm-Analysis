public class Assignment1 {

	// help from https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/
	// help from https://www.enjoyalgorithms.com/blog/longest-common-subsequence
	// m and n are how far into the string to check
	public static int commonSubsequence(char[] text1, char[] text2, int m, int n) {
		if (m == 0 || n == 0) return 0; // base case - one or both of the strings have been completely checked
		if (text1[m - 1] == text2[n - 1]) { // if the last characters match...
			return 1 + commonSubsequence(text1, text2, m - 1, n - 1); // ...continue with the next-to-last in both strings
		} else { // if the last characters don't match...
			int x = commonSubsequence(text1, text2, m, n - 1); // ...move further into each individual string
			int y = commonSubsequence(text1, text2, m - 1, n);
			return (x > y) ? x : y; // pick the best result of the two
		}
	}

	// I used the formula: n_i = (2 * n_(i-1)) + (3 * n_(i-2))
	// This matches the example output.
	public static long[] notFibonacci(int terms) {
		long[] result = new long[terms];
		result[0] = 0;
		result[1] = 1;

		for (int i = 2; i < terms; i++) {
			result[i] = (2 * result[i - 1]) + (3 * result[i - 2]);
		}

		return result;
	}

	public static int whereInSequence(int input) {
		if (input < 0) return -1; // input should be positive

		int seqLength = 10;
		long[] seq = notFibonacci(seqLength);

		// if input is greater than greatest term of seq, generate more terms
		while (input > seq[seqLength - 1]) {
			seqLength += 5;
			seq = notFibonacci(seqLength);
		}

		for (int i = 0; i < seq.length; i++) {
			if (seq[i] == input) return i + 1; // output starts counting at 1
			if (seq[i] > input) return i;
		}

		return -2; // shouldn't happen
	}

	public static int removeElement(int[] nums, int val) {
		int j = nums.length - 1; // marks section of array where occurrences of val are stored
		while (j > 0 && nums[j] == val) j--; // if there are already elements that equal val at the end of the array, update the marker

		for (int i = 0; i <= j; i++) {
			if (nums[i] == val) {
				if (nums[i] == nums[j]) {
					j--;
					i--;
					continue; // don't swap if nums are the same, decrement j and reset loop to check again
				}
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
				j--;
			}
		}

		return j + 1; // nums[j] == last element not equal to val, so j + 1 == number of elements not equal to val
	}

	public static void main(String[] args) {
		// test commonSubsequence
		System.out.println("Testing commonSubsequence");
		String text1 = "abc";
		String text2 = "abc";
		System.out.println(text1 + ", " + text2 + ": " + commonSubsequence(text1.toCharArray(), text2.toCharArray(), text1.length(), text2.length()));
		text1 = "almanacs";
		text2 = "albatross";
		System.out.println(text1 + ", " + text2 + ": " + commonSubsequence(text1.toCharArray(), text2.toCharArray(), text1.length(), text2.length()));
		text1 = "almanac";
		text2 = "ferris";
		System.out.println(text1 + ", " + text2 + ": " + commonSubsequence(text1.toCharArray(), text2.toCharArray(), text1.length(), text2.length()));

		// test notFibonacci
		System.out.println("\nTesting notFibonacci");
		long[] result = notFibonacci(10);
		for (long i : result) System.out.print(i + " ");
		System.out.println();

		// test whereInSequence
		System.out.println("\nTesting whereInSequence");
		System.out.println(whereInSequence(8));
		System.out.println(whereInSequence(1640));
		System.out.println(whereInSequence(-20));
		System.out.println(whereInSequence(100000));

		// test removeElement
		System.out.println("\nTesting removeElement");
		int[] nums = new int[] { 3, 2, 2, 3 };
		int val = 3;
		System.out.print(removeElement(nums, val) + ", nums = [ ");
		for (int i : nums) System.out.print(i + " ");
		System.out.println("]");
		nums = new int[] {0, 1, 2, 2, 3, 0, 4, 2};
		val = 2;
		System.out.print(removeElement(nums, val) + ", nums = [ ");
		for (int i : nums) System.out.print(i + " ");
		System.out.println("]");
		nums = new int[] { 0, 4, 4, 0, 4, 4, 4, 0, 2 };
		val = 4;
		System.out.print(removeElement(nums, val) + ", nums = [ ");
		for (int i : nums) System.out.print(i + " ");
		System.out.println("]");
		nums = new int[] { 4, 2, 0, 2, 2, 1, 4, 4, 1, 4, 3, 2 };
		val = 4;
		System.out.print(removeElement(nums, val) + ", nums = [ ");
		for (int i : nums) System.out.print(i + " ");
		System.out.println("]");
	}

}