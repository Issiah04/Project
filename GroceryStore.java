//Issiah Roman CSC 210
// Sam Lima

package com.example.project;

public class GroceryStore {
    private final String username = "issiah1"; // my username
    private final String password = "abc4"; // my password
    private final String[] itemNames = {"Yogurt", "oreos", "Cookies", "Milk", "juice"}; // Listed Items
    private final double[] itemPrices = {2.00, 3.50, 1.70, 3.90, 2.80}; // Prices for items

    // Returns the username
    public String getUsername() {
        return username;
    }

    // Returns the password
    public String getPassword() {
        return password;
    }

    // Returns the list of item names
    public String[] getItemNames() {
        return itemNames;
    }

    // Returns the list of item prices
    public double[] getItemPrices() {
        return itemPrices;
    }

    // Calculation for the total cost of an item based on its quantity
    public double calculateCost(String itemName, int quantity) {
        for (int i = 0; i < itemNames.length; i++) {
            if (itemNames[i].equalsIgnoreCase(itemName)) {
                return itemPrices[i] * quantity;
            }
        }
        return -1; // Item not found
    }

    // Calculation for the tax on a given cost
    public double calculateTax(double cost) {
        final double TAX_RATE = 0.07; // 7% tax
        return cost * TAX_RATE;
    }
}

