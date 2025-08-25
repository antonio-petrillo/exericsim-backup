public class ArmstrongNumbers {

    public boolean isArmstrongNumber(int num) {
        return num == sumDigit(num);
    }

    private int sumDigit(int num) {
        int sum = 0;
        int exp = 0;

        for (int i = num; i > 0; i /= 10) {
            exp++;
        }

        for(; num > 0; num /= 10) {
            sum += fastPow(num % 10, exp);
        }

        return sum;
    }

    private static int fastPow(int a, int b) {
        if (a == 0 && b == 0) throw new ArithmeticException("Can't raise 0 to the 0.");
        if (a == 1 || b == 0) return 1;
        int acc = 1;
        while (b > 1) {
            if ((b & 1) == 1) {
                acc *= a;
                b--;
            }
            a *= a;
            b >>= 1;
        }
        return acc * a;
    }

}
