package code.numbers;

import static java.lang.Math.sqrt;

public class ComplexNumber implements Comparable<ComplexNumber> {
    private double realPart;
    private double imaginaryPart;

    public ComplexNumber(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    public ComplexNumber() {
        this(0, 0);
    }

    private StringBuilder utilSb = new StringBuilder();
    @Override
    public String toString() {
        utilSb.setLength(0);
        if (realPart != 0) {
            utilSb.append(realPart);
            if (imaginaryPart < 0) {
                utilSb.append("-");
                utilSb.append(-imaginaryPart);
                utilSb.append("i");
            } else if (imaginaryPart > 0) {
                utilSb.append("+");
                utilSb.append(imaginaryPart);
                utilSb.append("i");
            }
        } else {
            utilSb.append(imaginaryPart);
            if (imaginaryPart != 0) {
                utilSb.append("i");
            }
        }

        return utilSb.toString();
    }

    public ComplexNumber add(ComplexNumber other) {
        ComplexNumber result = new ComplexNumber();
        result.realPart = realPart + other.realPart;
        result.imaginaryPart = imaginaryPart + other.imaginaryPart;
        return result;
    }

    public ComplexNumber sub(ComplexNumber other) {
        ComplexNumber result = new ComplexNumber();
        result.realPart = realPart - other.realPart;
        result.imaginaryPart = imaginaryPart - other.imaginaryPart;
        return result;
    }

    public ComplexNumber mul(ComplexNumber other) {
        ComplexNumber result = new ComplexNumber();
        result.realPart = realPart * other.realPart - imaginaryPart * other.imaginaryPart;
        result.imaginaryPart = realPart * other.imaginaryPart + imaginaryPart * other.realPart;
        return result;
    }

    public ComplexNumber div(ComplexNumber other) {
        double div = other.realPart * other.realPart + other.imaginaryPart * other.imaginaryPart;
        ComplexNumber result = new ComplexNumber();
        result.realPart = realPart * other.realPart + imaginaryPart * other.imaginaryPart;
        result.realPart /= div;
        result.imaginaryPart = imaginaryPart * other.realPart - realPart * other.imaginaryPart;
        result.imaginaryPart /= div;
        return result;
    }

    public ComplexNumber getConjugate() {
        ComplexNumber result = new ComplexNumber();
        result.realPart = realPart;
        result.imaginaryPart = imaginaryPart * -1;
        return result;
    }

    public double getModulus() {
        return sqrt(realPart * realPart + imaginaryPart * imaginaryPart);
    }

    public double getRealPart() {
        return realPart;
    }

    public void setRealPart(double realPart) {
        this.realPart = realPart;
    }

    public double getImaginaryPart() {
        return imaginaryPart;
    }

    public void setImaginaryPart(double imaginaryPart) {
        this.imaginaryPart = imaginaryPart;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int compareTo(ComplexNumber o) {
        if (getModulus() < o.getModulus()) {
            return -1;
        }
        if (getModulus() > o.getModulus()) {
            return 1;
        }
        return 0;
    }
}
