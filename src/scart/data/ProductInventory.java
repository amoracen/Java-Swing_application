/*
Project Topic: Shopping Cart
Project Title: Speedy Shopping and Selling
File Name: ProductInventory.java

 */
package scart.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to create,save, and read an inventory of products.
 *
 */
public final class ProductInventory {

    /**
     * A string representing the sellerName
     */
    private String sellerName;

    /**
     * Constructor for Customer to read All Inventory products.
     *
     * precondition none
     * postcondition returns an ArrayList of Product if file is found. 
     * Else,ArrayList is empty
     */
    public ProductInventory() {
        inventory = new ArrayList<>();
        readInventory("Customer");
    }

    /**
     * Constructor for Seller inventory.
     * Reads only the seller's inventory
     *
     * precondition none
     * postcondition returns an ArrayList of Product if file is found. 
     * Else,ArrayList is empty
     * @param sellerName a string
     */
    public ProductInventory(String sellerName) {
        inventory = new ArrayList<>();
        this.sellerName = sellerName;
        readInventory("Seller");
    }

    /**
     * Read Inventory files
     * preconditio sellerName != null
     * postcondition ArrayList updated if found
     * @param invType a string representing which inventory to read
     */
    public void readInventory(String invType) {
        try {
            switch (invType) {
                case "Customer":
                    String PathAll = "./Inventory/";
                    //Read all files
                    File directory = new File(PathAll);
                    for (File file : directory.listFiles()) {
                        String sellerNameFile = file.getName().replaceAll(".csv", "");
                        System.out.println("Seller Found: " + sellerNameFile);
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String row;
                        row = br.readLine();
                        while ((row = br.readLine()) != null) {
                            String[] info = row.split(",");
                            //Add new Product to inventory
                            this.inventory.add(new Product(Integer.parseInt(info[0]), sellerNameFile,
                                    info[1], Double.parseDouble(info[2]), Double.parseDouble(info[3]), Integer.parseInt(info[4])));
                        }
                        br.close();
                    }
                    break;
                case "Seller":
                    String PathSeller = "./Inventory/" + sellerName + ".csv";
                    //Read only the seller file
                    File sellerDirectory = new File(PathSeller);
                    if (!sellerDirectory.exists()) {
                        System.out.println("Not Found");
                        return;
                    }

                    BufferedReader br = new BufferedReader(new FileReader(PathSeller));
                    String row;
                    row = br.readLine();
                    while ((row = br.readLine()) != null) {
                        String[] info = row.split(",");
                        //Add new Product to inventory
                        this.inventory.add(new Product(Integer.parseInt(info[0]), sellerName,
                                info[1], Double.parseDouble(info[2]), Double.parseDouble(info[3]), Integer.parseInt(info[4])));
                    }
                    br.close();
                    break;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Add product to inventory and save it to file
     * precondition none
     * postcondition product saved
     *
     * @param sellerName a string
     * @param productName a string
     * @param invoicePrice a double
     * @param productPrice a double
     * @param quantity an integer
     */
    public void addProductToInventory(String sellerName, String productName, double invoicePrice,
            double productPrice, int quantity) {
        try {
            int id = 1;
            //Inventory path
            String Path = "./Inventory/" + sellerName + ".csv";
            File file = new File(Path);
            FileWriter fw;
            BufferedWriter bw;

            if (!file.exists()) {
                //Create new file
                fw = new FileWriter(file.getAbsoluteFile(), true);
                bw = new BufferedWriter(fw);
                String[] columnName = new String[]{"ID", "PRODUCT NAME", "INVOICE PRICE", "SELLING PRICE", "QUANTITY"};
                for (String columnName1 : columnName) {
                    bw.append(columnName1);
                    bw.append(",");
                }
                bw.append("\n");
            } else {
                //Reading file to update ID
                fw = new FileWriter(file.getAbsoluteFile(), true);
                bw = new BufferedWriter(fw);
                Path path = Paths.get(Path);
                List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
                id = fileContent.size();
                System.out.println(id);
            }
            bw.append(String.valueOf(id));
            bw.append(",");
            bw.append(productName);
            bw.append(",");
            bw.append(String.valueOf(invoicePrice));
            bw.append(",");
            bw.append(String.valueOf(productPrice));
            bw.append(",");
            bw.append(String.valueOf(quantity));
            bw.append("\n");
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update Inventory and save file
     * precondition product != null
     * postcondition if product file, new quantity is updated
     * @param accType a string representing the account type
     * @param product a Product object
     */
    public void updateInventory(String accType, Product product) {
        //Path to seller inventory
        String Path = "./Inventory/" + product.getSellerName() + ".csv";
        Path path = Paths.get(Path);
        try {
            //Read file Content
            List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
            boolean replaced = false;
            for (int i = 1; i < fileContent.size(); i++) {
                String row = fileContent.get(i);
                String[] info = row.split(",");
                //Checking if id is equal
                if (product.getId() == Integer.valueOf(info[0])) {
                    int newQTY;
                    //Select new Qty
                    if ("Customer".equals(accType)) {
                        //Customer updates the quantity after submiting the order
                        newQTY = Integer.valueOf(info[4]) - product.getQuantity();
                    } else {
                        //Seller updates the quantity at any time
                        newQTY = product.getQuantity();
                    }
                    String newLine = info[0] + "," + info[1] + "," + info[2] + "," + info[3] + "," + newQTY;
                    fileContent.set(i, newLine);
                    replaced = true;
                    break;
                }
            }//EOF for loop
            if (replaced) {
                //Save to file if the quantity was updated
                Files.write(path, fileContent, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //Update ArrayList
        inventory.clear();
        readInventory(accType);
    }

    /**
     * Find a Product in the inventory
     * precondition inventory.size greater than 0
     * postcondition return Product
     * @param i index of Product
     * @return a Product Object
     */
    public Product getProductAt(int i) {
        Product p = inventory.get(i);
        return p;
    }

    /**
     * Find Product QTY
     * precondition inventory != null
     * postcndition return the quantity
     * @param sellerName a string
     * @param id an integer
     * @return the product quantity if found on the ArrayList
     */
    public int findProductQTY(String sellerName, String id) {
        for (Product product : inventory) {
            if (product.getId() == Integer.valueOf(id) && product.getSellerName().equals(sellerName)) {
                return product.getQuantity();
            }
        }
        return 0;
    }

    /**
     * String representation of the object.
     *
     * @return a string.
     */
    @Override
    public String toString() {
        String inv = "";
        inv += "Product Inventory :";
        for (Product product : this.inventory) {
            inv += "\n" + product.getId() + " " + product.toString();
        }
        return inv;
    }

    /**
     * Inventory Size
     * precondition inventory != null
     * postcondition returns inventory's size
     * @return an integer
     */
    public int getSize() {
        return inventory.size();
    }

    /**
     * Prints the string representation of the object.
     */
    public void printProductInventory() {
        System.out.println(this.toString());
    }

    /**
     * An arrayList of products.
     */
    private final ArrayList<Product> inventory;
}
