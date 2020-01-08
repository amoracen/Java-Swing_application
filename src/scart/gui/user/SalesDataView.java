/*
Project Topic: Shopping Cart
Project Title: Speedy Shopping and Selling
File Name: SalesDataView.java
 */
package scart.gui.user;


import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Sales Data JPanel
 */
public class SalesDataView {

  
    private final JPanel mainPanel;
    private final JPanel formPanel;
    private JLabel titleLabel,dateLabel, productsSoldLabel, profitLabel, costLabel, revenueLabel;
    private JLabel ProductsSoldValue, ProfitValue, CostValue, RevenueValue;

    /**
     * Constructor 
     * precondition mainPanel != null
     * postcondition SalesData Panel created
     * @param mainPanel a JPanel
     * @param productSold an int
     * @param Cost a double
     * @param revenue a double
     * @param profit a double
     */
    public SalesDataView(JPanel mainPanel,int productSold,double Cost,double revenue,double profit) {
        this.mainPanel = mainPanel;
        this.formPanel = new JPanel(new GridLayout(5, 2, 50, 35));
        formPanel.setBorder(new EmptyBorder(125, 25, 25, 25));

        //Section for Sales Data
        titleLabel = new JLabel("Sales Data");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        this.formPanel.add(titleLabel);

        //Create Date Label    
        dateLabel = new JLabel("Date: " + LocalDate.now().toString());
        dateLabel.setFont(new Font("Ariel", Font.PLAIN, 24));
        this.formPanel.add(dateLabel);
        
        //Create ProductsSold Label
        productsSoldLabel = new JLabel("Products Sold: ");
        productsSoldLabel.setFont(new Font("Ariel", Font.PLAIN, 22));
        this.formPanel.add(productsSoldLabel);

        //Create ProductsSold Value
        ProductsSoldValue = new JLabel(String.valueOf(productSold));
        ProductsSoldValue.setFont(new Font("Ariel", Font.PLAIN, 22));
        this.formPanel.add(ProductsSoldValue);

        //Create Cost Label
        costLabel = new JLabel("Cost: ");
        costLabel.setFont(new Font("Ariel", Font.PLAIN, 22));
        this.formPanel.add(costLabel);

       //Create Cost Value
        CostValue = new JLabel("$" + String.valueOf(Cost));
        CostValue.setFont(new Font("Ariel", Font.PLAIN, 22));
        this.formPanel.add(CostValue);

        //Create Revenue Label
        revenueLabel = new JLabel("Revenue: ");
        revenueLabel.setFont(new Font("Ariel", Font.PLAIN, 22));
        this.formPanel.add(revenueLabel);

        //Create Revenue Value
        RevenueValue = new JLabel("$" + String.valueOf(revenue));
        RevenueValue.setFont(new Font("Ariel", Font.PLAIN, 22));
        this.formPanel.add(RevenueValue);

        //Create Profit Label
        profitLabel = new JLabel("Profit: ");
        profitLabel.setFont(new Font("Ariel", Font.PLAIN, 22));
        this.formPanel.add(profitLabel);

        //Create Profit Value
        ProfitValue = new JLabel("$" + String.valueOf(profit));
        ProfitValue.setFont(new Font("Ariel", Font.PLAIN, 22));
        this.formPanel.add(ProfitValue);
        
        //Add to mainPanel
        this.mainPanel.add(formPanel);
    }  
}
