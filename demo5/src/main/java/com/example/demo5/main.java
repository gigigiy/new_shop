package com.example.demo5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class main extends Application {

    private Map<Integer, Product> cart = new HashMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Простой магазин");

        VBox productBox = new VBox(10);
        productBox.setPadding(new Insets(10));
        productBox.getChildren().addAll(
                createProduct(1, "Товар 1", 10.00),
                createProduct(2, "Товар 2", 20.00)
        );

        VBox cartBox = new VBox(10);
        cartBox.setPadding(new Insets(10));
        Label totalLabel = new Label("Общая сумма: $0.00");
        cartBox.getChildren().addAll(new Label("Корзина"), totalLabel);

        VBox root = new VBox(20);
        root.getChildren().addAll(productBox, cartBox);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createProduct(int productId, String productName, double productPrice) {
        HBox productHBox = new HBox(10);
        productHBox.setPadding(new Insets(5));

        Label nameLabel = new Label(productName);
        Label priceLabel = new Label("Цена: $" + productPrice);

        TextField quantityField = new TextField("1");
        quantityField.setPrefWidth(50);

        Button addButton = new Button("Добавить");
        addButton.setOnAction(e -> addToCart(productId, productPrice, Integer.parseInt(quantityField.getText())));

        Button removeButton = new Button("Убрать");
        removeButton.setOnAction(e -> removeFromCart(productId, productPrice));

        productHBox.getChildren().addAll(nameLabel, priceLabel, quantityField, addButton, removeButton);
        return productHBox;
    }

    private void addToCart(int productId, double productPrice, int quantity) {
        if (cart.containsKey(productId)) {
            cart.get(productId).setQuantity(cart.get(productId).getQuantity() + quantity);
        } else {
            Product product = new Product(productId, productPrice, quantity);
            cart.put(productId, product);
        }
        updateCart();
    }

    private void removeFromCart(int productId, double productPrice) {
        if (cart.containsKey(productId)) {
            cart.get(productId).setQuantity(cart.get(productId).getQuantity() - 1);
            if (cart.get(productId).getQuantity() <= 0) {
                cart.remove(productId);
            }
            updateCart();
        }
    }

    private void updateCart() {
        double total = cart.values().stream().mapToDouble(Product::getSubtotal).sum();
        total = Math.max(0, total); // Общая сумма не может быть меньше 0

        System.out.println("Обновление корзины. Общая сумма: $" + total);

        // Здесь вы можете обновить пользовательский интерфейс, например, обновить Label в JavaFX
    }

    private static class Product {
        private final int productId;
        private final double productPrice;
        private int quantity;

        public Product(int productId, double productPrice, int quantity) {
            this.productId = productId;
            this.productPrice = productPrice;
            this.quantity = quantity;
        }

        public int getProductId() {
            return productId;
        }

        public double getProductPrice() {
            return productPrice;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getSubtotal() {
            return quantity * productPrice;
        }
    }
}
