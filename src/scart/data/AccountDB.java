/*
Project Topic: Shopping Cart
Project Title: Speedy Shopping and Selling
File Name: AccountDB.java
 */
package scart.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class to save new accounts and check login credentials
 *
 */
public class AccountDB {

    /**
     * Constructor
     */
    public AccountDB() {
    }

    /**
     * Create and saved a new account
     *
     * precondition none
     * postcondition return true if account was created.Otherwise, returns
     * false
     * @param accType a string
     * @param userName a string
     * @param Password a string
     * @return Boolean true if account was created
     */
    public boolean createAccount(String accType, String userName, String Password) {
        //Check if username was already used
        if (!findUser(userName)) {
            String Path = "./AccountDB/Users/" + accType + "/";
            String dirName = Path.concat(userName);
            File directory = new File(dirName);
            if (!directory.exists()) {
                directory.mkdir();
            }
            String fileName = userName + ".txt";
            File file = new File(directory + "/" + fileName);
            try {
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(Password);
                bw.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return true;
        }
        return false;
    }

    /**
     * Find user using the userName
     *
     * precondition none
     * postcondition return true if userName is already used
     * @param userName a string
     * @return Boolean true if userName found, False otherwise
     */
    public boolean findUser(String userName) {
        String Path = "./AccountDB/Users/";
        String[] dirName = new String[]{Path.concat("Customer"), Path.concat("Seller")};
        for (int i = 0; i < 2; i++) {
            File directory = new File(dirName[i]);
            for (File file : directory.listFiles()) {
                if (userName.equalsIgnoreCase(file.getName())) {
                    System.out.println("UserName Found");
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check login credentials
     *
     * precondition none
     * postcondition return true if user is found and password is correct
     * @param accType a string
     * @param userName a string
     * @param Password a string
     * @return Boolean true if login was successful
     */
    public boolean checkPassword(String accType, String userName, String Password) {
        String fileName = userName + ".txt";
        boolean passVerified = false;
        try {
            File file = new File("./AccountDB/Users/" + accType + "/" + userName + "/" + fileName);
            if (!file.exists()) {
                //File does not exsits. Fail Login
                return false;
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String savedPass;
            savedPass = br.readLine();
            //Check if password is correct
            if (savedPass.equals(Password)) {
                passVerified = true;
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return passVerified;
    }
}
