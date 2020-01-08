/*
Project Topic: Shopping Cart
Project Title: Speedy Shopping and Selling
File Name: FormView.java
 */
package scart.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Creates form JPanel for registration and login
 *
 */
public class FormView implements KeyListener {

    /**
     * JPanel form
     */
    private final JPanel formPanel;
    /**
     * JLabel welcome message
     */
    private JLabel messageLabel;
    /**
     * JTexfields for user input
     */
    private JTextField userNametextfield, passwordtextfield;
    /**
     * JCheckBox for user to select account type
     */
    private JCheckBox customerCheckBox, sellerCheckBox;
    /**
     * Boolean to check if form was completed
     */
    private boolean formCompleted;
    /**
     * String to represent account type
     */
    private String accType;

    /**
     * Constructor
     *
     * precondition formPanel != null
     * @param formPanel a JPanel
     */
    public FormView(JPanel formPanel) {
        this.formPanel = formPanel;
        formCompleted = false;
        this.formPanel.setLayout(new FlowLayout());
    }

    /**
     * Creates From Panel
     *
     * precondition formPanel != null
     * postconditon Form Panel created
     */
    public void createForm() {
        //enter name label
        JLabel userNameLabel = new JLabel("UserName :");
        userNameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        userNameLabel.setPreferredSize(new Dimension(140, 30));
        formPanel.add(userNameLabel);

        //textfield to enter name
        userNametextfield = new JTextField();
        userNametextfield.setFont(new Font("Arial", Font.PLAIN, 15));
        userNametextfield.setPreferredSize(new Dimension(180, 30));
        userNametextfield.addKeyListener(this);
        formPanel.add(userNametextfield);

        //CheckBoxes
        JLabel checkBoxlabel = new JLabel("Account type");
        checkBoxlabel.setFont(new Font("Arial", Font.PLAIN, 18));
        checkBoxlabel.setPreferredSize(new Dimension(125, 30));
        customerCheckBox = new JCheckBox("Customer");
        customerCheckBox.setFont(new Font("Arial", Font.PLAIN, 16));
        customerCheckBox.setPreferredSize(new Dimension(100, 30));
        sellerCheckBox = new JCheckBox("Seller");
        sellerCheckBox.setFont(new Font("Arial", Font.PLAIN, 16));
        sellerCheckBox.setPreferredSize(new Dimension(75, 30));
        //Add to Jpanel
        formPanel.add(checkBoxlabel);
        formPanel.add(customerCheckBox);
        formPanel.add(sellerCheckBox);

        //enter password label
        JLabel passwordLabel = new JLabel("Password :");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordLabel.setPreferredSize(new Dimension(140, 30));
        formPanel.add(passwordLabel);

        //textfield to enter password
        passwordtextfield = new JTextField();
        passwordtextfield.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordtextfield.setPreferredSize(new Dimension(180, 30));
        passwordtextfield.addKeyListener(this);
        formPanel.add(passwordtextfield);

        messageLabel = new JLabel();
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        messageLabel.setPreferredSize(new Dimension(300, 30));
        messageLabel.setForeground(Color.red);
        formPanel.add(messageLabel);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    /**
     * KeyListener used to check if the form was completed
     *
     * @param e Keyevent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        formCompleted = false;
        boolean emptyU = true;
        boolean emptyP = true;
        if (!"".equals(passwordtextfield.getText())) {
            emptyP = false;
        }
        if (!"".equals(userNametextfield.getText())) {
            emptyU = false;
        }
        if (!emptyP && !emptyU) {
            if (!customerCheckBox.isSelected() && !sellerCheckBox.isSelected()) {
                messageLabel.setText("Please Select the account type");
                passwordtextfield.setText(null);
            }
            if (customerCheckBox.isSelected() && sellerCheckBox.isSelected()) {
                messageLabel.setText("Please Select only one account type");
                passwordtextfield.setText(null);
                customerCheckBox.setSelected(false);
                sellerCheckBox.setSelected(false);
            }

            if (customerCheckBox.isSelected()) {
                formCompleted = true;
                setAccType("Customer");
            } else {
                formCompleted = true;
                setAccType("Seller");
            }
        }
    }

    /**
     * Return true if the from is completed
     *
     * precondition none
     * @return true if form was completed.Otherwise, returns false
     */
    public boolean isCompleted() {
        return this.formCompleted;
    }

    /**
     * Set a message on the JPanel
     *
     * precondition none
     * postcondition messageLabel
     * @param msg a string 
     */
    public void setMessage(String msg) {
        messageLabel.setText(msg);
    }

    /**
     * Clear all fields
     * precondition none
     * postconsition form is empty
     */
    public void clearTextField() {
        this.userNametextfield.setText("");
        this.passwordtextfield.setText("");
        this.customerCheckBox.setSelected(false);
        this.sellerCheckBox.setSelected(false);
        this.messageLabel.setText("");
        this.formCompleted = false;
    }

    /**
     * Set Account type
     * @param accType a string
     */
    public void setAccType(String accType) {
        this.accType = accType;
    }

    /**
     * Get Account type
     * @return a string representing account type
     */
    public String getAccType() {
        return accType;
    }

    /**
     * Get UserName
     *
     * @return a string representing the userName if formCompleted is true.
     */
    public String getUserName() {
        return (formCompleted) ? userNametextfield.getText() : "";
    }

    /**
     * Get Password
     *
     * @return a string representing the userName if formCompleted is true.
     */
    public String getPassword() {
        return (formCompleted) ? passwordtextfield.getText() : "";
    }
}
