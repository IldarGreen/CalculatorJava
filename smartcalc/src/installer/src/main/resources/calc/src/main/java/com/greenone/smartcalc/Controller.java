package com.greenone.smartcalc;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import java.text.DecimalFormat;
import java.io.*;

public class Controller {
    private NativeLib nativeLib;
    private static String historyPath;
    private static ListView<String> listViewCopy;

    @FXML
    private TextField InputLable;

    @FXML
    private Label ErrorLable;

    @FXML
    private TextField TextFieldX;

    @FXML
    private Button AddButton;

    @FXML
    private Button CleanButton;

    @FXML
    private LineChart<Number, Number> LineChart;

    @FXML
    private ListView<String> listViewShow;

    @FXML
    private TextField MaxTextField;

    @FXML
    private TextField MinTextField;

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

    @FXML
    public void LeftBracketButtonClick() {
        InputLable.setText(InputLable.getText() + "(");
    }

    @FXML
    public void RightBracketButtonClick() {
        InputLable.setText(InputLable.getText() + ")");
    }

    @FXML
    public void CEButtonClick() {
        if (InputLable.getText().length() > 0) {
            InputLable.setText(InputLable.getText().substring(0, InputLable.getText().length() - 1));
            ErrorLable.setText("");
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
        String jarPath = SmartCalcApplication.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        historyPath = jarPath.substring(0, jarPath.lastIndexOf("/")) + "/history.txt";
        if (!new File(historyPath).exists()) {/////////////////////////////////
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(historyPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                listViewShow.getItems().add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/history.txt"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                listViewShow.getItems().add(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @FXML
    public void ResultButtonClick() {
        if (TextFieldX.getText().isEmpty()) {
            TextFieldX.setText("0");
        }
        if (!InputLable.getText().isEmpty() && InputLable.getText().length() < 256) {
            listViewShow.getItems().add(InputLable.getText());
        }
        ////////////////////////////////////////////////////////
        System.out.println("nativeLib.MainFunRunner = " + nativeLib.MainFunRunner(InputLable.getText(), TextFieldX.getText()));
        ErrorLable.setText(nativeLib.MainFunRunner(InputLable.getText(), TextFieldX.getText()));
    }

    @FXML
    public void AddButtonClick() {
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
    }

    @FXML
    public void TextChange() {
        int val_result; //0 - invalid, 1 - valid int, 2 - valid double.
        if (TextFieldX.isFocused()) {
            if (nativeLib.FieldValidatorIntDouble(TextFieldX.getText()) == 0) {
                TextFieldX.setText("0");
            }
        }
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
    protected void ReplotButtonClick() {
        GraphButtonClick();
    }

    private void Plot(double min, double max) {
        LineChart.getData().clear();
        XYChart.Series series = new XYChart.Series();
        series.setName("Graph");
//        try {
//            Double.parseDouble(nativeLib.MainFunRunner(InputLable.getText(), TextFieldX.getText()));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }

        if(min < -1000000.0 || max > 1000000.0) {
            return;
        }
        double N = 500.0;
        double h = (max - min) / N;
        double num;

        for (double i = min; i < max; i+=h) {
            DecimalFormat df = new DecimalFormat("#.##");
            String str = String.valueOf(df.format(i));
            try {
                num = Double.parseDouble(nativeLib.MainFunRunner(InputLable.getText(), str));
                series.getData().add(new XYChart.Data<>(str, num));
            } catch (Exception e) {
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
//        String historyFile = "src/main/resources/history.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(historyPath))) {
            for (int i = 0; listViewCopy != null && i < listViewCopy.getItems().size(); i++) {
                writer.write(listViewCopy.getItems().get(i).toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}