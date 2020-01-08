/*
Project Topic: Shopping Cart
Project Title: Speedy Shopping and Selling
File Name: CustomerView.java
 */
package scart.gui.user;

import scart.data.ProductInventory;
import scart.data.Product;
import scart.data.ShoppingCart;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Creates Customer View
 *
 */
public class CustomerView extends UserView implements ActionListener {

    /**
     * An inventory object
     */
    private ProductInventory inventory;
    /**
     * A shoppingCart object
     */
    private final ShoppingCart myShoppingCart;
    /*
    * Number of product per page
     */
    private final int MAX_NUMB_PRODUCTS = 10;

    /**
     * JTextfield to enter new quantity
     */
    private JTextField[] updateQuantity;
    /**
     * JButton to move between pages
     */
    private JButton nextPagebtn, previousPagebtn;
    /**
     * JButtons
     */
    private JButton topButton, checkOutbtn, submitbtn;

    /**
     * JButtons to add product to cart
     */
    private final JButton[] addToCartBtn;
    /**
     * JButton to update new quantity
     */
    private JButton[] newQtyBtn;

    /**
     * JPanel for ShoppingCart
     */
    private JPanel shoppingCartPanel;
    /**
     * JPanels for ShoppingCart products
     */
    private JPanel[] cartProducts;
    /**
     * JScroollPane
     */
    private JScrollPane scrollPanel;
    /**
     * JPanel for JButton and JTextfield
     */
    private JPanel[] qtyPanel;
    /**
     * JTextArea for Shopping cart's Products Info
     */
    private JTextArea[] cartProductInfo;
    /**
     * Credit Card From View
     */
    private CreditCardView creditCardView;

    /**
     * Constructor 
     * precondition JPanel != null 
     * postcondition Customer View
     * created
     *
     * @param topPanel a JPanel
     * @param mainPanel a JPanel
     * @param bottomPane a JPanel
     */
    public CustomerView(JPanel topPanel, JPanel mainPanel, JPanel bottomPane) {
        super(topPanel, mainPanel, bottomPane);
        inventory = new ProductInventory();
        myShoppingCart = new ShoppingCart();
        addToCartBtn = new JButton[MAX_NUMB_PRODUCTS];
    }

