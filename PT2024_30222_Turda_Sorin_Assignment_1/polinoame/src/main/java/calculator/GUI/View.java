package calculator.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private Model model;
    private JPanel pane;
    private JPanel polynomialPanel;
    private JPanel operations;
    private JTextField polynomial1Tf;
    private JTextField polynomial2Tf;
    private JLabel polynomial1Label;
    private JLabel polynomial2Label;
    private JButton add;
    private JButton substract;
    private JButton divide;
    private JButton multiply;
    private JButton derivate;
    private JButton integrate;
    private JPanel resultPanel;
    private JLabel resultLabel;

    public View(Model model) {
        this.model = model;
        this.setSize(600, 200);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        this.add(pane, BorderLayout.CENTER);
        operations = new JPanel();
        polynomialPanel = new JPanel();

        polynomial1Label = new JLabel("Polynomial 1: ");
        polynomial2Label = new JLabel("Polynomial 2: ");
        polynomial2Tf = new JTextField();
        polynomial1Tf = new JTextField();
        add = new JButton("ADD");
        substract = new JButton("SUBSTRACT");
        divide = new JButton("DIVIDE");
        multiply = new JButton("MULTIPLY");
        integrate = new JButton("INTEGRATE");
        derivate = new JButton("DERIVATE");
        polynomialPanel.setLayout(new GridLayout(2, 2));
        polynomialPanel.add(polynomial1Label);
        polynomialPanel.add(polynomial1Tf);
        polynomialPanel.add(polynomial2Label);
        polynomialPanel.add(polynomial2Tf);
        operations.setLayout(new BoxLayout(operations, BoxLayout.X_AXIS));
        operations.add(add);
        operations.add(substract);
        operations.add(divide);
        operations.add(multiply);
        operations.add(integrate);
        operations.add(derivate);
        pane.add(polynomialPanel);
        pane.add(operations);
        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.LINE_AXIS));
        resultLabel = new JLabel("RESULT: ");
        resultPanel.add(resultLabel);
        this.add(resultPanel, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JTextField getPolynomial1Tf() {
        return polynomial1Tf;
    }
    public JTextField getPolynomial2Tf(){
        return polynomial2Tf;
    }

    public JLabel getResultLabel() {
        return resultLabel;
    }

    public void addMultiplyActoinListener(ActionListener actionListener) {
        multiply.addActionListener(actionListener);
    }

    public void addAddActoinListener(ActionListener actionListener) {
        add.addActionListener(actionListener);
    }

    public void addDivideActoinListener(ActionListener actionListener) {
        divide.addActionListener(actionListener);
    }

    public void addSubstractActoinListener(ActionListener actionListener) {
        substract.addActionListener(actionListener);
    }
    public void addIntegrateActoinListener(ActionListener actionListener){integrate.addActionListener(actionListener);}
    public void addDerivateActoinListener(ActionListener actionListener){derivate.addActionListener(actionListener);}
}
