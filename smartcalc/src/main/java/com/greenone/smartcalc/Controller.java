package com.greenone.smartcalc;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import java.text.DecimalFormat;
import java.io.*;
import com.greenone.smartcalc.NativeLib;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {
    private NativeLib nativeLib;
    private static ListView<String> listViewCopy;///
//    private String TextFieldXSave;

    @FXML
    private TextField InputLable;

    @FXML
    private Label ErrorLable;

    @FXML
    private Label InputLable1;

    @FXML
    private TextField TextFieldX;

    @FXML
    private Button AddButton;

    @FXML
    private Button CleanButton;
    ///
    @FXML
    private LineChart<Number, Number> LineChart;
    ///
    @FXML
    private ListView<String> listViewShow;
    ///
    @FXML
    private TextField MaxTextField;
    ///
    @FXML
    private TextField MinTextField;

//    -----------------------------------------------------------
    @FXML
    protected void Button0Click() {
        InputLable.setText(InputLable.getText() + "0");
    }

    @FXML
    protected void Button1Click() {
        InputLable.setText(InputLable.getText() + "1");
    }

    @FXML
    protected void Button2Click() {
        InputLable.setText(InputLable.getText() + "2");
    }

    @FXML
    protected void Button3Click() {
        InputLable.setText(InputLable.getText() + "3");
    }

    @FXML
    protected void Button4Click() {
        InputLable.setText(InputLable.getText() + "4");
    }

    @FXML
    protected void Button5Click() {
        InputLable.setText(InputLable.getText() + "5");
    }

    @FXML
    protected void Button6Click() {
        InputLable.setText(InputLable.getText() + "6");
    }

    @FXML
    protected void Button7Click() {
        InputLable.setText(InputLable.getText() + "7");
    }

    @FXML
    protected void Button8Click() {
        InputLable.setText(InputLable.getText() + "8");
    }

    @FXML
    protected void Button9Click() {
        InputLable.setText(InputLable.getText() + "9");
    }


    @FXML
    protected void DotButtonClick() {
        InputLable.setText(InputLable.getText() + ".");
    }

    @FXML
    protected void ACButtonClick() {
        InputLable.setText("");
        ErrorLable.setText("");
    }

    @FXML
    public void TanButtonClick() {
        InputLable.setText(InputLable.getText() + "tan(");
    }

    @FXML
    public void CosButtonClick() {
        InputLable.setText(InputLable.getText() + "cos(");
    }

    @FXML
    public void SinButtonClick() {
        InputLable.setText(InputLable.getText() + "sin(");
    }

    @FXML
    public void AtanButtonClick() {
        InputLable.setText(InputLable.getText() + "atan(");
    }

    @FXML
    public void AsinButtonClick() {
        InputLable.setText(InputLable.getText() + "asin(");
    }

    @FXML
    public void AcosButtonClick() {
        InputLable.setText(InputLable.getText() + "acos(");
    }

    @FXML
    public void LogButtonClick() {
        InputLable.setText(InputLable.getText() + "log(");
    }

    @FXML
    public void LnButtonClick() {
        InputLable.setText(InputLable.getText() + "ln(");
    }

    @FXML
    public void DevButton() {
        InputLable.setText(InputLable.getText() + "/");
    }

    @FXML
    public void MulButtonClick() {
        InputLable.setText(InputLable.getText() + "*");
    }

    @FXML
    public void SubButtonClick() {
        InputLable.setText(InputLable.getText() + "-");
    }

    @FXML
    public void PlusButtonClick() {
        InputLable.setText(InputLable.getText() + "+");
    }

    @FXML
    public void PiButtonClick() {
        InputLable.setText(InputLable.getText() + "π");
    }

    @FXML
    public void PowButtonClick() {
        InputLable.setText(InputLable.getText() + "^");
    }

    @FXML
    public void ModButtonClick() {
        InputLable.setText(InputLable.getText() + "mod");
    }

    @FXML
    public void SqrtButtonClick() {
        InputLable.setText(InputLable.getText() + "sqrt(");
    }

    @FXML
    public void XButtonClick() {
        InputLable.setText(InputLable.getText() + "x");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    public void CEButtonClick() {
        if (InputLable.getText().length() > 0) {
            InputLable.setText(InputLable.getText().substring(0, InputLable.getText().length() - 1));
            ErrorLable.setText("");
        }
    }

    @FXML
    public void LeftBracketButtonClick() {
        InputLable.setText(InputLable.getText() + "(");
    }

    @FXML
    public void RightBracketButtonClick() {
        InputLable.setText(InputLable.getText() + ")");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    public void ResultButtonClick() {
//        Object selectedItem = listViewShow.getSelectionModel().getSelectedItem();
//        if (selectedItem != null) {
//            InputLable.setText(selectedItem.toString());
//            listViewShow.getItems().remove(selectedItem);
//            listViewShow.getSelectionModel().clearSelection();
//        } else if (selectedItem == null && InputLable.getText().length() > 0 && InputLable.getText().length() < 256) {
//            listViewShow.getItems().add(InputLable.getText());
//            InputLable.setText(nativeLib.MainFunRunner(InputLable.getText(), TextFieldX.getText()));
//            listViewShow.scrollTo(listViewShow.getItems().size() - 1);
//        } else if (selectedItem == null && InputLable.getText().length() == 0) {
//            listViewShow.getItems().clear();
//        }
//        listViewCopy = listViewShow;
        //---------------------------------------------------------------------------------------------
//        System.out.println("----------------------------------------------------------------");
//        String result = nativeLib.MainFunRunner(InputLable.getText(), TextFieldX.getText());
//        System.out.println(result);
//        ErrorLable.setText(result);


//        AddButton

        if (TextFieldX.getText().isEmpty()) {
            TextFieldX.setText("0");
        }
        if (!InputLable.getText().isEmpty() && InputLable.getText().length() < 256) {
            listViewShow.getItems().add(InputLable.getText());
        }
        ErrorLable.setText(nativeLib.MainFunRunner(InputLable.getText(), TextFieldX.getText()));
    }

    @FXML
    public void AddButtonClick() {
//        listViewShow.setVisible(false);
//        AddButton.setVisible(false);
        Object selectedItem = listViewShow.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            InputLable.setText(selectedItem.toString());
            ErrorLable.setText("");
        }
    }

    @FXML
    protected void CleanButtonClick() {
        listViewShow.getItems().clear();
        listViewCopy.getItems().clear();
    }

    @FXML
    public void HistoryButtonClick() {
        if (listViewShow.isVisible()) {
            listViewShow.setVisible(false);
            AddButton.setVisible(false);
            CleanButton.setVisible(false);
        } else {
            listViewShow.setVisible(true);
            AddButton.setVisible(true);
            CleanButton.setVisible(true);
        }
//        Object selectedItem = listViewShow.getSelectionModel().getSelectedItem();
//        if (selectedItem != null) {
//            InputLable.setText(selectedItem.toString());
//            ErrorLable.setText("");
//        }
    }

    @FXML
    public void GraphButtonClick() {
        try {
            double min = (MinTextField == null || MinTextField.getText().equals(""))? -10.0 : Double.parseDouble(MinTextField.getText());
            double max = (MaxTextField == null || MaxTextField.getText().equals(""))? 10.0 : Double.parseDouble(MaxTextField.getText());
            if(min < max) {
                Plot(min, max);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void TextChange() {
        int val_result; //0 - invalid, 1 - valid int, 2 - valid double.
        InputLable1.setText(InputLable.getText());
        if (InputLable.isFocused()) {
            InputLable1.setText(InputLable.getText());
        }
        if (TextFieldX.isFocused()) {
            System.out.println("X in focus _________________________________________________");
            System.out.println(nativeLib.FieldValidatorIntDouble(TextFieldX.getText()));
            if (nativeLib.FieldValidatorIntDouble(TextFieldX.getText()) == 0) {
                System.out.println("X changed");
//                TextFieldX.setText(TextFieldXSave);
                TextFieldX.setText("0");
            }
        }
    }

    @FXML
    private void initialize() {
        nativeLib = new NativeLib();
        listViewShow.setVisible(false);
        AddButton.setVisible(false);
        CleanButton.setVisible(false);
        MinTextField.setText("-10");
        MaxTextField.setText("10");

        listViewCopy = listViewShow;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/history.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                listViewShow.getItems().add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Plot(double min, double max) {
        System.out.println(min + " " + max);/////////

        if (LineChart.getData().size() > 0) {
            LineChart.getData().clear();
        }
        XYChart.Series series = new XYChart.Series();
        series.setName("Graph");
        try {
            Double.parseDouble(nativeLib.MainFunRunner(InputLable.getText(), TextFieldX.getText()));
        } catch (Exception e) {
            System.out.println("try_string == null ---------------------------");///////////////////
            e.printStackTrace();
            return;
        }

        if(min < -1000000.0 || max > 1000000.0) {
            return;
        }
        double N = 100.0;
        double h = (max - min) / N;

        for (double i = min; i < max; i+=h) {
            DecimalFormat df = new DecimalFormat("#.##");
            String str = String.valueOf(df.format(i));
            System.out.println(str + "____________________________");

//            System.out.println("min = " + min + ", max = " + max + ", h = " + h +", i = " + i + ", str = " + str);
//            double num = Double.parseDouble(nativeLib.MainFunRunner(InputLable.getText(), String.valueOf(i)));

            double num = Double.parseDouble(nativeLib.MainFunRunner(InputLable.getText(), str));
            if (Double.isFinite(num)) {
                series.getData().add(new XYChart.Data<>(str, num));
            }
        }

        LineChart.getData().add(series);
        LineChart.setCreateSymbols(false);
        LineChart.setLegendVisible(false);
        LineChart.setAnimated(false);
        LineChart.setCreateSymbols(false);
        LineChart.setLegendVisible(false);
        LineChart.setAnimated(false);

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