package com.greenone.smartcalc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import java.text.DecimalFormat;
import java.io.*;
import com.greenone.smartcalc.NativeLib;

public class Controller {
    private NativeLib nativeLib;
    private static ListView<String> listViewCopy;///

    @FXML
    private Label layerMenu;

    @FXML
    private Label layerMenu1;

    @FXML
    private TextField TextField;

    ///
    @FXML
    private LineChart<Number, Number> LineChart;
    ///
    @FXML
    private ListView<String> listViewHistory;
    ///
    @FXML
    private TextField MaxTextField;
    ///
    @FXML
    private TextField MinTextField;

//    -----------------------------------------------------------
    @FXML
    protected void Button0Click() {
        layerMenu.setText(layerMenu.getText() + "0");
    }

    @FXML
    protected void Button1Click() {
        layerMenu.setText(layerMenu.getText() + "1");
    }

    @FXML
    protected void Button2Click() {
        layerMenu.setText(layerMenu.getText() + "2");
    }

    @FXML
    protected void Button3Click() {
        layerMenu.setText(layerMenu.getText() + "3");
    }

    @FXML
    protected void Button4Click() {
        layerMenu.setText(layerMenu.getText() + "4");
    }

    @FXML
    protected void Button5Click() {
        layerMenu.setText(layerMenu.getText() + "5");
    }

    @FXML
    protected void Button6Click() {
        layerMenu.setText(layerMenu.getText() + "6");
    }

    @FXML
    protected void Button7Click() {
        layerMenu.setText(layerMenu.getText() + "7");
    }

    @FXML
    protected void Button8Click() {
        layerMenu.setText(layerMenu.getText() + "8");
    }

    @FXML
    protected void Button9Click() {
        layerMenu.setText(layerMenu.getText() + "9");
    }


    @FXML
    protected void DotButtonClick() {
        layerMenu.setText(layerMenu.getText() + ".");
    }

    @FXML
    protected void ACButtonClick() {
        layerMenu.setText("");
    }



    public static void SaveHistory() {
        String historyFile = "src/main/resources/history.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(historyFile))) {
            for (int i = 0; listViewCopy != null && i < listViewCopy.getItems().size(); i++) {
                writer.write(listViewCopy.getItems().get(i).toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}