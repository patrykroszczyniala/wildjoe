package pl.pepuch.wildjoe.helpers;

public class Math {

    public static int factorial(int n) throws IndexOutOfBoundsException {
        if (n < 0) {
            throw new IndexOutOfBoundsException("You can not calculate the factorial of a negative number");
        }

        long result = 1;
        if (n == 0) {
            result = 1;
        } else {
            for (long i = 1; i <= n; i++) {
                result *= i;
                if (result >= Integer.MAX_VALUE) {
                    throw new IndexOutOfBoundsException("Result is too large for Integer type");
                }
            }
        }

        return (int) result;
    }
}
