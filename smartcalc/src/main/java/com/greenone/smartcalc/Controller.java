package com.greenone.smartcalc;

import javafx.event.ActionEvent;
import javafx.event.Event;
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
    private static ListView<String> listViewShow;///

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

    @FXML
    public void TanButtonClick() {
        layerMenu.setText(layerMenu.getText() + "tan(");
    }

    @FXML
    public void CosButtonClick() {
        layerMenu.setText(layerMenu.getText() + "cos(");
    }

    @FXML
    public void SinButtonClick() {
        layerMenu.setText(layerMenu.getText() + "sin(");
    }

    @FXML
    public void AtanButtonClick() {
        layerMenu.setText(layerMenu.getText() + "atan(");
    }

    @FXML
    public void AsinButtonClick() {
        layerMenu.setText(layerMenu.getText() + "asin(");
    }

    @FXML
    public void AcosButtonClick() {
        layerMenu.setText(layerMenu.getText() + "acos(");
    }

    @FXML
    public void LogButtonClick() {
        layerMenu.setText(layerMenu.getText() + "log(");
    }

    @FXML
    public void LnButtonClick() {
        layerMenu.setText(layerMenu.getText() + "ln(");
    }

    @FXML
    public void DevButton() {
        layerMenu.setText(layerMenu.getText() + "/");
    }

    @FXML
    public void MulButtonClick() {
        layerMenu.setText(layerMenu.getText() + "*");
    }

    @FXML
    public void SubButtonClick() {
        layerMenu.setText(layerMenu.getText() + "-");
    }

    @FXML
    public void PlusButtonClick() {
        layerMenu.setText(layerMenu.getText() + "+");
    }

    @FXML
    public void PiButtonClick() {
        layerMenu.setText(layerMenu.getText() + "π");
    }

    @FXML
    public void PowButtonClick() {
        layerMenu.setText(layerMenu.getText() + "^");
    }

    @FXML
    public void ModButtonClick() {
        layerMenu.setText(layerMenu.getText() + "mod(");
    }

    @FXML
    public void SqrtButtonClick() {
        layerMenu.setText(layerMenu.getText() + "sqrt(");
    }

    @FXML
    public void XButtonClick() {
        layerMenu.setText(layerMenu.getText() + "x");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    public void CEButtonClick() {
        if (layerMenu.getText().length() > 0) {
            layerMenu.setText(layerMenu.getText().substring(0, layerMenu.getText().length() - 1));
        }
    }

    @FXML
    public void LeftBracketButtonClick() {
        layerMenu.setText(layerMenu.getText() + "(");
    }

    @FXML
    public void RightBracketButtonClick() {
        layerMenu.setText(layerMenu.getText() + ")");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    public void ResultButtonClick() {
//        Object selectedItem = listViewHistory.getSelectionModel().getSelectedItem();
//        if (selectedItem != null) {
//            layerMenu.setText(selectedItem.toString());
//            listViewHistory.getItems().remove(selectedItem);
//            listViewHistory.getSelectionModel().clearSelection();
//        } else if (selectedItem == null && layerMenu.getText().length() > 0 && layerMenu.getText().length() < 256) {
//            listViewHistory.getItems().add(layerMenu.getText());
//            layerMenu.setText(nativeLib.Arithmetic(layerMenu.getText(), TextField.getText()).replaceAll("\\.?0*$", ""));
//            listViewHistory.scrollTo(listViewHistory.getItems().size() - 1);
//        } else if (selectedItem == null && layerMenu.getText().length() == 0) {
//            listViewHistory.getItems().clear();
//        }
//        listViewShow = listViewHistory;
        //---------------------------------------------------------------------------------------------

//        System.out.println(layerMenu.getText());
//        System.out.println(TextField.getText());
//
//        nativeLib.MainFunRunner(layerMenu.getText(), TextField.getText());
        System.out.println("----------------------------------------------------------------");
//        System.out.println(nativeLib.ReturnDouble());

        String result = nativeLib.MainFunRunner(layerMenu.getText(), TextField.getText());
        System.out.println(result);
        layerMenu.setText(result);

//        System.out.println(nativeLib.MainFunRunner(layerMenu.getText(), TextField.getText()));
//        layerMenu.setText(String.valueOf(nativeLib.MainFunRunner(layerMenu.getText(), TextField.getText())));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    public void clearClick() {
        listViewShow.getItems().clear();
        listViewHistory.getItems().clear();
    }

    @FXML
    public void GraphButtonClick() {
        layerMenu1.setText(layerMenu.getText());
        try {
            double min = (MinTextField == null || MinTextField.getText().equals(""))? -10.0 : Double.parseDouble(MinTextField.getText());
            double max = (MaxTextField == null || MaxTextField.getText().equals(""))? 10.0 : Double.parseDouble(MaxTextField.getText());
            if(min < max) Plot(min, max);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void TextChange() {
        GraphButtonClick();
    }

    @FXML
    private void initialize() {
        nativeLib = new NativeLib();
//        listViewShow = listViewHistory;
//        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/history.txt"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                listViewHistory.getItems().add(line);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void Plot(double min, double max) {
//        if (LineChart.getData().size() > 0) LineChart.getData().clear();
//        XYChart.Series series = new XYChart.Series();
//        series.setName("Graph");
//        if (!nativeLib.MainFunRunner(layerMenu.getText())) return;
//        if(min < -1000000.0 || max > 1000000.0) return;
//        double N = 100.0;
//        double h = (max - min) / N;
//
//        for (double i = min; i < max; i+=h) {
//            DecimalFormat df = new DecimalFormat("#.##");
//            String str = String.valueOf(df.format(i));
////            System.out.println("min = " + min + ", max = " + max + ", h = " + h +", i = " + i + ", str = " + str);
//            if (Double.isFinite(nativeLib.Graph(str)))
//                series.getData().add(new XYChart.Data<>(str, nativeLib.Graph(str)));
//        }
//
//        LineChart.getData().add(series);
//        LineChart.setCreateSymbols(false);
//        LineChart.setLegendVisible(false);
//        LineChart.setAnimated(false);
//        LineChart.setCreateSymbols(false);
//        LineChart.setLegendVisible(false);
//        LineChart.setAnimated(false);

    }

    public static void SaveHistory() {
        String historyFile = "src/main/resources/history.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(historyFile))) {
            for (int i = 0; listViewShow != null && i < listViewShow.getItems().size(); i++) {
                writer.write(listViewShow.getItems().get(i).toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}