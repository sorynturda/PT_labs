package presentation;

import bll.ClientBLL;
import dao.ClientDAO;
import model.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * graphical user interface for client tabel where user can add, delete or update a client
 */
public class ClientWindow extends JFrame {
    private JPanel panel1;
    private JTextField first_name;
    private JTextField last_name;
    private JTable client;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JScrollPane scrollPane1;

    private ClientBLL clientBLL;

    public ClientWindow() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel1);
        this.setTitle("CLIENT");
        this.setSize(500, 500);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = client.getSelectedRow();
                if (row == -1)
                    JOptionPane.showMessageDialog(null, "Select a client!");
                 else {
                    Client cl = new Client(Integer.parseInt(client.getValueAt(row, 0).toString()), client.getValueAt(row, 1).toString(), client.getValueAt(row, 2).toString());
                    clientBLL.deleteClient(cl);
                    populateClientTable();
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client cl = new Client(first_name.getText(), last_name.getText());
                clientBLL.insertClient(cl);
                populateClientTable();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = client.getSelectedRow();
                if (row == -1)
                    JOptionPane.showMessageDialog(null, "Select the updated client!");
                else {
                    Client cl = new Client(Integer.parseInt(client.getValueAt(row, 0).toString()), client.getValueAt(row, 1).toString(), client.getValueAt(row, 2).toString());
                    clientBLL.updateClient(cl);
                    populateClientTable();
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        clientBLL = new ClientBLL();
        client = new JTable();
        populateClientTable();
    }

    private void populateClientTable() {
//        client = new JTable(PopulateTable.buildTableModel(clientBLL.findClients()));
        client.setModel(PopulateTable.buildTableModel(clientBLL.findClients()));
    }
}

