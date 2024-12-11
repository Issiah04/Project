package com.example.project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GroceryStoreApp extends Application {

    private GroceryStore store = new GroceryStore();

    @Override
    public void start(Stage primaryStage) {
        // where we put the login screen
        VBox loginBox = createLoginScreen(primaryStage);
        Scene loginScene = new Scene(loginBox, 300, 200);

        //  where the stage is Setup
        primaryStage.setTitle("Grocery Store App");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    //  Creating the login screen and stage
    private VBox createLoginScreen(Stage primaryStage) {
        VBox loginBox = new VBox(10);
        loginBox.setPadding(new Insets(20));
        loginBox.setAlignment(Pos.CENTER);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");
        Button loginButton = new Button("Login");
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (store.getUsername().equals(username) && store.getPassword().equals(password)) {
                // Proceed to the store scene if login is successful
                primaryStage.setScene(createStoreScene(primaryStage));
            } else {
                errorLabel.setText("Invalid username or password. Try again.");
            }
        });

        loginBox.getChildren().addAll(
                new Label("Grocery Store Login"),
                usernameField, passwordField, loginButton, errorLabel
        );

        return loginBox;
    }

    // Create the store page with items listed
    private Scene createStoreScene(Stage primaryStage) {
        VBox storeBox = new VBox(10);
        storeBox.setPadding(new Insets(20));
        storeBox.setAlignment(Pos.TOP_CENTER);

        // Display available  where items  are in a grid
        GridPane itemGrid = new GridPane();
        itemGrid.setHgap(10);
        itemGrid.setVgap(5);

        String[] itemNames = store.getItemNames();
        double[] itemPrices = store.getItemPrices();

        for (int i = 0; i < itemNames.length; i++) {
            itemGrid.add(new Label(itemNames[i] + ": $" + itemPrices[i]), 0, i);
        }

        // Input fields for the user to enter item and quantity
        TextField itemField = new TextField();
        itemField.setPromptText("Enter item name");
        TextField quantityField = new TextField();
        quantityField.setPromptText("Enter quantity");

        Button calculateButton = new Button("Calculate Total");
        Label resultLabel = new Label();

        calculateButton.setOnAction(e -> {
            String itemName = itemField.getText();
            String quantityText = quantityField.getText();

            // checking user input
            if (itemName.isEmpty() || quantityText.isEmpty()) {
                resultLabel.setText("Please fill in all fields.");
                return;
            }

            try {
                int quantity = Integer.parseInt(quantityText);
                if (quantity <= 0) {
                    resultLabel.setText("Quantity must be a positive number.");
                    return;
                }
                   // tax calculation
                double cost = store.calculateCost(itemName, quantity);
                if (cost < 0) {
                    resultLabel.setText("Item not found.");
                    return;
                }

                double tax = store.calculateTax(cost);
                double total = cost + tax;

                resultLabel.setText(String.format(
                        "Pre-tax Cost: $%.2f\nTax: $%.2f\nTotal Cost: $%.2f",
                        cost, tax, total
                ));

            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid quantity.");
            }
        });

        storeBox.getChildren().addAll(
                new Label("Available Items:"),
                itemGrid,
                new Label("Enter item name:"),
                itemField,
                new Label("Enter quantity:"),
                quantityField,
                calculateButton,
                resultLabel
        );

        return new Scene(storeBox, 400, 400);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
