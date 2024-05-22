package ro.tuc;

import ro.tuc.gui.SimulationFrame;

public class Controller {
    public static void main(String[] args) {
        SimulationManager sim = new SimulationManager();
        Thread t = new Thread(sim);
        SimulationFrame frame = new SimulationFrame(sim);
    }
}
