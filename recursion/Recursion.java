package recursion;

public class Recursion {

    // pow method
    public static double pow(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent < 0) {
            return 1 / pow(base, -exponent);
        }
        return base * pow(base, exponent - 1);
    }

    // int backwards
    public static int backwards(int n) {
        if (n < 10) {
            return n;
        }
        return n % 10 + backwards(n / 10);
    }

    // max val 1d array
    public static int maxVal(int[] arr) {
        return maxVal(arr, 0);
    }

    private static int maxVal(int[] arr, int index) {
        if (index == arr.length - 1) {
            return arr[index];
        }
        return Math.max(arr[index], maxVal(arr, index + 1));
    }

    // contents of arr backwards
    public static int[] arrReverse(int[] arr) {
        int[] arrNew = new int[arr.length];
        
        int index = 0;
        

        return arrNew;
    }

}
