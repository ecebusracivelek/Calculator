package com.example.calculatorapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.ArcTo;

import javax.swing.*;

public class CalculatorController {
    @FXML
    Button btnPercentage;
    @FXML
    Button btnClearEntry;
    @FXML
    Button btnClear;
    @FXML
    Button btnDelete;
    @FXML
    Button btnReciprocal;
    @FXML
    Button btnSquare;
    @FXML
    Button btnSquareRoot;
    @FXML
    Button btnDivision;
    @FXML
    Button btnSeven;
    @FXML
    Button btnEight;
    @FXML
    Button btnNine;
    @FXML
    Button btnMultiplication;
    @FXML
    Button btnFour;
    @FXML
    Button btnOne;
    @FXML
    Button btnNegate;
    @FXML
    Button btnFive;
    @FXML
    Button btnSix;
    @FXML
    Button btnSubtraction;
    @FXML
    Button btnTwo;
    @FXML
    Button btnZero;
    @FXML
    Button btnThree;
    @FXML
    Button btnAddition;
    @FXML
    Button btnDecimalPoint;
    @FXML
    Button btnEqual;
    @FXML
    Label display;

    private Calc model = new Calc();
    private double number = 0;
    private boolean newOperation = true;

    @FXML
    private void handleNumberAction(ActionEvent event){
        String value = ((Button) event.getSource()).getText();
        if(newOperation){
            display.setText("");
            newOperation = false;
        }
        display.setText(display.getText() + value);
    }

    @FXML
    private void handleOperatorAction(ActionEvent event){
        String value = ((Button) event.getSource()).getText();
        if(!display.getText().isEmpty()){
            number = Double.parseDouble(display.getText());
        }
        model.calculate(number, model.getCurrentOperation());
        display.setText(String.valueOf(model.getResult()));
        model.setCurrentOperation(value);
        newOperation = true;
    }

    @FXML
    private void handlePercentageAction(ActionEvent event){
        double value = Double.parseDouble(display.getText());
        model.calculate(value, "%");
        display.setText(String.valueOf(model.getResult()));
    }

    @FXML
    private void handleClearEntryAction(ActionEvent event){
        display.setText("");
    }

    @FXML
    private void handleClearAction(ActionEvent event){
        model.clear();
        display.setText("");
    }

    @FXML
    private void handleDeleteAction(ActionEvent event){
        String currentText = display.getText();
        if(!currentText.isEmpty())
            display.setText(currentText.substring(0, currentText.length() - 1));

        if(display.getText().isEmpty())
            display.setText("");
    }

    @FXML
    private void handleReciprocalAction(ActionEvent event){
        try{
            double value = Double.parseDouble(display.getText());
            model.calculate(value, "1/x");
            display.setText(String.valueOf(model.getResult()));
        }catch(ArithmeticException e){
            display.setText("Error");
        }
    }

    @FXML
    private void handleSquareAction(ActionEvent event){
        double value = Double.parseDouble(display.getText());
        model.calculate(value, "x²");
        display.setText(String.valueOf(model.getResult()));
    }

    @FXML
    private void handleSquareRootAction(ActionEvent event){
        try{
            double value = Double.parseDouble(display.getText());
            model.calculate(value, " √x");
            display.setText(String.valueOf(model.getResult()));
        }catch (ArithmeticException e) {
            display.setText("Error");
        }
    }

    @FXML
    private void handleNegateAction(ActionEvent event){
        double value = Double.parseDouble(display.getText());
        value *= -1;
        display.setText(String.valueOf(value));
    }

    @FXML
    private void handleDecimalPointAction(ActionEvent event){
        if(!display.getText().contains(",")){
            display.setText(display.getText() + ",");
        }
    }

    @FXML
    private void handleEqualsAction(ActionEvent event){
        try{
            number = Double.parseDouble(display.getText());
            model.calculate(number, model.getCurrentOperation());
            display.setText(String.valueOf(model.getResult()));
            model.setCurrentOperation("");
            newOperation = true;
        }catch (Exception e){
            display.setText("Error");
        }
    }
}
