/*
Project Topic: Shopping Cart
Project Title: Speedy Shopping and Selling
File Name: Order.java
 */
package scart.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to save and read orders created by users.
 *
 */
public class Order {

    /**
     * String for sellerName
     */
    private final String sellerName;

    /**
     * Constructor
     *
     * precondition none
     * postcondition sellerName != null
     * @param sellerName a string
     */
    public Order(String sellerName) {
        this.sellerName = sellerName;
    }

    /**
     * Creates a file on the user directory and saves the order
     *
     * precondition product != null
     * postcondition Order saved to directory
     * @param product a Product Object
     * @param CreditCard a string
     */
    public void saveOrder(Product product, String CreditCard) {
        try {
            String Path = "./AccountDB/Users/Seller/";
            Path += product.getSellerName();
            File myOrder = new File(Path);
            if (!myOrder.exists()) {
                //Create new inventory
                myOrder.mkdirs();
            }
            String fileName = "Orders.csv";
            File file = new File(Path + "/" + fileName);
            FileWriter fw;
            BufferedWriter bw;
            boolean exists = file.exists();
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            if (!exists) {
                //If file does not exists 
                String[] columnName = new String[]{"ID", "PRODUCT NAME", "INVOICE PRICE", "SELLING PRICE", "QUANTITY", "Credit Card"};
                for (String columnName1 : columnName) {
                    bw.append(columnName1);
                    bw.append(",");
                }
                bw.append("\n");
            }
            //Append information to file
            bw.append(String.valueOf(product.getId()));
            bw.append(",");
            bw.append(product.getProductName());
            bw.append(",");
            bw.append(String.valueOf(product.getInvoicePrice()));
            bw.append(",");
            bw.append(String.valueOf(product.getProductPrice()));
            bw.append(",");
            bw.append(String.valueOf(product.getQuantity()));
            bw.append(",");
            bw.append(CreditCard);
            bw.append("\n");
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Return an ArrayList with the fileContent
     *
     * precondition none
     * postcondition fileContent != null
     * @return an ArrayList String[] with the orders
     */
    public ArrayList<String[]> readOrders() {
        //Path to saved orders
        String PathOrder = "./AccountDB/Users/Seller/" + sellerName + "/Orders.csv";
        File sellerDirectory = new File(PathOrder);
        ArrayList<String[]> fileContent = new ArrayList<>();
        if (!sellerDirectory.exists()) {
            System.out.println("Not Found");
            return fileContent;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(PathOrder));
            String row;
            row = br.readLine();
            while ((row = br.readLine()) != null) {
                String[] info = row.split(",");
                fileContent.add(info);
            }
            br.close();
            return fileContent;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return fileContent;
    }
}
