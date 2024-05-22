package calculator.model;

import java.util.*;

public class Polynomial<T extends Number> implements Cloneable {
    HashMap<Integer, T> polynomial;
    ArrayList<Integer> a;
    private int degree;

    public Polynomial() {
        polynomial = new HashMap<>();
    }

    public void addCoefficient(Integer power, T coefficient) {
        if (coefficient.intValue() == 0 && (coefficient.doubleValue() == Math.floor(coefficient.doubleValue())))
            return;
        if (polynomial.get(power) == null)
            polynomial.put(power, coefficient);
        else {
            Integer sum = coefficient.intValue() + polynomial.get(power).intValue();
            if (sum.equals(0))
                polynomial.remove(power);
            else
                polynomial.put(power, (T) sum);
        }
        degree = Math.max(power, degree);
    }

    public void sortPolynomial() {
        a = new ArrayList<>(polynomial.keySet());
        a.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
    }

    public HashMap<Integer, T> getPolynomial() {
        return polynomial;
    }

    public int getDegree() {
        return degree;
    }

    public boolean checkZero() {
        return polynomial.isEmpty();
    }

    @Override
    public String toString() {
        String res = "Polynomial: ";
        sortPolynomial();
        for (Integer power : a) {
            double coefficient = polynomial.get(power).doubleValue();
            if (coefficient > 0)
                res += "+";
            if(coefficient == Math.floor(polynomial.get(power).doubleValue()))
                res += polynomial.get(power).intValue();
            else
                res += Math.floor(coefficient*100) / 100;
            res += "x^" + power;
        }
        return res;
    }
    @Override
    public Polynomial clone() {
        try {
            Polynomial<T> clone = (Polynomial) super.clone();
            clone.polynomial = (HashMap<Integer, T>) polynomial.clone();
            clone.degree = degree;
            if (a != null)
                clone.a = (ArrayList<Integer>) a.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
