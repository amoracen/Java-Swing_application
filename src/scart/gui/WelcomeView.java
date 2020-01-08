/*
Project Topic: Shopping Cart
Project Title: Speedy Shopping and Selling
File Name: WelcomeView.java
 */
package scart.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates the welcome JPanel
 *
 */
public class WelcomeView {

    /**
     * JPanel for mainPanel
     */
    private final JPanel mainPanel;
    /**
     * JPanel for topPanel
     */
    private final JPanel topPanel;
    /**
     * JLabel for topLabel
     */
    private final JLabel topLabel;

    /**
     * Constructor
     * precondition JPanels != null
     * @param topLabel a JPanel
     * @param topJPanel a JPanel
     * @param mainPanel a JPanel
     */
    public WelcomeView(JLabel topLabel, JPanel topJPanel, JPanel mainPanel) {
        this.topLabel = topLabel;
        this.topPanel = topJPanel;
        this.mainPanel = mainPanel;
    }

    /**
     * Adds topLabel to TopPanel
     * precondition JPanels != null
     * postcondition topLabel added to topPanel
     */
    public void topPanel() {
        this.topLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        this.topLabel.setText("Welcome to the Speedy Shopping and Selling Application");
        topPanel.setLayout(new FlowLayout());
        topPanel.add(topLabel);
    }

    /**
     * Updates topLabel
     * precondition JLabel != null
     * postcondition sets new Text
     * @param newLabel a string
     */
    public void updateTopLabel(String newLabel) {
        this.topLabel.setText(newLabel);
    }

    /**
     * Add Components to the main Panel
     * precondition JButtons != null
     * postcondition adds JButton to mainPanel
     * @param registerButton a JButton
     * @param loginButton a JButton
     */
    public void mainPanel(JButton registerButton, JButton loginButton) {
        this.mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JButton registerBtn  = registerButton;
        JButton loginBtn = loginButton;

        registerBtn.setText("<html><center>" + "Do you need an Account?" + "<br>" + "<h3>Register</h3>" + "</center></html>");
        registerBtn.setPreferredSize(new Dimension(200, 60));
        registerBtn.setFocusable(false);

        loginBtn.setText("<html><center>" + "Already have an Account?" + "<br>" + "<h3>Login</h3>" + "</center></html>");
        loginBtn.setPreferredSize(new Dimension(200, 60));
        loginBtn.setFocusable(false);

        //Add buttons to btnPanel
        mainPanel.add(registerBtn);
        mainPanel.add(loginBtn);
    }
}
