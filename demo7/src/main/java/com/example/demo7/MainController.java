package com.example.demo7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MainController {

    @FXML
    private Button AddProduct1Button;

    @FXML
    private Button AddProduct2Button;

    @FXML
    private Button AddProduct3Button;

    @FXML
    private Button DeleteProduct1Button;

    @FXML
    private Button DeleteProduct2Button;

    @FXML
    private Button DeleteProduct3Button;

    @FXML
    private Label testProduct1;
    @FXML
    private Label testProduct2;
    @FXML
    private Label testProduct3; 
    @FXML
    private AnchorPane FirstProduct;

    @FXML
    private AnchorPane SecondProduct;

    @FXML
    private AnchorPane ThirdProduct;

    @FXML
    private Label TotalPrice;

    private int product1Price = 10;
    private int product2Price = 20;
    private int product3Price = 30;

    private int totalPriceValue = 0;

    @FXML
    void handleAddProduct(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        int productPrice = 0;

        if (sourceButton == AddProduct1Button) {
            productPrice = product1Price;
        } else if (sourceButton == AddProduct2Button) {
            productPrice = product2Price;
        } else if (sourceButton == AddProduct3Button) {
            productPrice = product3Price;
        }

        totalPriceValue += productPrice;
        updateTotalPriceLabel();
    }

    @FXML
    void handleDeleteProduct(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        int productPrice = 0;

        if (sourceButton == DeleteProduct1Button) {
            productPrice = product1Price;
            testProduct1.setText("You added product " + product1.getText());
        } else if (sourceButton == DeleteProduct2Button) {
            productPrice = product2Price;
        } else if (sourceButton == DeleteProduct3Button) {
            productPrice = product3Price;
        }

        if (totalPriceValue - productPrice >= 0) {
            totalPriceValue -= productPrice;
        } else {
            totalPriceValue = 0;
        }

        updateTotalPriceLabel();
    }

    private void updateTotalPriceLabel() {
        TotalPrice.setText(String.valueOf(totalPriceValue));
    }
}
