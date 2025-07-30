import java.util.Objects;

public record Rational(int numerator, int denominator) {

    private static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (b > a) {
            int tmp = b;
            b = a;
            a = tmp;
        }
        while (b > 0) {
           int mod = a % b;
           a = b;
           b = mod;
        }
        return a;
    }

    private static int mcm(int a, int b) {
        return a * b / gcd(a, b);
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

    public Rational(int numerator, int denominator) {
        if (denominator == 0) throw new IllegalArgumentException("Denominator cannot be 0.");
        int gcd = this.gcd(numerator, denominator);
        int sign = (denominator & 0x80000000) != 0 ? -1 : 1; // basically (denominator < 0) but cooler
        this.numerator = numerator / gcd * sign;
        this.denominator = Math.abs(denominator / gcd);
    }

    public int getNumerator() {
        return numerator();
    }

    public int getDenominator() {
        return denominator();
    }

    public Rational add(Rational other) {
        int mcm = mcm(denominator(), other.denominator());
        return new Rational(numerator() * (mcm / denominator()) + other.numerator() * (mcm / other.denominator()), mcm);
    }

    public Rational subtract(Rational other) {
        return add(new Rational(- other.numerator(), other.denominator()));
    }

    public Rational multiply(Rational other) {
        return new Rational(numerator() * other.numerator(), denominator() * other.denominator());
    }

    public Rational divide(Rational other) {
        return multiply(new Rational(other.denominator(), other.numerator()));
    }

    public Rational abs() {
        return new Rational(Math.abs(numerator()), denominator());
    }

    public Rational pow(int power) {
        if (power < 0) return new Rational(denominator(), numerator()).pow(-power);

        return new Rational(fastPow(numerator(), power), fastPow(denominator(), power));
    }

    public double exp(double base) {
        return Math.pow(Math.pow(base, 1. / denominator()), numerator());
    }

    @Override
    public String toString() {
        return String.format("%d/%d", this.getNumerator(), this.getDenominator());
    }

    // @Override
    // public boolean equals(Object obj) {
    //     if (obj instanceof Rational other) {
    //         return this.getNumerator() == other.getNumerator()
    //                 && this.getDenominator() == other.getDenominator();
    //     }

    //     return false;
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(this.getNumerator(), this.getDenominator());
    // }
}
