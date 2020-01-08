/*
Project Topic: Shopping Cart
Project Title: Speedy Shopping and Selling
File Name: UserView.java
 */
package scart.gui.user;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * Template method to create a userView
 *
 */
public abstract class UserView {

    /**
     * JPanel for topPanel
     */
    private final JPanel topPanel;

    /**
     * JPanel for mainPanel
     */
    private final JPanel mainPanel;
    /**
     * JPanel for bottomPanel
     */
    private final JPanel bottomPanel;
    /**
     * JLabel for totalLabel
     */
    private final JLabel totalLabel;
    /**
     * JLabel for welcome message
     */
    private final JLabel welcomeLabel;
    /**
     * Number of product to display per window
     */
    private final int MAX_NUMB_PRODUCTS = 10;
    /**
     * Index of first and last product displayed
     */
    private int firstProduct, lastProduct;
    //Components for inventory Panel
    /**
     * JPanel for product's information
     */
    private final JPanel[] productPanel;
    /**
     * JPanel for product's information
     */
    private final JPanel[] prodPanelBottom;
    /**
     * JTextArea for product's information
     */
    private final JTextArea[] pInfo;
    /**
     * JButton for product's panel
     */
    private final JButton[] prodBTN1;

    /**
     * Constructor
     * precondition JPanels != null
     * postcondition UserView created
     * @param topPanel a JPanel
     * @param mainPanel a JPanel
     * @param bottomPanel a JPanel
     */
    public UserView(JPanel topPanel, JPanel mainPanel, JPanel bottomPanel) {
        this.topPanel = topPanel;
        this.mainPanel = mainPanel;
        this.bottomPanel = bottomPanel;
        totalLabel = new JLabel();
        welcomeLabel = new JLabel();
        firstProduct = 0;
        lastProduct = 0;
        productPanel = new JPanel[MAX_NUMB_PRODUCTS];
        prodPanelBottom = new JPanel[MAX_NUMB_PRODUCTS];
        pInfo = new JTextArea[MAX_NUMB_PRODUCTS];
        prodBTN1 = new JButton[MAX_NUMB_PRODUCTS];
    }

    /**
     * Template Method to create a UserView
     * @param userName a string
     * @param panelType a string
     * @param bottomType a string
     */
    public final void createHomePage(String userName, String panelType, String bottomType) {
        topPanel(userName);
        mainPanel(panelType);
        bottomPanel(bottomType);
    }

    /**
     * Abstract Method
     * @param userName a string
     */
    public abstract void topPanel(String userName);

    /**
     * Abstract Method
     * @param panel a string
     */
    public abstract void mainPanel(String panel);

    /**
     * Abstract Method
     * @param bottomType s string
     */
    public abstract void bottomPanel(String bottomType);

    /**
     * Get TopPanel
     * @return a JPanel
     */
    public JPanel getTopJPanel() {
        return topPanel;
    }

    /**
     * Get TopLabel
     * @return a JLabel
     */
    public JLabel getTotalLabel() {
        return totalLabel;
    }

    /**
     * Get WelcomLabel
     * @return a JLabel
     */
    public JLabel getWelcomeLabel() {
        return welcomeLabel;
    }

    /**
     * Get MainPani
     * @return a JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Get bottomPanel
     * @return a JPanel
     */
    public JPanel getBottomPanel() {
        return bottomPanel;
    }

    /**
     * Set Last Product displayed
     * @param index an int
     */
    public void setLastProductDisplayed(int index) {
        this.lastProduct = index;
    }

    /**
     * Get Last Product displayed
     * @return an int
     */
    public int getLastProductDisplayed() {
        return lastProduct;
    }

    /**
     * Get First Product displayed
     * @return an int
     */
    public int getFirstProductDisplayed() {
        return firstProduct;
    }

    /**
     * Set First Product displayed
     * @param index an int
     */
    public void setFirstProductDisplayed(int index) {
        this.firstProduct = index;
    }

    /**
     * Get JPanel using an index
     * @param index an int
     * @return a JPanel
     */
    public JPanel getProductPanel(int index) {
        return productPanel[index];
    }

    /**
     * Set JPanel using an index
     * @param index an int
     * @param productPanel is a JPanel
     */
    public void setProductPanel(int index, JPanel productPanel) {
        this.productPanel[index] = productPanel;
    }

    /**
     * Get JPanel using an index
     * @param index an int
     * @return a JPanel
     */
    public JPanel getProdPanelBottom(int index) {
        return prodPanelBottom[index];
    }

    /**
     * Set JPanel using an index
     * @param index an int
     * @param bottomPanel is a JPanel
     */
    public void setProdPanelBottom(int index, JPanel bottomPanel) {
        this.prodPanelBottom[index] = bottomPanel;
    }

    /**
     * Get JTextArea using an index
     * @param index an int
     * @return a JTextArea
     */
    public JTextArea getProdInfoPanel(int index) {
        return pInfo[index];
    }

    /**
     * Set JTextArea using an index
     * @param index an int
     * @param textArea a JTexArea
     */
    public void setProdInfoPanel(int index, JTextArea textArea) {
        this.pInfo[index] = textArea;
        this.pInfo[index].setEditable(false);
        this.pInfo[index].setOpaque(false);
        this.pInfo[index].setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    /**
     * Get JButton using index
     * @param index an int
     * @return a JButton
     */
    public JButton getProdBTN1(int index){
        return this.prodBTN1[index];
    }

    /**
     * Set JButton using index
     * @param index an int
     * @param button a JButton
     */
    public void setProdBTN1(int index,JButton button){
        this.prodBTN1[index] = button;
    }

    /**
     * Create a POPUP window
     *
     * @param name title
     * @param description a string
     */
    public void createPOPUP(String name, String description) {
        JOptionPane pane = new JOptionPane();
        pane.setMessage(description);
        JDialog dTitle;
        dTitle = pane.createDialog(name);
        dTitle.setVisible(true);
    }
}
