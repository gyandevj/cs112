package sorting; 

public class Sorts {

    public static <E extends Comparable<E>> void sort1(E[] array) { 
        for (int j = 0; j < array.length - 1; j++) {
            for (int i = 0; i < array.length - j - 1; i++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    E temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp; 
                }
            }
        } 
    }

    public static <E extends Comparable<E>> void sort2(E[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[min]) < 0) {
                    min = j;
                }
            }
            if (min != i) {
                E temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    public static <E extends Comparable<E>> void sort3(E[] array) { 
        if (array.length < 2) {
            return;
        }
        int mid = array.length / 2;
        E[] left = (E[]) new Comparable[mid];
        E[] right = (E[]) new Comparable[array.length - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < array.length; i++) {
            right[i - mid] = array[i];
        }

        sort3(left);
        sort3(right);
        sort3helper(array, left, right);
    }

    private static <E extends Comparable<E>> void sort3helper(E[] array, E[] left, E[] right) { 
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) <= 0) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        while (i < left.length) {
            array[k++] = left[i++];
        }

        while (j < right.length) {
            array[k++] = right[j++];
        }
    }


    public static <E extends Comparable<E>> void sort4(E[] array) {
        sort4helper1(array, 0, array.length - 1);
    }

    private static <E extends Comparable<E>> void sort4helper1(E[] array, int low, int high) {
        if (low < high) {
            int pi = sort4helper2(array, low, high);
            sort4helper1(array, low, pi - 1);
            sort4helper1(array, pi + 1, high);
        }
    }

    private static <E extends Comparable<E>> int sort4helper2(E[] array, int low, int high) {
        E pivot = array[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                E temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        E temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

}
