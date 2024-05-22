package ro.tuc.gui;

import ro.tuc.Controller;
import ro.tuc.SimulationManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationFrame extends JFrame{
    private JPanel mainPanel;
    private JTextField clientsTf;
    private JTextField queuesTf;
    private JTextField simulationTimeTf;
    private JTextField arrivalTimeTf;
    private JTextField serviceTimeTf;
    private JRadioButton timeRadioButton;
    private JRadioButton queueRadioButton;
    private JButton startButton;
    private boolean click;

    private SimulationManager sim;
    public SimulationFrame(SimulationManager sim) {
        setContentPane(mainPanel);
        this.sim = sim;
        setSize(450,300);

        ButtonGroup G = new ButtonGroup();
        G.add(timeRadioButton);
        G.add(queueRadioButton);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

//        startButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(timeRadioButton.isSelected());
//            }
//        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int p=1;
                if(timeRadioButton.isSelected())
                    p=0;
                sim.setParameters(
                        Integer.parseInt(simulationTimeTf.getText()),
                        Integer.parseInt(arrivalTimeTf.getText().split(",")[0]),
                        Integer.parseInt(arrivalTimeTf.getText().split(",")[1]),
                        Integer.parseInt(serviceTimeTf.getText().split(",")[0]),
                        Integer.parseInt(serviceTimeTf.getText().split(",")[1]),
                        Integer.parseInt(queuesTf.getText()),
                        Integer.parseInt(clientsTf.getText()),
                        p
                );
                Thread t = new Thread(sim);
                t.start();
            }
        });
    }

    public boolean isClick() {
        return click;
    }
}
