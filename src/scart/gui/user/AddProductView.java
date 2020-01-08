/*
Project Topic: Shopping Cart
Project Title: Speedy Shopping and Selling
File Name: AddProductView.java
 */
package scart.gui.user;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Creates JPanel for AddProduct
 */
public class AddProductView implements KeyListener {

  
    private JPanel mainPanel;
    private JPanel formPanel;
    private JLabel titleLabel, productNameLabel, priceLabel, invoicePriceLabel, quantityLabel;
    private JTextField productNameTextField, priceTextField, invoicePriceTextField, quantityTextField;
    private boolean formCompleted;

    /**
     * Constructor
     * precondition mainPanel != null
     * postcondition AddProduct Panel created
     * @param mainPanel a J p   anel
     */
    public AddProductView(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.formPanel = new JPanel(new GridLayout(4, 2, 50, 35));
        formPanel.setBorder(new EmptyBorder(125, 25, 25, 25));
        formCompleted = false;

        //Section to add a new product Title
        titleLabel = new JLabel("Add New Product");
        titleLabel.setBorder(new EmptyBorder(50, 25, 25, 25));
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setPreferredSize(new Dimension(600, 50));
        this.mainPanel.add(titleLabel);

        //Create Product Name Label
        productNameLabel = new JLabel("Product Name: ");
        productNameLabel.setFont(new Font("Ariel", Font.PLAIN, 22));
        this.formPanel.add(productNameLabel);

        //Product Name TextField
        productNameTextField = new JTextField();
        productNameTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        productNameTextField.addKeyListener(this);
        this.formPanel.add(productNameTextField);

        //Create Selling Price Label
        priceLabel = new JLabel("Selling Price: ");
        priceLabel.setFont(new Font("Ariel", Font.PLAIN, 22));
        this.formPanel.add(priceLabel);

        //Product Selling Price TextField
        priceTextField = new JTextField();
        priceTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        priceTextField.addKeyListener(this);
        this.formPanel.add(priceTextField);

        //Create Invoice Price Label
        invoicePriceLabel = new JLabel("Invoice Price: ");
        invoicePriceLabel.setFont(new Font("Ariel", Font.PLAIN, 22));
        this.formPanel.add(invoicePriceLabel);

        //Product Invoice Price TextField
        invoicePriceTextField = new JTextField();
        invoicePriceTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        invoicePriceTextField.addKeyListener(this);
        this.formPanel.add(invoicePriceTextField);

        //Create Quantity Label
        quantityLabel = new JLabel("Quantity: ");
        quantityLabel.setFont(new Font("Ariel", Font.PLAIN, 22));
        this.formPanel.add(quantityLabel);

        //Product Quantity TextField
        quantityTextField = new JTextField();
        quantityTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        quantityTextField.addKeyListener(this);
        this.formPanel.add(quantityTextField);

        //Add to mainPanel
        this.mainPanel.add(formPanel);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    /**
     * Ger Form completed
     * @return boolean
     */
    public boolean getFormCompleted() {
        return this.formCompleted;
    }

    /**
     * Get Product Name
     * @return a string
     */
    public String getProductName() {
        return (formCompleted) ? productNameTextField.getText() : "";
    }

    /**
     * Get Product invoice Price
     * @return a string
     */
    public String getInvoicePrice() {
        return (formCompleted) ? invoicePriceTextField.getText() : "";
    }

    /**
     * Get Product Selling Price
     * @return a string
     */
    public String getSellingPrice() {
        return (formCompleted) ? priceTextField.getText() : "";
    }

    /**
     * Get Product Quantity
     * @return a string
     */
    public String getQuantity() {
        return (formCompleted) ? quantityTextField.getText() : "";
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        formCompleted = false;
        boolean emptyProductName = true;
        boolean emptySellingPrice = true;
        boolean emptyInvoicePrice = true;
        boolean emptyQuantity = true;

        if (!"".equals(productNameTextField.getText()) && !"".equals(priceTextField.getText())) {
            emptyProductName = false;
            emptySellingPrice = false;
        }
        if (!"".equals(invoicePriceTextField.getText()) && !"".equals(quantityTextField.getText())) {
            emptyInvoicePrice = false;
            emptyQuantity = false;
        }
        if (!emptyProductName && !emptySellingPrice && !emptyInvoicePrice && !emptyQuantity) {
            formCompleted = true;
        }
    }
}
