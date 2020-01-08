/*
Project Topic: Shopping Cart
Project Title: Speedy Shopping and Selling
File Name: CreditCardView.java
 */
package scart.gui.user;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Credit Card Form
 *
 */
public class CreditCardView implements KeyListener {
    
    /**
     * Array used for day
     */
    private final String day[]
            = {"1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25",
                "26", "27", "28", "29", "30",
                "31"};
    /**
     * Array used for month
     */
    private final String month[]
            = {"Jan", "feb", "Mar", "Apr",
                "May", "Jun", "July", "Aug",
                "Sup", "Oct", "Nov", "Dec"};
    /**
     * Array used for year
     */
    private final String year[]
            = {"2019", "2020", "2021", "2022",
                "2023", "2024", "2025", "2026"};
    /**
     * JTextfield for credit card's owner
     */
    private final JTextField nameTextField;
    /**
     * JTextfield for credit card's number
     */
    private final JTextField creditcardTextField;
    /**
     * JTextfield for credit card's zip code
     */
    private final JTextField zipCodeTextField;
    /**
     * JComboBox for expiration day
     */
    private final JComboBox dayJComboBox;
    /**
     * JComboBox for expiration month
     */
    private final JComboBox monthJComboBox;
    /**
     * JComboBox for expiration year
     */
    private final JComboBox yearJComboBox;
    /**
     * Boolean to check if form was completed
     */
    private boolean formCompleted;

    /**
     * Constructor 
     * precondition mainPanel != null
     * postcondition Credit Card Form created
     * @param mainPanel a JPanel
     */
    public CreditCardView(JPanel mainPanel) {
        JPanel creditCardPanel = mainPanel;
        formCompleted = false;
        creditCardPanel.setLayout(new FlowLayout());

        //JLabel for title
        JLabel titleLable = new JLabel("Credit Card Information");
        titleLable.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLable.setPreferredSize(new Dimension(275, 60));
        creditCardPanel.add(titleLable);
        //JLabel for owner's name
        JLabel nameLabel = new JLabel("Enter Owner Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        nameLabel.setPreferredSize(new Dimension(150, 60));
        creditCardPanel.add(nameLabel);
        //JTextField for owner's name
        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        nameTextField.setPreferredSize(new Dimension(200, 30));
        nameTextField.addKeyListener(this);
        creditCardPanel.add(nameTextField);
        //JLabel for credit card number
        JLabel creditCardLabel = new JLabel("Credit Card Number:");
        creditCardLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        creditCardLabel.setPreferredSize(new Dimension(150, 60));
        creditCardPanel.add(creditCardLabel);
        //JTextField for credit card number
        creditcardTextField = new JTextField();
        creditcardTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        creditcardTextField.setPreferredSize(new Dimension(200, 30));
        creditcardTextField.addKeyListener(this);
        creditCardPanel.add(creditcardTextField);
        //JLabel for Expiration Date
        JLabel expirationDateJLabel = new JLabel("Expiration Date");
        expirationDateJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        expirationDateJLabel.setPreferredSize(new Dimension(100, 30));
        creditCardPanel.add(expirationDateJLabel);
        //JComboBox for month
        monthJComboBox = new JComboBox(month);
        monthJComboBox.setFont(new Font("Arial", Font.PLAIN, 15));
        monthJComboBox.setPreferredSize(new Dimension(60, 20));
        creditCardPanel.add(monthJComboBox);
        //JComboBox for day
        dayJComboBox = new JComboBox(day);
        dayJComboBox.setFont(new Font("Arial", Font.PLAIN, 15));
        dayJComboBox.setPreferredSize(new Dimension(50, 20));
        creditCardPanel.add(dayJComboBox);
        //JComboBox for year
        yearJComboBox = new JComboBox(year);
        yearJComboBox.setFont(new Font("Arial", Font.PLAIN, 15));
        yearJComboBox.setPreferredSize(new Dimension(60, 20));
        creditCardPanel.add(yearJComboBox);
        //JLabel for zip Code
        JLabel zipCodeLabel = new JLabel("Zip Code: ");
        zipCodeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        zipCodeLabel.setPreferredSize(new Dimension(150, 60));
        creditCardPanel.add(zipCodeLabel);
        //JTextField for zip Code
        zipCodeTextField = new JTextField();
        zipCodeTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        zipCodeTextField.setPreferredSize(new Dimension(150, 30));
        zipCodeTextField.addKeyListener(this);
        creditCardPanel.add(zipCodeTextField);
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
     * @param e KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        formCompleted = false;
        boolean emptyN = true;
        boolean emptyC = true;
        boolean emptyZ = true;
        if (!"".equals(nameTextField.getText())) {
            emptyN = false;
        }
        if (!"".equals(creditcardTextField.getText())) {
            emptyC = false;
        }
        if (!"".equals(zipCodeTextField.getText())) {
            emptyZ = false;
        }

        if (!emptyN && !emptyC && !emptyZ) {
            formCompleted = true;
        }
    }

     /**
     * Return true if the from is completed
     *
     * precondition none
     * @return true if form was completed.Otherwise, returns false
     */
    public boolean getFormCompleted() {
        return this.formCompleted;
    }

    /**
     * Get owner's name
     * @return a string
     */
    public String getName() {
        return (formCompleted) ? nameTextField.getText() : "";
    }

    /**
     * Get credit card number
     * @return s string
     */
    public String getCreditCard() {
        return (formCompleted) ? creditcardTextField.getText() : "";
    }

    /**
     * Get zip code
     * @return a string
     */
    public String getZIPCode() {
        return (formCompleted) ? zipCodeTextField.getText() : "";
    }

    /**
     * Get Expiration Date
     * @return a string
     */
    public String getExpirationDate() {
        String expirationDate = (String) monthJComboBox.getSelectedItem();
        expirationDate += "/" + dayJComboBox.getSelectedItem();
        expirationDate += "/" + yearJComboBox.getSelectedItem();
        return (formCompleted) ? expirationDate : "";
    }
}
