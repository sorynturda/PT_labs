package calculator.logic;

import calculator.model.Polynomial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Operation {

    public Polynomial<Integer> add(Polynomial<Integer> p1, Polynomial<Integer> p2) {
        Polynomial<Integer> p = p1.clone();
        HashMap<Integer, Integer> polynomial2 = p2.getPolynomial();
        for (Map.Entry<Integer, Integer> entry : polynomial2.entrySet()) {
            int power = entry.getKey();
            int coefficient = entry.getValue();
            p.addCoefficient(power, coefficient);
        }
        return p;
    }

    public Polynomial<Double> addD(Polynomial<Double> p1, Polynomial<Double> p2) {
        Polynomial<Double> p = p1.clone();
        HashMap<Integer, Double> polynomial2 = p2.getPolynomial();
        for (Map.Entry<Integer, Double> entry : polynomial2.entrySet()) {
            int power = entry.getKey();
            double coefficient = entry.getValue();
            p.addCoefficient(power, coefficient);
        }
        return p;
    }

    public Polynomial<Integer> substract(Polynomial<Integer> p1, Polynomial<Integer> p2) {
        Polynomial<Integer> p = p1.clone();
        HashMap<Integer, Integer> polynomial2 = p2.getPolynomial();
        for (Map.Entry<Integer, Integer> entry : polynomial2.entrySet()) {
            int power = entry.getKey();
            int coefficient = entry.getValue();
            p.addCoefficient(power, -coefficient);
        }
        return p;
    }

    public Polynomial<Double> substractD(Polynomial<Double> p1, Polynomial<Double> p2) {
        Polynomial<Double> p = p1.clone();
        HashMap<Integer, Double> polynomial2 = p2.getPolynomial();
        for (Map.Entry<Integer, Double> entry : polynomial2.entrySet()) {
            int power = entry.getKey();
            double coefficient = entry.getValue();
            p.addCoefficient(power, -coefficient);
        }
        return p;
    }

    public ArrayList<Polynomial<Double>> divide(Polynomial<Integer> n, Polynomial<Integer> d) {
        if (d.checkZero())
            return null;
        Polynomial<Double> q = new Polynomial<>();
        Polynomial<Double> r = new Polynomial<>();
        while (!r.checkZero() && r.getDegree() >= d.getDegree()) {
            double t = r.getPolynomial().get(r.getDegree()) / d.getPolynomial().get(r.getDegree());
            Integer power = r.getDegree() - d.getDegree();
            Polynomial<Double> monom = new Polynomial<>();
            monom.addCoefficient(power, t);
            q = addD(q, monom);
            r = substractD(r, multiplyD(monom, d));
        }
        ArrayList<Polynomial<Double>> a = new ArrayList<>();
        a.add(q);
        a.add(r);
        return a;
    }

    public Polynomial<Integer> multiply(Polynomial<Integer> p1, Polynomial<Integer> p2) {
        Polynomial<Integer> p = new Polynomial<>();
        HashMap<Integer, Integer> polynomial1 = p1.getPolynomial();
        HashMap<Integer, Integer> polynomial2 = p2.getPolynomial();
        for (Map.Entry<Integer, Integer> entry1 : polynomial1.entrySet()) {
            int power1 = entry1.getKey();
            int coefficient1 = entry1.getValue();
            for (Map.Entry<Integer, Integer> entry2 : polynomial2.entrySet()) {
                int power2 = entry2.getKey();
                int coefficient2 = entry2.getValue();
                int newPower = power1 + power2;
                p.addCoefficient(newPower, coefficient2 * coefficient1);
            }
        }
        return p;
    }

    public Polynomial<Double> multiplyD(Polynomial<Double> p1, Polynomial<Integer> p2) {
        Polynomial<Double> p = new Polynomial<>();
        HashMap<Integer, Double> polynomial1 = p1.getPolynomial();
        HashMap<Integer, Integer> polynomial2 = p2.getPolynomial();
        for (Map.Entry<Integer, Double> entry1 : polynomial1.entrySet()) {
            int power1 = entry1.getKey();
            double coefficient1 = entry1.getValue();
            for (Map.Entry<Integer, Integer> entry2 : polynomial2.entrySet()) {
                int power2 = entry2.getKey();
                double coefficient2 = entry2.getValue();
                int newPower = power1 + power2;
                p.addCoefficient(newPower, coefficient2 * coefficient1);
            }
        }
        return p;
    }

    public Polynomial<Integer> derivate(Polynomial<Integer> p) {
        Polynomial<Integer> res = new Polynomial<>();
        for (Map.Entry<Integer, Integer> entry : p.getPolynomial().entrySet()) {
            Integer power = entry.getKey();
            Integer coefficient = entry.getValue();
            if (!power.equals(0))
                res.addCoefficient(power - 1, coefficient * power);
        }
        return res;
    }

    public Polynomial<Double> integrate(Polynomial<Integer> p) {
        Polynomial<Double> res = new Polynomial<>();
        for (Map.Entry<Integer, Integer> entry : p.getPolynomial().entrySet()) {
            Integer power = entry.getKey();
            double coefficient = entry.getValue().doubleValue();
            res.addCoefficient(power + 1, coefficient / (power + 1));
        }
        return res;
    }
}
