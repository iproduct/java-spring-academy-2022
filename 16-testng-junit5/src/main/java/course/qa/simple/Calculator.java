package course.qa.simple;

import java.math.BigInteger;

public class Calculator {
    public int add(int x, int y) {
        return x + y;
    }
    public int multiply(int x, int y) {
        return x * y;
    }
    public int divide(int x, int y) {
        return x / y;
    }

    public int power(int x, int y) {
        return (int) Math.pow(x, y);
    }
    public BigInteger generateNextPrime(BigInteger biggerThan){
        return biggerThan.nextProbablePrime();
    }
}
