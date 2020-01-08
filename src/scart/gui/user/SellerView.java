/*
Project Topic: Shopping Cart
Project Title: Speedy Shopping and Selling
File Name: SellerView.java
 */
package scart.gui.user;


import scart.data.Order;
import scart.data.ProductInventory;
import scart.data.Product;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Creates SellerView
 */
public class SellerView extends UserView implements ActionListener {

    
    private ProductInventory inventory;
    private final int MAX_NUMB_PRODUCTS = 10;
    //Seller Inventory Panel
    private JTextField[] updateQuantity;
    private JButton nextPagebtn, previousPagebtn;
    //Seller Add Section
    private AddProductView addProductView;
    private JButton submitbtn;
    private String sellerName;
    private JButton invButton, addButton;
    private JButton salesdataBtn;
    private Order myOrders;

  
    /**
     * Constructor 
     * precondition JPanel != null 
     * postcondition Seller View created
     *
     * @param topPanel a JPanel
     * @param mainPanel a JPanel
     * @param bottomPane a JPanel
     */

    public SellerView(JPanel topPanel, JPanel mainPanel, JPanel bottomPane) {
        super(topPanel, mainPanel, bottomPane);
        updateQuantity = new JTextField[MAX_NUMB_PRODUCTS];
    }

    /**
     * Adds components to topPanel precondition topPanel != null postcondition
     * topPanel created
     *
     * @param userName a string
     */
    @Override
    public void topPanel(String userName) {
        this.sellerName = userName;
        inventory = new ProductInventory(sellerName);
        getTopJPanel().setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        //Welcome Message
        getWelcomeLabel().setText("Welcome Seller: " + userName);
        getWelcomeLabel().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));

        //Creating JButtons for topPanel
        invButton = new JButton("Inventory");
        invButton.setPreferredSize(new Dimension(150, 35));
        invButton.addActionListener(this);
        addButton = new JButton("Add Product");
        addButton.setPreferredSize(new Dimension(150, 35));
        addButton.addActionListener(this);
        salesdataBtn = new JButton("Sales Data");
        salesdataBtn.setPreferredSize(new Dimension(150, 35));
        salesdataBtn.addActionListener(this);

        //Add Components to topPanel
        getTopJPanel().add(getWelcomeLabel());
        getTopJPanel().add(invButton);
        getTopJPanel().add(addButton);
        getTopJPanel().add(salesdataBtn);
    }

    /**
     * Manages components on the mainPanel precondition mainPanel != null
     * postcondition mainPanel created
     *
     * @param panelType a string representing the mainPanel to display
     */
    @Override
    public void mainPanel(String panelType) {

        if (getMainPanel() != null) {
            getMainPanel().removeAll();
            getMainPanel().revalidate();
            getMainPanel().repaint();
        }

        switch (panelType) {
            case "Inventory":
                if (inventory.getSize() < 1) {
                    createPOPUP("Message", "Inventory is Empty. Go to Add Product");
                    break;
                } else {
                    getMainPanel().setLayout(new GridLayout(5, 3, 10, 10));
                    getMainPanel().setBorder(new EmptyBorder(10, 10, 10, 10));
                    inventoryPanel();
                    bottomPanel("Inventory");
                    System.out.println("Number of products displayed: " + getLastProductDisplayed());
                }
                break;
            case "Add Product":
                getMainPanel().setLayout(new FlowLayout());
                addProductPanel();
                bottomPanel("Add Product");
                break;
            case "Sales Data":
                getMainPanel().setLayout(new FlowLayout());
                salesDataPanel();
                bottomPanel("Sales Data");
                break;
        }
    }

    /**
     * Manages Components on the bottomPanel precondition bottomPanel != null
     * postconditon bottomPanel created
     *
     * @param bottomType a string representing the bottomPanel to display
     */
    @Override
    public void bottomPanel(String bottomType) {
        if (getBottomPanel() != null) {
            getBottomPanel().removeAll();
            getBottomPanel().revalidate();
            getBottomPanel().repaint();
        }
        switch (bottomType) {
            case "Inventory":
                getBottomPanel().setLayout(new FlowLayout(FlowLayout.CENTER, 150, 10));
                //Creating nextPage BTN
                nextPagebtn = new JButton("Next");
                nextPagebtn.setPreferredSize(new Dimension(150, 35));
                nextPagebtn.addActionListener(this);
                //Creating Previous BTN
                previousPagebtn = new JButton("Previous");
                previousPagebtn.setPreferredSize(new Dimension(150, 35));
                previousPagebtn.addActionListener(this);
                //Add to Panel
                getBottomPanel().add(previousPagebtn);
                getBottomPanel().add(nextPagebtn);
                break;
            case "Add Product":
                submitbtn = new JButton("Save Product");
                submitbtn.setPreferredSize(new Dimension(150, 35));
                submitbtn.addActionListener(this);
                getBottomPanel().add(submitbtn);
                break;
            case "Sales Data":
                break;
        }

    }
     /**
     * Creates inventory View 
     * precondition inventory.size greater than 0
     * postcondtion InventoryPanel created
     */
    public void inventoryPanel() {

        int invSize = inventory.getSize();
        setLastProductDisplayed(getFirstProductDisplayed());
        for (int i = 0; i < MAX_NUMB_PRODUCTS; i++) {
            //JPanel for each product in inventory
            setProductPanel(i, new JPanel(new BorderLayout(5, 5)));
            //For Button and JTextField
            setProdPanelBottom(i, new JPanel(new FlowLayout()));
            //Update Quantity Button
            setProdBTN1(i, new JButton("New QTY:"));
            getProdBTN1(i).addActionListener(this);
            //Button to Panel
            getProdPanelBottom(i).add(getProdBTN1(i));
            //Quantity textfield
            updateQuantity[i] = new JTextField();
            updateQuantity[i].setPreferredSize(new Dimension(45, 30));
            getProdPanelBottom(i).add(updateQuantity[i]);
            //Product Quantity
            Product p = inventory.getProductAt(getLastProductDisplayed());
            //TextArea 
            setProdInfoPanel(i, new JTextArea(p.toString()));
            //Adding Comp
            getProductPanel(i).add(getProdInfoPanel(i), BorderLayout.NORTH);
            if (p.getQuantity() == 0) {
                getProductPanel(i).setBorder(new LineBorder(Color.RED));
            } else {
                getProductPanel(i).setBorder(new LineBorder(Color.BLACK));
            }
            getProductPanel(i).add(getProdPanelBottom(i), BorderLayout.SOUTH);
            getMainPanel().add(getProductPanel(i));
            setLastProductDisplayed(getLastProductDisplayed() + 1);
            if (i + getFirstProductDisplayed() == invSize - 1) {
                break;
            }
        }
    }
    /**
     * Creates  AddProduct Panel 
     * precondition mainPanel != null
     * postcondtion addProductPanel created
     */
    public void addProductPanel() {
        addProductView = new AddProductView(getMainPanel());
    }

    /**
     * Creates salesDataPanel Panel 
     * precondition mainPanel != null
     * postcondtion addProductPanel created
     */
    public void salesDataPanel() {
        myOrders = new Order(sellerName);
        ArrayList<String[]> ordersInfo = myOrders.readOrders();
        int productSold = 0;
        double cost = 0;
        double revenue = 0;
        if (ordersInfo !=null && ordersInfo.size() > 0) {
            for (String[] strings : ordersInfo) {
                System.out.println(Arrays.toString(strings));
                productSold += Integer.valueOf(strings[4]);
                cost += Double.valueOf(strings[2]) * Integer.valueOf(strings[4]);
                revenue += Double.valueOf(strings[3]) * Integer.valueOf(strings[4]);
            }
        }
        double profit = revenue - cost;
        SalesDataView salesDataView = new SalesDataView(getMainPanel(), productSold, cost, revenue, profit);
    }

     /**
     * Checking which JButton was pressed.
     *
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean found = false;
        //Checking inventory Button
        if (e.getSource() == invButton) {
            //Getting Product
            mainPanel("Inventory");
            found = true;
        }
        //Checking add Product
        if (!found) {
            if (e.getSource() == addButton) {
                mainPanel("Add Product");
                found = true;
            }
        }
        //Checking submit 
        if (!found) {
            if (e.getSource() == submitbtn) {
                if (addProductView.getFormCompleted()) {
                    //Save product
                    String productName = addProductView.getProductName();
                    double invoicePrice = Double.valueOf(addProductView.getInvoicePrice());
                    double sellingPrice = Double.valueOf(addProductView.getSellingPrice());
                    int quantity = Integer.valueOf(addProductView.getQuantity());
                    //Add Product to inventory
                    inventory.addProductToInventory(sellerName, productName, invoicePrice, sellingPrice, quantity);
                    //Update Inventory
                    inventory = new ProductInventory(sellerName);
                    mainPanel("Add Product");
                    createPOPUP("Message", "Product Added to Inventory");
                } else {
                    createPOPUP("Message", "Please Complete Form");
                }
                found = true;
            }
        }
        //Checking Sales data 
        if (!found) {
            if (e.getSource() == salesdataBtn) {
                mainPanel("Sales Data");
                bottomPanel("Sales Data");
                found = true;
            }
        }
        //Checking next Page button
        if (!found) {
            if (e.getSource() == nextPagebtn) {
                if (getLastProductDisplayed() != inventory.getSize()) {
                    //Update the first Product displayed
                    setFirstProductDisplayed(getLastProductDisplayed());
                    //Update mainPanel
                    mainPanel("Inventory");
                    found = true;
                    previousPagebtn.setEnabled(true);
                } else {
                    //Disable button if end of inventory
                    nextPagebtn.setEnabled(false);
                }
            }
        }
        //Checking previous Page button
        if (!found) {
            if (e.getSource() == previousPagebtn) {
                if (getLastProductDisplayed() - MAX_NUMB_PRODUCTS != 0 && getLastProductDisplayed() - MAX_NUMB_PRODUCTS > 0) {
                    //Update the first Product displayed
                    setFirstProductDisplayed(getFirstProductDisplayed() - MAX_NUMB_PRODUCTS);
                    //Update mainPanel
                    mainPanel("Inventory");
                    nextPagebtn.setEnabled(true);
                } else {
                    //Disable button if start of inventory
                    previousPagebtn.setEnabled(false);
                }
            }
        }
        //Checking new Quantity 
        if (!found) {
            for (int j = 0; j < MAX_NUMB_PRODUCTS; j++) {
                if (getProdBTN1(j) == null) {
                    break;
                }
                if (e.getSource() == getProdBTN1(j)) {
                    if ("".equals(updateQuantity[j].getText())) {
                        //Error message if textfield is empty
                        createPOPUP("Message", "Please enter a new Quantity");
                    } else {
                        //Getting Product and new quantity
                        int newQTY = Integer.valueOf(updateQuantity[j].getText());
                        Product oldProduct = inventory.getProductAt(getFirstProductDisplayed() + j);
                        String seller = oldProduct.getSellerName();
                        String productName = oldProduct.getProductName();
                        double invoicePrice = oldProduct.getInvoicePrice();
                        double sellingPrice = oldProduct.getProductPrice();
                        int id = oldProduct.getId();
                        //Create new Product
                        Product newProduct = new Product(id, seller, productName, invoicePrice, sellingPrice, newQTY);
                        //Update Inventory
                        inventory.updateInventory("Seller", newProduct);
                        //Update main Panel
                        mainPanel("Inventory");
                    }
                }
                //Stop loop if the button was found
                if (found) {
                    break;
                }
            }
        }
    }
}
