package calculator.GUI;

import calculator.model.Polynomial;

public class Model {
    private Polynomial<Integer> p1;
    private Polynomial<Integer> p2;

    public Polynomial<Integer> getP1() {
        return p1;
    }

    public void setP1(Polynomial<Integer> p1) {
        this.p1 = p1;
    }

    public Polynomial<Integer> getP2() {
        return p2;
    }

    public void setP2(Polynomial<Integer> p2) {
        this.p2 = p2;
    }
}
