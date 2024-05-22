package presentation;

import bll.ClientBLL;
import bll.OrderrBLL;
import bll.ProductBLL;
import model.Client;
import model.Orderr;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.NoSuchElementException;

public class OrderrWindow extends JFrame {
    private JPanel panel1;
    private JTable orderr;
    private JButton add;
    private JTextField clientId;
    private JTextField productId;
    private JTextField quantity;
    private JScrollPane scrollPane1;
    private OrderrBLL orderrBLL;
    private ClientBLL clientBLL;
    private ProductBLL productBLL;

    public OrderrWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        clientBLL = new ClientBLL();
        productBLL = new ProductBLL();

        this.setSize(500, 500);
        this.add(panel1);
        this.setSize(500, 500);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client cl = null;
                Product pr = null;
                int qt = Integer.parseInt(quantity.getText());
                if(qt < 0){
                    JOptionPane.showMessageDialog(null, "Enter a positive integer");
                    return;
                }
                try {
                    cl = clientBLL.findClientById(Integer.parseInt(clientId.getText()));
                    pr = productBLL.findProductById(Integer.parseInt(productId.getText()));
                } catch (NoSuchElementException exception) {
                    JOptionPane.showMessageDialog(null, "Select an existing client and an existing product!");
                }
                if (pr != null && cl != null && pr.getQuantity() - qt < 0) {
                    JOptionPane.showMessageDialog(null, "Not enough products!");
                } else {
                    orderrBLL.insertOrderr(new Orderr(cl.getId(), pr.getId(), qt));
                    productBLL.updateProduct(new Product(pr.getId(), pr.getName(), pr.getQuantity() - qt));
                    populateOrderTable();
                    JOptionPane.showMessageDialog(null, "The order was placed!");
                }
            }
        });

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        orderrBLL = new OrderrBLL();
        orderr = new JTable();
        populateOrderTable();
    }

    private void populateOrderTable() {
        orderr.setModel(PopulateTable.buildTableModel(orderrBLL.findOrderrs()));
    }
}
