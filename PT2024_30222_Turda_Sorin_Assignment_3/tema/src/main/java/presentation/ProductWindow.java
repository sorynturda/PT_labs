package presentation;

import bll.ProductBLL;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * graphical user interface where user can add, delete and update a product
 */
public class ProductWindow extends JFrame {
    private JPanel panel1;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JTable product;
    private JTextField quantity;
    private JTextField nname;
    private ProductBLL productBLL;

    public ProductWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel1);
        this.setTitle("PRODUCT");
        this.setSize(500, 500);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product pr = new Product(nname.getText(), Integer.parseInt(quantity.getText()));
                productBLL.insertProduct(pr);
                populateProductTable();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = product.getSelectedRow();
                if (row == -1)
                    JOptionPane.showMessageDialog(null, "Select a product!");
                else {
                    Product pr = new Product(Integer.parseInt(product.getValueAt(row, 0).toString()), product.getValueAt(row, 1).toString(), Integer.parseInt(product.getValueAt(row, 2).toString()));
                    productBLL.deleteProduct(pr);
                    populateProductTable();
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = product.getSelectedRow();
                if (row == -1)
                    JOptionPane.showMessageDialog(null, "Select the updated product!");
                else {
                    Product pr = new Product(Integer.parseInt(product.getValueAt(row, 0).toString()), product.getValueAt(row, 1).toString(), Integer.parseInt(product.getValueAt(row, 2).toString()));
                    productBLL.updateProduct(pr);
                    populateProductTable();
                }
            }
        });
        updateButton.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                System.out.println("FOCUS");
                populateProductTable();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        productBLL = new ProductBLL();
        product = new JTable();
        populateProductTable();
    }

    private void populateProductTable() {
        product.setModel(PopulateTable.buildTableModel(productBLL.findProducts()));
    }
}
