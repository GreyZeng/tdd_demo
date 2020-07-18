package org.example.math;

import org.junit.Assert;
import org.junit.Test;

public class PrimeUtilTest {

    @Test
    public void getPrimes() {
        int[] expected = new int[]{};
        Assert.assertArrayEquals(expected, PrimeUtil.getPrimes(0));
        Assert.assertArrayEquals(expected, PrimeUtil.getPrimes(-1));
        Assert.assertArrayEquals(expected, PrimeUtil.getPrimes(2));
    }


    @Test
    public void getPrimes2() {
        Assert.assertArrayEquals(new int[]{2, 3, 5, 7}, PrimeUtil.getPrimes(9));
        Assert.assertArrayEquals(new int[]{2, 3, 5, 7, 11, 13}, PrimeUtil.getPrimes(17));
        Assert.assertArrayEquals(new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29}, PrimeUtil.getPrimes(30));
    }
}
