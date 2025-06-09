public class ComplexNumber {

    private double real, imaginary;
    
    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public double abs() {
        return Math.sqrt(real*real + imaginary*imaginary);
    }

    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(real + other.real, imaginary + other.imaginary);
    }

    public ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(real - other.real, imaginary - other.imaginary);
    }

    public ComplexNumber multiply(ComplexNumber other) {
        double acbd = real * other.real - imaginary * other.imaginary;
        double adbc = real * other.imaginary + imaginary * other.real;
        return new ComplexNumber(acbd, adbc);
    }

    public ComplexNumber multiply(double factor) {
        return multiply(new ComplexNumber(factor, 0));
    }

    public ComplexNumber reciprocal() {
        double abSquare = real * real + imaginary * imaginary;
        return new ComplexNumber(real / abSquare, (-imaginary) / abSquare);
    }

    public ComplexNumber divide(ComplexNumber other) {
        return other
            .reciprocal()
            .multiply(this);
    }

    public ComplexNumber divide(double divisor) {
        return divide(new ComplexNumber(divisor, 0));
    }

    public ComplexNumber conjugate() {
        return new ComplexNumber(real, -imaginary);
    }

    public ComplexNumber exponentialOf() {
        double expReal = Math.exp(real) * Math.cos(imaginary);
        double expImaginary = Math.exp(real) * Math.sin(imaginary);

        return new ComplexNumber(expReal, expImaginary);
    }

}