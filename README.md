# TDD示例

参考文档：
[码农翻身-从零开始造Spring](https://mp.weixin.qq.com/s/gbvdwpPtQcjyaigRBDjd-Q) 中的《介绍TDD开发方式， 重构的方法》


TDD的流程是：
写一个测试用例->运行：失败->写Just enough的代码，让测试通过->重构代码保持测试通过，然后循环往复。


下面，我们通过一个简单的例子来说明TDD的流程


需求：写一个方法，返回小于给定值max的所有素数组成的数组
```java
public static int[] getPrimes(int max);
```
先做一个简单的任务分解

- 边界条件：getPrimes(0) getPrimes(-1) getPrimes(2)应该返回什么？
- 正常输入：getPrimes(9) getPrimes(17) getPrimes(30)



首先，要创建一个测试类
PrimeTest
```java
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
}
```
运行这个测试用例，显示测试失败：
![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1595059607786-7097a14e-5f9d-4675-ab62-1022cbbb98f6.png#align=left&display=inline&height=222&margin=%5Bobject%20Object%5D&name=image.png&originHeight=222&originWidth=1375&size=47867&status=done&style=none&width=1375)
然后实现刚好满足需求的这部分代码
```java
public class PrimeUtil {

    public static int[] getPrimes(int max) {
        if (max == 0 || max == -1 || max == 2) {
            return new int[]{};
        }
        return null;
    }
}
```
重新运行测试用例，测试通过
![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1595059704248-3bf08940-6307-4ff6-8049-d7007d3d3ed7.png#align=left&display=inline&height=309&margin=%5Bobject%20Object%5D&name=image.png&originHeight=309&originWidth=1059&size=28493&status=done&style=none&width=1059)


然后增加测试方法
```java
 @Test
    public void getPrimes2() {
        Assert.assertArrayEquals(new int[]{2, 3, 5, 7}, PrimeUtil.getPrimes(9));
        Assert.assertArrayEquals(new int[]{2, 3, 5, 7, 11, 13}, PrimeUtil.getPrimes(17));
        Assert.assertArrayEquals(new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29}, PrimeUtil.getPrimes(30));
    }
```
运行这个测试，报错
![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1595072591082-c8c39d69-8c19-4d5d-8941-ac7fe196d4e4.png#align=left&display=inline&height=315&margin=%5Bobject%20Object%5D&name=image.png&originHeight=315&originWidth=1473&size=56932&status=done&style=none&width=1473)
然后再实现满足这个测试用例的方法
```java
public class PrimeUtil {

    public static int[] getPrimes(int max) {
        if (max <= 2) {
            return new int[]{};
        }
        int[] newArray = new int[max];
        int size = 0, k= 0;
        for (int i = 2  ; i < max; i++) {
            for ( k = 2  ; k < i/2+1; k++) {
                if(i%k ==0) {
                    break;
                }
            }
            if (k == i / 2+1){
                newArray[size++] = i;
            }
        }
        newArray = Arrays.copyOf(newArray,size);
        return newArray;
    }
}
```
再次运行单元测试，测试通过
![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1595072793710-3770e3fe-9f95-4de6-ab2d-92a234e0dcd6.png#align=left&display=inline&height=267&margin=%5Bobject%20Object%5D&name=image.png&originHeight=267&originWidth=1046&size=25390&status=done&style=none&width=1046)
最后，重构getPrimes方法
```java
public static int[] getPrimes(int max) {
        if (max <= 2) {
            return new int[]{};
        }
        int[] primes = new int[max];
        int count = 0, j = 0;
        for (int num = 2; num < max; num++) {
            if (isPrime(num)) {
                primes[count++] = num;
            }
        }
        primes = Arrays.copyOf(primes, count);
        return primes;
    }

    private static boolean isPrime(int num) {
        int i ;
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
```
重新运行单元测试，测试通过
![image.png](https://cdn.nlark.com/yuque/0/2020/png/757806/1595073155835-ccaaf933-de27-4de3-ab39-2e0e6aef738f.png#align=left&display=inline&height=205&margin=%5Bobject%20Object%5D&name=image.png&originHeight=205&originWidth=598&size=16917&status=done&style=none&width=598)


源码：
[Github](https://github.com/GreyZeng/tdd_demo)
[Gitee](https://gitee.com/greyzeng/tdd_demo.git)




