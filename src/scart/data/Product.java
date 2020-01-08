/*
Project Topic: Shopping Cart
Project Title: Speedy Shopping and Selling
File Name: Product.java
 */
package scart.data;

/**
 * A product in the inventory.
 *
 */
public class Product implements Comparable<Product> {

    /**
     * Constructor.
     *
     * precondition none
     * postcondition Product object created
     * @param id an integer
     * @param sellerName a string
     * @param productName is a string.
     * @param invoicePrice is a double
     * @param productPrice is a double.
     * @param quantity is an integer
     */
    public Product(int id, String sellerName, String productName, double invoicePrice, double productPrice, int quantity) {
        this.sellerName = sellerName;
        this.productName = productName;
        this.invoicePrice = invoicePrice;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.id = id;
    }

    /**
     * Checking if two products are equal Two products are equal if the id and
     * sellerName are equal
     *
     * precondition obj != null
     * postcondition returns true if equal
     * @param obj a Product Object
     * @return Boolean if id and sellerName are equal.False, otherwise
     */
    public boolean equals(Product obj) {
        return (this.id == obj.id && this.sellerName.equals(obj.sellerName));
    }

    /**
     * Get product's name.
     *
     * @return a string.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Get product's price
     *
     * @return a double
     */
    public double getProductPrice() {
        return productPrice;
    }

    /**
     * Get product's price invoice price
     *
     * @return a double
     */
    public double getInvoicePrice() {
        return invoicePrice;
    }

    /**
     * Get product's id
     *
     * @return an integer
     */
    public int getId() {
        return id;
    }

    /**
     * Get product's sellerName
     *
     * @return a string.
     */
    public String getSellerName() {
        return this.sellerName;
    }

    /**
     * Get product's quantity
     *
     * @return an integer
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * String representation of the object.
     *
     * @return a string.
     */
    @Override
    public String toString() {
        String product = "";
        product += "Seller: " + this.sellerName;
        product += "\nProduct Name: " + this.productName;
        product += "\nPrice: $" + this.productPrice;
        product += "\nQuantity: " + this.quantity;
        return product;
    }

    /**
     * Compare based on ID
     * Ascending order
     * precondition other != null
     * @param other a Product object
     */
    @Override
    public int compareTo(Product other) {
        int compareID = other.getId();
        //Ascending
        return this.id - compareID;
    }

    /**
     * A string representing the product's name.
     */
    private final String productName;
    /**
     * A double representing the product's price.
     */
    private final double productPrice;
    /**
     * An integer representing the product's quantity.
     */
    private final int quantity;
    /**
     * A string representing the product's sellerName
     */
    private final String sellerName;
    /**
     * A double representing the product's invoicePrice.
     */
    private final double invoicePrice;
    /**
     * An integer representing the product's id.
     */
    private final int id;

}
