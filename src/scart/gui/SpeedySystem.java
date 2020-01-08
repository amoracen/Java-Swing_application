/*
Project Topic: Shopping Cart
Project Title: Speedy Shopping and Selling
@author: Alejandro Moracen
File Name: SpeedySystem.java
 */
package scart.gui;

import scart.gui.user.SellerView;
import scart.gui.user.CustomerView;
import scart.data.AccountDB;
import scart.data.Customer;
import scart.data.ProductInventory;
import scart.data.Seller;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that controls the user interaction with the System
 */
public class SpeedySystem implements ActionListener {

    /**
     * Application JFrame
     */
    private final JFrame frame;

    /**
     * JButton for registration JPanel
     */
    private final JButton registerBtn;
    /**
     * JButton for login JPanel
     */
    private final JButton loginBtn;
    /**
     * Application JPanels
     */
    private JPanel topPanel, mainPanel, FormPanel, bottomPanel;
    /**
     * JLabel to display messages
     */
    private final JLabel topLabel;
    /**
     * Decorator for mainPanel
     */
    //private final WelcomeView welcomeView;
    /**
     * Decorator for mainPanel
     */
    private FormView formView;
    /**
     * Decorator for mainPanel
     */
    private CustomerView customerView;
    /**
     * Decorator for mainPanel
     */
    private SellerView sellerView;

    /**
     * Customer object
     */
    private Customer customer;
    /**
     * Seller object
     */
    private Seller seller;
    //private ProductInventory inventory;
    /**
     * AccountDB object
     */
    private final AccountDB accountsDB;

    /**
     * Constructor creates new JFrame, uses decorator WelcomeView, and add
     * ActionListeners to JButtons
     *
     * precondition none
     * postcondition JFrame is visible
     */
    public SpeedySystem() {
        accountsDB = new AccountDB();
        frame = new JFrame("Speedy Shopping and Selling");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        //Welcome Label
        topLabel = new JLabel();
        //Top Panel
        topPanel = new JPanel();
        //Panel for JButtons
        mainPanel = new JPanel();
        //Register and Login Buttons
        registerBtn = new JButton();
        loginBtn = new JButton();
        //Create Welcome View
        WelcomeView welcomeView = new WelcomeView(topLabel, topPanel, mainPanel);
        welcomeView.topPanel();
        welcomeView.mainPanel(registerBtn, loginBtn);
        //Add Listeners
        registerBtn.addActionListener(this);
        loginBtn.addActionListener(this);
        //Add Top Panel to frame
        frame.add(topPanel, BorderLayout.NORTH);
        //Add Main panel to frame
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setBounds(600, 400, 600, 200);
        frame.setVisible(true);
    }

    /**
     * Update JFrame Bounds
     *
     * precondition JFrame was created
     * postcondition JFrame bounds updated
     * @param x new x-coordinate
     * @param y new y-coordinate
     * @param w new width
     * @param h new height
     */
    public void changeFrameBounds(int x, int y, int w, int h) {
        frame.setBounds(x, y, w, h);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Updates FromView for Registration and Login
     *
     * precondition JPanel mainPanel != null
     * postcondition FormView != null
     * @param form a string representing the type of form
     */
    public void updateFormView(String form) {
        if (mainPanel != null) {
            mainPanel.removeAll();
        }
        //Update topLabel
        topLabel.setText("Welcome to " + form + " Page");

        switch (form) {
            case "Registration":
                registerBtn.setPreferredSize(new Dimension(100, 25));
                mainPanel.add(registerBtn);
                frame.add(mainPanel, BorderLayout.SOUTH);
                break;
            case "Login":
                loginBtn.setPreferredSize(new Dimension(100, 25));
                mainPanel.add(loginBtn);
                frame.add(mainPanel, BorderLayout.SOUTH);
                break;
        }
        if (FormPanel == null && formView == null) {
            //Creating the form Panel
            FormPanel = new JPanel();
            formView = new FormView(FormPanel);
            formView.createForm();
            frame.add(FormPanel, BorderLayout.CENTER);
        } else {
            //Form created by registration
            formView.clearTextField();
        }
        changeFrameBounds(600, 400, 450, 300);
    }

    /**
     * Updates UserView between Customer and Seller
     *
     * precondition AccountDB.checkPassword == true
     * @param user a string
     * @param userName a string
     * @param password a string
     */
    public void updateUserView(String user, String userName, String password) {
        switch (user) {
            case "Customer":
                createCustomerView(userName, password);
                break;
            case "Seller":
                createSellerView(userName, password);
                break;
        }
    }

    /**
     * Creates Customer View
     *
     * precondition JFrame != null
     * postCondition customer and customerView objects created
     * @param userName a string
     * @param password a string
     */
    public void createCustomerView(String userName, String password) {
        //Creating Customer
        customer = new Customer(userName, password);
        //Creating Customer GUI
        topPanel = new JPanel();
        mainPanel = new JPanel();
        bottomPanel = new JPanel();
        customerView = new CustomerView(topPanel, mainPanel, bottomPanel);
        //TOP Panel//Main Panel//Bottom Panel
        customerView.createHomePage(customer.getUserName(), "Inventory", "Inventory");
        //Adding panels to frame
        addJPaneltoFrame(topPanel, mainPanel, bottomPanel);
    }

    /**
     * Adds JPanels to JFrame
     *
     * precondition JPanels != null
     * postcondition JFrame updated
     * @param topPanel a JPanel
     * @param mainJPanel a JPanel
     * @param bottomJPanel a JPanel
     */
    public void addJPaneltoFrame(JPanel topPanel, JPanel mainJPanel, JPanel bottomJPanel) {
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainJPanel, BorderLayout.CENTER);
        frame.add(bottomJPanel, BorderLayout.SOUTH);
        changeFrameBounds(500, 50, 900, 800);
    }

