import java.util.Comparator;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Julia Neuman
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement cocktail shaker sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * When writing your sort, don't recheck already sorted items. The amount of
     * items you are comparing should decrease by 1 for each pass of the array
     * (in either direction). See the PDF for more info.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void cocktailShakerSort(T[] arr,
                                              Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Arr and comparator"
                    + " cannot be null");
        }
        boolean sorted = false;
        int checkLength = arr.length - 1;
        int startIndex = 0;
        while (!sorted) {
            sorted = true;
            for (int i = startIndex; i < checkLength; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    sorted = false;
                }

            }
            if (!sorted) {
                sorted = true;
                checkLength = checkLength - 1;
                for (int i = checkLength; i > startIndex; i--) {
                    if (comparator.compare(arr[i - 1], arr[i]) >  0) {
                        swap(arr, i, i - 1);
                        sorted = false;
                    }
                }

            }
            startIndex = startIndex + 1;
        }

    }

    /**
     * Swaps two elements in an array
     * @param arr   the array to perform the swap on
     * @param x     an index
     * @param y     an index
     * @param <T>   data type of the array
     */
    private static <T> void swap(T[] arr, int x, int y) {
        T temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {

        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("The array and the comparator"
                    + " cannot be null.");
        }
        int j = 0;
        for (int i = 1; i < arr.length; i++) {
            T temp = arr[i];
            j = i;
            while (j != 0 && comparator.compare(temp, arr[j - 1]) < 0) {
                arr[j] = arr[j - 1];
                j--;

            }
            arr[j] = temp;
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("The array and the "
                     + "comparator cannot be null.");
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minInt = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[minInt], arr[j]) > 0) {
                    minInt = j;
                }
            }
            if (minInt != i) {
                swap(arr, minInt , i);
            }
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("Neither the array, comparator,"
                    + " nor rand can be null.");
        }
        if (arr.length > 1) {
            quickSortHelper(arr, comparator, rand, 0 , arr.length - 1);
        }

    }
    /**
     * helper method for quick sort
     *
     * @param arr         the array
     * @param comparator  how items will be compared
     * @param rand        the random object
     * @param start       index
     * @param end         index
     * @param <T>         type of array
     */
    private static <T> void quickSortHelper(T[] arr, Comparator<T> comparator,
                                            Random rand, int start, int end) {
        if (start >= end) {
            return;
        }
        int savedStart = start;
        int savedEnd = end;
        int pivotIndex = rand.nextInt(end - start) + start;
        System.out.println("pivot: " + pivotIndex + "pivot data: " + arr[pivotIndex]);
        while (start <= end) {
            while (comparator.compare(arr[pivotIndex], arr[end]) < 0) {
                end--;
            }


            while (comparator.compare(arr[pivotIndex], arr[start]) > 0) {
                start++;
            }

            if (start <= end) {
                swap(arr, start, end);
                start++;
                end--;
            }
        }
        if (savedStart < end) {
            quickSortHelper(arr, comparator, rand, savedStart, end);
        }
        if (start < savedEnd) {
            quickSortHelper(arr, comparator, rand, start, savedEnd);
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * ********************* IMPORTANT ************************
     * FAILURE TO DO SO MAY CAUSE ClassCastException AND CAUSE
     * YOUR METHOD TO FAIL ALL THE TESTS FOR MERGE SORT
     * ********************************************************
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("The array and the"
                    + " comparator cannot be null.");
        }
        T[] arr2;
        arr2 = mergeSortHelper(arr, comparator);
        for (int i = 0; i < arr2.length; i++) {
            arr[i] = arr2[i];
        }

    }
    /**
     * helper method for merge sort
     *
     * @param arr        the array
     * @param comparator how items will be compared
     * @param <T>        type of array
     * @return T[]       the sorted array
     */
    private static <T> T[] mergeSortHelper(T[] arr, Comparator<T> comparator) {
        if (arr.length <= 1) {
            return arr;
        } else {
            T[] s1 = (T[]) new Object[arr.length / 2];
            T[] s2 = (T[]) new Object[arr.length - (arr.length / 2)];
            for (int i = 0; i < (arr.length / 2); i++) {
                s1[i] = arr[i];
            }
            int j = 0;
            for (int i = arr.length / 2; i < arr.length; i++) {
                s2[j] = arr[i];
                j++;
            }
            s1 = mergeSortHelper(s1, comparator);
            s2 = mergeSortHelper(s2, comparator);
            return (merge(s1, s2, comparator));


        }
    }
    /**
     * merge sort helper method
     *
     * @param t1        first array
     * @param t2        second array
     * @param comparator how items will be compared
     * @param <T>        the type of the array
     * @return T[]      the merged array
     */
    private static <T> T[] merge(T[] t1, T[] t2, Comparator<T> comparator) {
        T[] temp = (T[]) new Object[t1.length + t2.length];

        int start = 0;
        int j1 = 0;
        int j2 = 0;
        for (int i = 0; i < (t2.length + t1.length); i++) {
            if (j1 < t1.length && j2 < t2.length
                    && comparator.compare(t1[j1], t2[j2]) <= 0) {
                temp[start] = t1[j1];
                start++;
                j1++;
            } else if (j1 < t1.length && j2 < t2.length) {
                temp[start] = t2[j2];
                start++;
                j2++;
            } else if (j1 < t1.length) {
                temp[start] = t1[j1];
                start++;
                j1++;
            } else {
                temp[start] = t2[j2];
                start++;
                j2++;
            }
        }
        return temp;
    }

    /**
     * Implement radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * DO NOT USE {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use an ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] radixSort(int[] arr) {
        if (arr != null) {
            final int radix = 10;
            List<Integer>[] bucket = new ArrayList[radix];
            List<Integer>[] negBucket = new ArrayList[radix];
            for (int i = 0; i < bucket.length; i++) {
                bucket[i] = new ArrayList<>();
                negBucket[i] = new ArrayList<>();
            }
            boolean max = false;
            int tmp;
            int placement = 1;
            while (!max) {
                max = true;
                for (Integer i : arr) {
                    tmp = i / placement;
                    if (tmp % radix >= 0) {
                        bucket[tmp % radix].add(i);
                    } else {
                        negBucket[negBucket.length
                                - Math.abs(tmp % radix)].add(i);
                    }
                    if (max && tmp > 0 || tmp < 0) {
                        max = false;
                    }
                }
                int a = 0;
                for (int x = 0; x < radix; x++) {
                    for (Integer i : negBucket[x]) {
                        arr[a++] = i;
                    }
                    negBucket[x].clear();
                }
                for (int b = 0; b < radix; b++) {
                    for (Integer i : bucket[b]) {
                        arr[a++] = i;
                    }
                    bucket[b].clear();
                }
                placement *= radix;
            }
            return arr;
        } else {
            throw new IllegalArgumentException("Null argument");
        }
    }



    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sort instead of {@code Math.pow()}. DO NOT MODIFY THIS METHOD.
     *
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power.
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Invalid exponent.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * pow(base, (exp / 2) + 1);
        }
    }
}
