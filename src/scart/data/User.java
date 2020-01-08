/*
Project Topic: Shopping Cart
Project Title: Speedy Shopping and Selling
File Name: User.java
 */
package scart.data;

/**
 * Abstract class to represent a user
 *
 */
public abstract class User {

    /**
     * String representing userName
     */
    private final String userName;
    /**
     * String representing password
     */
    private final String password;

    /**
     * Constructor
     *
     * precondition none
     * postcondition User object created
     * @param userName a string
     * @param password a string
     */
    User(String userName, String password) {

        this.userName = userName;
        this.password = password;
    }

    /**
     * Get UserName
     *
     * @return a string
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Get Password
     *
     * @return a string
     */
    public String getPassword() {
        return this.password;
    }
}