    /**
     * Creates Seller View
     *
     * precndition JFrame != null
     * postCondition seller and sellerView objects created
     * @param userName a string
     * @param password a string
     */
    public void createSellerView(String userName, String password) {
        //Creating Seller
        seller = new Seller(userName, password);
        //Creating Seller GUI
        topPanel = new JPanel();
        mainPanel = new JPanel();
        bottomPanel = new JPanel();
        sellerView = new SellerView(topPanel, mainPanel, bottomPanel);
        //TOP Panel//Main Panel//Bottom Panel
        sellerView.createHomePage(seller.getUserName(), "Inventory", "Inventory");
        //Adding panels to frame
        addJPaneltoFrame(topPanel, mainPanel, bottomPanel);
    }

    /**
     * ActionListener for JButtons
     *
     * precondition JButtons were attached to addActionListener()
     * @param e Actionevent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Stops if JButton found
        boolean found = false;
        //Checking if registrationBtn was pressed
        if (e.getSource() == registerBtn && !"Register".equals(registerBtn.getText())) {
            registerBtn.setText("Register");
            updateFormView("Registration");
            found = true;
        } else if (e.getSource() == registerBtn && "Register".equals(registerBtn.getText())) {
            found = true;
            //Checking if Registration Form was completed
            if (formView.isCompleted()) {
                //Creating new Account
                if (accountsDB.createAccount(formView.getAccType(), formView.getUserName(), formView.getPassword())) {
                    //Redirecting to Login Form
                    loginBtn.setText("Login");
                    updateFormView("Login");
                } else {
                    //Displayed Fail message
                    formView.setMessage("Registration Failed : UserName already used");
                }
            } else {
                //Displayed Fail message
                formView.setMessage("Form is incomplete");
            }
        }
        //Checking if registrationBtn was pressed
        if (!found) {
            if (e.getSource() == loginBtn && !"Login".equals(loginBtn.getText())) {
                loginBtn.setText("Login");
                updateFormView("Login");
            } else if (e.getSource() == loginBtn && "Login".equals(loginBtn.getText())) {
                //Checking if Login Form was completed
                if (formView.isCompleted()) {
                    //Checking credentials
                    if (accountsDB.checkPassword(formView.getAccType(), formView.getUserName(), formView.getPassword())) {
                        frame.remove(FormPanel);
                        frame.remove(loginBtn);
                        frame.remove(registerBtn);
                        frame.remove(topPanel);
                        frame.remove(mainPanel);
                        //Create UserView
                        updateUserView(formView.getAccType(), formView.getUserName(), formView.getPassword());
                    } else {
                        //Displayed Fail message
                        formView.setMessage("Login Failed");
                    }
                } else {
                    //Displayed Fail message
                    formView.setMessage("Form is incomplete");
                }
            }
        }
    }

    /**
     * Main method
     *
     * @param args none
     */
    public static void main(String[] args) {

        //Creating two folder to save the users
        String Path = "./AccountDB/Users/";
        String[] dirName = new String[]{Path.concat("Customer"), Path.concat("Seller")};
        for (int i = 0; i < 2; i++) {
            File directory = new File(dirName[i]);
            if (!directory.exists()) {
                directory.mkdirs();
            }
        }

        //Creating a folder for the Inventory
        String InvDir = "./Inventory/";
        File directory = new File(InvDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        //Create three Sellers
        AccountDB accounts = new AccountDB();
        accounts.createAccount("Seller", "Alejandro", "Moracen");
        accounts.createAccount("Customer", "customer1", "customer1");

        File alejandroInv = new File("./Inventory/Alejandro.csv");
        if (!alejandroInv.exists()) {
            //Creating products
            ProductInventory inventory = new ProductInventory("Alejandro");
            inventory.addProductToInventory("Alejandro", "HP Laptop", 300, 400, 5);
            inventory.addProductToInventory("Alejandro", "Lenovo ThinkPad", 600, 750, 15);
            inventory.addProductToInventory("Alejandro", "Dell Laptop", 150, 330, 20);
            inventory.addProductToInventory("Alejandro", "Dell Latitude", 100, 269, 0);
            inventory.addProductToInventory("Alejandro", "Alienware Laptop", 1200, 2049, 10);
            inventory.addProductToInventory("Alejandro", "Dell Inspiron", 250, 379, 0);
            inventory.addProductToInventory("Alejandro", "Alienware Aurora", 600, 999, 22);
            inventory.addProductToInventory("Alejandro", "HP Pavilion", 400, 899, 3);
            inventory.addProductToInventory("Alejandro", "Apple iMac", 125, 369, 0);
            inventory.addProductToInventory("Alejandro", "HP Desktop", 75, 220, 10);
            inventory.addProductToInventory("Alejandro", "Dell Alienware Monitor", 200, 625, 10);
            inventory.addProductToInventory("Alejandro", "Dell Full HD Monitor", 75, 145, 0);
            inventory.addProductToInventory("Alejandro", "HP EliteDisplay", 40, 125, 3);
            inventory.addProductToInventory("Alejandro", "Apple MacBook", 200, 450, 15);
            inventory.addProductToInventory("Alejandro", "Lenovo Legion", 350, 800, 50);
        }
        //Start Application
        SpeedySystem speedySystem = new SpeedySystem();
    }
}
