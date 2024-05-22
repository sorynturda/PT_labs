package ro.tuc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;

public class Log {

    String fileName;

    public Log(int numberOfClients, int numberOfServers, int minArrivalTime, int maxArrivalTime, int minProcessingTime, int maxProcessingTime, int simulationTime) {
        fileName = numberOfClients + ".txt";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("n = " + numberOfClients + "\n");
            writer.write("q = " + numberOfServers + "\n");
            writer.write("simulation time: " + simulationTime + "\n");
            writer.write("arrival time: [" + minArrivalTime + ", " + maxArrivalTime + "]\n");
            writer.write("process time: [" + minProcessingTime + ", " + maxProcessingTime + "]\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void append(String s) {
        BufferedWriter writer = null;
        try {
             writer = new BufferedWriter(new FileWriter(fileName, true));
             writer.write(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally{
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