    /**
     * Adds components to topPanel precondition topPanel != null postcondition
     * topPanel created
     *
     * @param userName a string
     */
    @Override
    public void topPanel(String userName) {
        getTopJPanel().setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
        //Total label
        getTotalLabel().setText("Total: " + String.valueOf(myShoppingCart.getTotal()));
        getTotalLabel().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        //Welcome Message
        getWelcomeLabel().setText("Welcome: " + userName);
        getWelcomeLabel().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        //Cart JButton
        topButton = new JButton("Shopping Cart");
        topButton.setPreferredSize(new Dimension(150, 35));

        //Action Listener
        topButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("Shopping Cart".equals(topButton.getText())) {
                    if (myShoppingCart.getSize() < 1) {
                        createPOPUP("Message", "Shopping Cart is Empty");
                    } else {
                        topButton.setText("Back");
                        mainPanel("Shopping Cart");
                    }
                } else {
                    if (scrollPanel != null) {
                        getMainPanel().remove(scrollPanel);
                    }
                    getMainPanel().remove(shoppingCartPanel);
                    getMainPanel().revalidate();
                    getMainPanel().repaint();
                    topButton.setText("Shopping Cart");
                    mainPanel("Inventory");
                }
            }
        });
        //Add Components to topPanel
        getTopJPanel().add(getWelcomeLabel());
        getTopJPanel().add(getTotalLabel());
        getTopJPanel().add(topButton);
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
        //Switch between the three options
        switch (panelType) {
            case "Inventory":
                if (inventory.getSize() < 1) {
                    createPOPUP("Message", "Inventory is Empty");
                } else {
                    getMainPanel().setLayout(new GridLayout(5, 3, 10, 10));
                    getMainPanel().setBorder(new EmptyBorder(10, 10, 10, 10));
                    inventoryPanel();
                    bottomPanel("Inventory");
                    //System.out.println("Number of products displayed: " + getLastProductDisplayed());
                }
                break;
            case "Shopping Cart":
                getMainPanel().setLayout(new FlowLayout());
                shoppinCartPanel();
                bottomPanel("Checkout");
                break;
            case "Checkout":
                getMainPanel().setLayout(new FlowLayout());
                createCheckoutView();
                bottomPanel("Submit Order");
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
            case "Checkout":
                checkOutbtn = new JButton("Go To Checkout");
                checkOutbtn.setPreferredSize(new Dimension(150, 35));
                checkOutbtn.addActionListener(this);
                getBottomPanel().add(checkOutbtn);
                break;
            case "Submit Order":
                submitbtn = new JButton("Submit Order");
                submitbtn.setPreferredSize(new Dimension(150, 35));
                submitbtn.addActionListener(this);
                getBottomPanel().add(submitbtn);
                break;
        }
    }

    /**
     * Creates inventory View precondition inventory.size greater than 0
     * postcondtion InventoryPanel created
     */
    public void inventoryPanel() {
        int invSize = inventory.getSize();
        System.out.println("Number of product in the inventory: " + invSize);
        setLastProductDisplayed(getFirstProductDisplayed());
        System.out.println("I am starting with: " + getFirstProductDisplayed());

        //Dsiplay 10 products per window
        for (int i = 0; i < MAX_NUMB_PRODUCTS; i++) {
            //Creating a JPanel for a product
            setProductPanel(i, new JPanel(new BorderLayout(5, 5)));
            //Creating a JPanel for buttons
            setProdPanelBottom(i, new JPanel(new FlowLayout()));
            //Add to cart button
            addToCartBtn[i] = new JButton("Add to Cart");
            addToCartBtn[i].addActionListener(this);
            //Details button
            setProdBTN1(i, new JButton("Details"));
            getProdBTN1(i).addActionListener(this);
            //Adding button to panel
            getProdPanelBottom(i).add(addToCartBtn[i]);
            getProdPanelBottom(i).add(getProdBTN1(i));
            //Checking product quantity
            Product p = inventory.getProductAt(getLastProductDisplayed());
            if (p.getQuantity() == 0) {
                addToCartBtn[i].setEnabled(false);
            }
            //Creating textArea with product information
            setProdInfoPanel(i, new JTextArea(p.toString()));
            //Adding Components to the Product JPanel
            getProductPanel(i).add(getProdInfoPanel(i), BorderLayout.NORTH);
            getProductPanel(i).setBorder(new LineBorder(Color.GRAY));
            getProductPanel(i).add(getProdPanelBottom(i), BorderLayout.SOUTH);
            //Adding Components to the  mainPanel
            getMainPanel().add(getProductPanel(i));
            setLastProductDisplayed(getLastProductDisplayed() + 1);
            //Break if i is equal to inventory's size
            if (i + getFirstProductDisplayed() == invSize - 1) {
                break;
            }
        }
    }

    /**
     * Creates shopping Cart Panel precondition myShoppingCart.size greater than
     * 0 postcondtion shoppinCartPanel created
     */
    public void shoppinCartPanel() {

        updateQuantity = new JTextField[myShoppingCart.getSize()];
        cartProducts = new JPanel[myShoppingCart.getSize()];
        qtyPanel = new JPanel[myShoppingCart.getSize()];
        cartProductInfo = new JTextArea[myShoppingCart.getSize()];
        newQtyBtn = new JButton[myShoppingCart.getSize()];

        int rows = (int) (myShoppingCart.getSize() + 4 - 1) / 4;
        //System.out.println("Number of rows: " + rows);
        shoppingCartPanel = new JPanel(new GridLayout(rows, 4, 10, 10));
        shoppingCartPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < myShoppingCart.getSize(); i++) {
            //Creating a JPanel for a product
            cartProducts[i] = new JPanel(new BorderLayout(10, 10));
            //Creating a JPanel for button and textfield
            qtyPanel[i] = new JPanel(new FlowLayout());
            //Update Quantity Button
            newQtyBtn[i] = new JButton("New QTY:");
            newQtyBtn[i].addActionListener(this);
            updateQuantity[i] = new JTextField();
            updateQuantity[i].setPreferredSize(new Dimension(45, 30));
            //Add to panel
            qtyPanel[i].add(newQtyBtn[i]);
            qtyPanel[i].add(updateQuantity[i]);
            //Product Information
            Product p = myShoppingCart.getProductAt(i);
            //Creating textArea with product information
            cartProductInfo[i] = new JTextArea(p.toString());
            cartProductInfo[i].setEditable(false);
            cartProductInfo[i].setOpaque(false);
            cartProductInfo[i].setBorder(new EmptyBorder(10, 10, 10, 10));
            //Adding Components to the JPanel
            cartProducts[i].add(cartProductInfo[i], BorderLayout.NORTH);
            cartProducts[i].setBorder(new LineBorder(Color.BLACK));
            cartProducts[i].add(qtyPanel[i], BorderLayout.SOUTH);
            //Add to shoppingCartPanel
            shoppingCartPanel.add(cartProducts[i]);
        }//EOF for loop

        //Add scrollPanel to shoppingCart if more than 8 products
        if (myShoppingCart.getSize() > 8) {
            createScrollPanel(shoppingCartPanel);
            //Adding to Main Panel
            getMainPanel().add(scrollPanel);
        } else {
            //Adding to Main Panel
            getMainPanel().add(shoppingCartPanel);
        }

    }

    /**
     * Adds scrollPanel to a JPanel precondition panel != null postcondition
     * scrollPane added to panel
     *
     * @param panel a JPanel
     */
    public void createScrollPanel(JPanel panel) {
        scrollPanel = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPanel.setPreferredSize(new Dimension(710, 600));
    }

    /**
     * Creates checkout JPanel precondition myShoppingCart.size greater than 0
     * postcondtion Checkout Panel created
     */
    public void createCheckoutView() {
        JPanel creditCartJPanel = new JPanel();
        creditCartJPanel.setBorder(new LineBorder(Color.RED));
        creditCardView = new CreditCardView(creditCartJPanel);

        getMainPanel().setLayout(new GridLayout(1, 2, 10, 10));
        int rows = (int) (myShoppingCart.getSize() + 2 - 1) / 2;
        shoppingCartPanel = new JPanel(new GridLayout(rows, 2, 10, 10));

        for (int i = 0; i < myShoppingCart.getSize(); i++) {
            //Creating a JPanel for a product
            cartProducts[i] = new JPanel(new BorderLayout(10, 10));
            //Product Information
            Product p = myShoppingCart.getProductAt(i);
            //Creating textArea with product information
            cartProductInfo[i] = new JTextArea(p.toString());
            cartProductInfo[i].setEditable(false);
            cartProductInfo[i].setOpaque(false);
            cartProductInfo[i].setBorder(new EmptyBorder(10, 10, 10, 10));
            //Adding Components to the JPanel
            cartProducts[i].add(cartProductInfo[i], BorderLayout.NORTH);
            cartProducts[i].setBorder(new LineBorder(Color.GRAY));
            //Add to shoppingCartPanel Panel
            shoppingCartPanel.add(cartProducts[i]);
        }//EOF for loop

        //Add scrollPanel to shoppingCart
        if (myShoppingCart.getSize() > 8) {
            createScrollPanel(shoppingCartPanel);
            //Adding to Main Panel
            getMainPanel().add(scrollPanel);
            getMainPanel().add(creditCartJPanel);
        } else {
            //Adding to Main Panel
            getMainPanel().add(shoppingCartPanel);
            getMainPanel().add(creditCartJPanel);
        }
    }

    /**
     * Checking which JButton was pressed.
     *
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean found = false;
        //Checking addToCart Button
        for (int j = 0; j < MAX_NUMB_PRODUCTS; j++) {
            if (addToCartBtn[j] == null) {
                break;
            }
            if (e.getSource() == addToCartBtn[j]) {
                //Getting Product
                Product p = inventory.getProductAt(getFirstProductDisplayed() + j);
                found = true;
                //Update Total
                myShoppingCart.setTotal(p.getProductPrice());
                //Update JLabel
                getTotalLabel().setText("Total: $" + String.valueOf(myShoppingCart.getTotal()));
                //Add to shopping Cart
                myShoppingCart.addProduct(new Product(p.getId(), p.getSellerName(), p.getProductName(), p.getInvoicePrice(), p.getProductPrice(), 1));
            }
            //Stop loop if the button was found
            if (found) {
                break;
            }
        }
        //Checking read Details
        if (!found) {
            for (int j = 0; j < MAX_NUMB_PRODUCTS; j++) {
                if (getProdBTN1(j) == null) {
                    break;
                }
                if (e.getSource() == getProdBTN1(j)) {
                    //Getting Product
                    Product pDescription = inventory.getProductAt(getFirstProductDisplayed() + j);
                    //Creating popup message
                    createPOPUP("Product Description", pDescription.toString());
                    found = true;
                }
                //Stop loop if the button was found
                if (found) {
                    break;
                }
            }
        }
        //Checking next Page button
        if (!found) {
            if (e.getSource() == nextPagebtn) {
                found = true;
                if (getLastProductDisplayed() != inventory.getSize()) {
                    //Update the first Product displayed
                    setFirstProductDisplayed(getLastProductDisplayed());
                    //Update mainPanel
                    mainPanel("Inventory");
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
                found = true;
                if (getLastProductDisplayed() - MAX_NUMB_PRODUCTS != 0 && getLastProductDisplayed() - MAX_NUMB_PRODUCTS > 0) {
                    //Update the first Product displayed
                    setFirstProductDisplayed(getFirstProductDisplayed() - MAX_NUMB_PRODUCTS);
                    //Update mainPanel
                    mainPanel("Inventory");
                    //mainPanel.repaint();
                    nextPagebtn.setEnabled(true);
                } else {
                    //Disable button if start of inventory
                    previousPagebtn.setEnabled(false);
                }
            }
        }
        //Checking checkout button
        if (!found) {
            if (e.getSource() == checkOutbtn) {
                //Update mainPanel
                mainPanel("Checkout");
                found = true;
            }
        }
        //Checking for new Qty
        if (!found) {
            for (int j = 0; j < myShoppingCart.getSize(); j++) {
                if (j > myShoppingCart.getSize() || newQtyBtn[j] == null) {
                    break;
                }
                if (e.getSource() == newQtyBtn[j]) {
                    found = true;
                    if (!"".equals(updateQuantity[j].getText())) {
                        //Getting Product
                        Product p = myShoppingCart.getProductAt(j);
                        //New Quantity entered
                        int newQTY = Integer.valueOf(updateQuantity[j].getText());
                        
                        //Update Total
                        double Total =  newQTY * p.getProductPrice();
                        double newTotal = Total + (myShoppingCart.getTotal() - (p.getProductPrice() * p.getQuantity()));
                        if (newQTY == 0) {
                            //Delete product from shoppingCart
                            myShoppingCart.removeProduct(j);
                            myShoppingCart.resetTotal();
                            myShoppingCart.setTotal(newTotal);
                            System.out.println("Shopping Cart Total" + newTotal);
                            //Update JLabel
                            getTotalLabel().setText("Total: $" + String.valueOf(myShoppingCart.getTotal()));
                            createPOPUP("Message", "Product Removed");
                            //Update mainPanel
                            mainPanel("Shopping Cart");
                        } else {
                            //Update new Quantity if possible
                            int result = inventory.findProductQTY(p.getSellerName(), String.valueOf(p.getId()));
                            System.out.println("Result in the inventory: " + result);
                            if (result != 0) {
                                if (newQTY > result) {
                                    createPOPUP("ERROR", "New quantity exceeds the number of product found in the inventory"
                                            + "\nNO MORE THAN: " + result);
                                    updateQuantity[j].setText("");
                                } else {
                                    myShoppingCart.updateProductQTY(new Product(p.getId(), p.getSellerName(), p.getProductName(), p.getInvoicePrice(), p.getProductPrice(), newQTY));
                                    myShoppingCart.resetTotal();
                                    myShoppingCart.setTotal(newTotal);
                                    getTotalLabel().setText("Total: $" + String.valueOf(myShoppingCart.getTotal()));
                                    //Update mainPanel
                                    mainPanel("Shopping Cart");
                                }
                            }
                        }
                    } else {
                        //Error message if textfield is empty
                        createPOPUP("Message", "Please enter a new Quantity");
                    }
                }
                //Stop loop if the button was found
                if (found) {
                    break;
                }
            }
        }

        //Checking submit order button
        if (!found) {
            if (e.getSource() == submitbtn) {
                if (!creditCardView.getFormCompleted()) {
                    createPOPUP("Message", "Please Complete Credit Card Form");
                } else {
                    //Complete Order
                    myShoppingCart.completeOrder(creditCardView.getName(), creditCardView.getCreditCard(), creditCardView.getExpirationDate(), creditCardView.getZIPCode());
                    createPOPUP("Order Completed", myShoppingCart.printReceipt());
                    //Update Inventory
                    myShoppingCart.getProductsBought().forEach((product) -> {
                        inventory.updateInventory("Customer", product);
                    });
                    //Clear myShoppingCart
                    myShoppingCart.clearCart();
                    myShoppingCart.resetTotal();
                    getTotalLabel().setText("Total: $" + String.valueOf(myShoppingCart.getTotal()));
                    inventory = new ProductInventory();
                    topButton.setText("Shopping Cart");
                    //Update mainPanel
                    mainPanel("Inventory");
                }
            }
        }
    }
}
