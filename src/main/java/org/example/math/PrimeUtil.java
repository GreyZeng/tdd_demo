package org.example.math;

import java.util.Arrays;

public class PrimeUtil {

    public static int[] getPrimes(int max) {
        if (max <= 2) {
            return new int[]{};
        }
        int[] primes = new int[max];
        int count = 0;
        for (int num = 2; num < max; num++) {
            if (isPrime(num)) {
                primes[count++] = num;
            }
        }
        primes = Arrays.copyOf(primes, count);
        return primes;
    }

    private static boolean isPrime(int num) {
        int i;
        for (i = 2; i < num / 2 + 1; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        if (i == num / 2 + 1) {
            return true;
        }
        return false;
    }
}
