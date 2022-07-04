package StanfordAssignments;

import java.math.BigInteger;

public class KaratsubaMultiplication {

    public static void main(String[] args) {
//        BigInteger a = new BigInteger("3141592653589793238462643383279502884197169399375105820974944592");
//        BigInteger b = new BigInteger("2718281828459045235360287471352662497757247093699959574966967627");
        BigInteger a = new BigInteger("101");
        BigInteger b = new BigInteger("219");
        System.out.println(defaultMultiply(a, b));
        System.out.println(multiply(a, b));
    }

    private static BigInteger defaultMultiply(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    private static BigInteger multiply(BigInteger a, BigInteger b) {
        int shift = Math.min(a.bitLength() / 2, b.bitLength() / 2);
        if (shift == 0) return a.multiply(b);
        BigInteger a1 = a.shiftRight(shift);
        BigInteger a2 = a.subtract(a1.shiftLeft(shift));
        BigInteger b1 = b.shiftRight(shift);
        BigInteger b2 = b.subtract(b1.shiftLeft(shift));
        BigInteger left = multiply(a1, b1);
        BigInteger right = multiply(a2, b2);
        BigInteger middle = multiply(a1.add(a2), b1.add(b2));
        middle = middle.subtract(left).subtract(right);
        return left.shiftLeft(2 * shift).add(middle.shiftLeft(shift)).add(right);
    }

}