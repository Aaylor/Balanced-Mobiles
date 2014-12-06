package helpers;


import java.util.Iterator;
import java.util.LinkedList;

public final class ExtMath {

    private ExtMath() {}

    /**
     * Returns the sum from a given array between beg and end (included).
     * @param array the given array
     * @param beg index where to start
     * @param end index where to end
     * @return the sum
     */
    public static Integer sum(Integer[] array, int beg, int end) {
        Integer result = 0;

        if (beg > end || beg < 0 || end >= array.length)
            throw new IllegalArgumentException();

        for (int i = beg; i <= end; i++) {
            result += array[i];
        }

        return result;
    }

    public static Integer sum(LinkedList<Integer> array) {
        Iterator<Integer> iterator = array.iterator();
        int sum = 0;

        while (iterator.hasNext()) {
            sum += iterator.next();
        }

        return sum;
    }

    /**
     * Returns the half from the given integer
     * @param i an integer
     * @return half of the integer
     */
    public static Double half(Integer i) {
        return ((double)i) / 2.0;
    }
}
