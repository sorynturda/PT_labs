package calculator.logic;

import calculator.GUI.Model;
import calculator.GUI.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private Model model;
    private View view;
    private Regex regex = new Regex();
    private Operation op = new Operation();

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        addActionListener();
        divideActionListener();
        substractActionListener();
        derivateActionListener();
        integrateActionListener();
        multiplyActionListener();
    }

    public void multiplyActionListener() {
        view.addMultiplyActoinListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPolynomials();
                if (view.getPolynomial1Tf().getText().equals("0") || view.getPolynomial2Tf().getText().equals("0"))
                    view.getResultLabel().setText("0");
                else
                    view.getResultLabel().setText(op.multiply(model.getP1(), model.getP2()).toString());
            }
        });
    }

    public void integrateActionListener() {
        view.addIntegrateActoinListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPolynomials();
                System.out.println(model.getP1().toString());
                view.getResultLabel().setText(op.integrate(model.getP1()).toString());
            }
        });
    }

    public void derivateActionListener() {
        view.addDerivateActoinListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPolynomials();
                System.out.println(op.derivate(model.getP1()).toString());
                view.getResultLabel().setText(op.derivate(model.getP1()).toString());
            }
        });
    }

    public void substractActionListener() {
        view.addSubstractActoinListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPolynomials();
                view.getResultLabel().setText(op.substract(model.getP1(), model.getP2()).toString());
            }
        });
    }

    public void divideActionListener() {
        view.addDivideActoinListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                model.setP1(regex.stringToPolynom(view.getPolynomial1Tf().getText()));
//                model.setP2(regex.stringToPolynom(view.getPolynomial2Tf().getText()));
//                view.getResultLabel().setText(op.add(model.getP1(), model.getP2()).toString());
            }
        });
    }

    public void addActionListener() {
        view.addAddActoinListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPolynomials();
                view.getResultLabel().setText(op.add(model.getP1(), model.getP2()).toString());
            }
        });
    }

    private void setPolynomials() {
        model.setP1(regex.stringToPolynom(view.getPolynomial1Tf().getText()));
        model.setP2(regex.stringToPolynom(view.getPolynomial2Tf().getText()));
    }

}
