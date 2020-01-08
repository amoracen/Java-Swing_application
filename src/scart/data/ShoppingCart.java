/*
Project Topic: Shopping Cart
Project Title: Speedy Shopping and Selling
File Name: ShoppingCart.java
 */
package scart.data;


import java.util.ArrayList;
import java.util.Collections;

/**
 * Class for Shopping Cart
 *
 */
public class ShoppingCart {

    /**
     * Constructor
     * precondition none
     * postcondition cartProducts != null
     */
    public ShoppingCart() {
        this.total = 0.0;
        cartProducts = new ArrayList<>();
    }

    /**
     * Add product to Cart and sorts the ArrayList in ascending order 
     * precondition cartProducts != null
     * postcondition add new Product to cart or updates the quantity of existing product
     * @param newProduct a Product object
     */
    public void addProduct(Product newProduct) {
        Product productExists = findProduct(newProduct);
        if (productExists == null) {
            //Add new Product
            this.cartProducts.add(newProduct);
        } else {
            //Update quantity of existing product
            this.cartProducts.remove(productExists);
            cartProducts.add(new Product(newProduct.getId(), newProduct.getSellerName(), newProduct.getProductName(),
                    newProduct.getInvoicePrice(), newProduct.getProductPrice(), newProduct.getQuantity() + productExists.getQuantity()));

        }
        //System.out.println("Before Sorting");
        //System.out.println(cartProducts.toString());
        //Sort ArrayList
        Collections.sort(cartProducts);
    }

    /**
     * Update product QTY and Sort ArrayList
     * precondition cartProducts != null
     * postcondition quantity update if product was found
     * @param newProduct a Product object
     */
    public void updateProductQTY(Product newProduct) {
        Product productExists = findProduct(newProduct);
        if (productExists != null) {
            this.cartProducts.remove(productExists);
            cartProducts.add(new Product(newProduct.getId(), newProduct.getSellerName(), newProduct.getProductName(),
                    newProduct.getInvoicePrice(), newProduct.getProductPrice(), newProduct.getQuantity()));
            //System.out.println(cartProducts.toString());
            Collections.sort(cartProducts);
        }
    }

    /**
     * Find a product on the Shopping Cart
     * precondition cartProducts != null
     * postcondition return product if found. Else, return null
     * @param newProduct a Product object
     * @return Product object if found in the ArrayList.Else, return null
     */
    public Product findProduct(Product newProduct) {
        for (Product product : cartProducts) {
            if (product.equals(newProduct)) {
                return product;
            }
        }
        return null;
    }

    /**
     * Set Total
     * precondition total != null
     * postcondition total was update
     * @param total a double
     */
    public void setTotal(double total) {
        this.total += total;
    }
    /**
     * Set Total to 0
     */
    public void resetTotal(){
        this.total = 0.0;
    }

    /**
     * Get Total
     *
     * @return a double
     */
    public double getTotal() {
        return this.total;
        
    }

    /**
     * Get Size
     *
     * @return an integer
     */
    public int getSize() {
        return this.cartProducts.size();
    }

    /**
     * Get a Product using an index
     * precondition cartProducts != null
     * postcondition return Product at location i
     * @param i an integer
     * @return a Product Object
     */
    public Product getProductAt(int i) {
        return cartProducts.get(i);
    }

    /**
     * Get the list of products on the cart
     * precondition cartProducts != null
     * postcondition returns the ArraLisy of products
     * @return an ArrayList
     */
    public ArrayList<Product> getProductsBought() {
        return this.cartProducts;
    }

    /**
     * String representation of the object
     *
     * @return a string
     */
    @Override
    public String toString() {
        String inv = "";
        inv += "Shopping Cart :";
        for (Product product : this.cartProducts) {
            inv += "\n" + product.toString();
        }
        return inv;
    }

    /**
     * Create an Order File
     * precondition  cartProduct != null
     * postcondition order created
     * @param cardName a string
     * @param cardNumber a string
     * @param cardExpirationDate a string
     * @param cardZipCode a string
     */
    public void completeOrder(String cardName, String cardNumber, String cardExpirationDate, String cardZipCode) {
        String creditCard = cardName+" "+cardNumber +" "+cardExpirationDate+" "+cardZipCode;
        //Save Order
        for (Product product : this.cartProducts) {
            Order saveOrder = new Order(product.getSellerName());
            saveOrder.saveOrder(product, creditCard);
        }
    }

    /**
     * Print Receipt
     *
     * @return a string
     */
    public String printReceipt() {
        String recp = cartProducts.toString();
        return recp;
    }

    /**
     * Clear Cart.
     * precondition cartProducts != null
     * postconditon cartProduct.size == 0
     */
    public void clearCart() {
        cartProducts.clear();
    }

    /**
     * Remove a product from the Shopping Cart
     * precondition cartProducts != null
     * postconditon cartProduct.size -1 
     * @param i an integer
     */
    public void removeProduct(int i) {
        this.cartProducts.remove(i);
    }

    /**
     * List of products added to the cart
     */
    private final ArrayList<Product> cartProducts;
    /**
     * Total
     */
    private Double total;
}
