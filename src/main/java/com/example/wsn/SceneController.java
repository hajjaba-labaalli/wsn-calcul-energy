package com.example.wsn;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kotlin.Suppress;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class SceneController {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    private TextField nbrNodes;
    @FXML
    private TextField Tstart;
    @FXML
    private TextField Pstart;
    @FXML
    private TextField R;
    @FXML
    private TextField Ptxelc;
    @FXML
    private Button submit;
    @FXML
    private Label label;
    public void submit(ActionEvent event){
        try{
            Global.nbrNodes = Integer.parseInt(nbrNodes.getText());
            Global.Tstart = Float.parseFloat(Tstart.getText());
            Global.Pstart = Float.parseFloat(Pstart.getText());
            Global.R = Float.parseFloat(R.getText());
            Global.Ptxelc = Float.parseFloat(Ptxelc.getText());
            label.setText("success");

        }
        catch (NumberFormatException e){
            label.setText("Enter only numbers please");
        }
        catch (Exception e){
            label.setText("error");
        }

    }
    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void switchToScene2(ActionEvent event) throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("table_nodes.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene3(ActionEvent event) throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("result.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    // Table View
    @FXML
    private TableView<com.example.wsn.Node> tableView;

    // Table columns
    @FXML
    private TableColumn<com.example.wsn.Node, Integer> nbitsColumn ;
    @FXML
    private TableColumn<com.example.wsn.Node, Float> RcodeColumn ;
    @FXML
    private TableColumn<com.example.wsn.Node, Float> EamplColumn ;
    @FXML
    private TableColumn<com.example.wsn.Node, Float> EnergieColumn ;

    // inputs
    @FXML
    private TextField Eampl;
    @FXML
    private TextField Rcode;
    @FXML
    private TextField n;
    @FXML
    void removeCustomer(ActionEvent event) {
        try {
            int selectedID = tableView.getSelectionModel().getSelectedIndex();
            tableView.getItems().remove(selectedID);
            label1.setText("OK");
        }
        catch (Exception e){
            label1.setText("Please select an item ");
        }
    }
    @FXML
    private Label label1;
    @FXML
    void submit1(ActionEvent event) {
        try {
            com.example.wsn.Node node = new com.example.wsn.Node(Integer.parseInt(n.getText()),
                    Float.parseFloat(Rcode.getText()),
                    Float.parseFloat(Eampl.getText()));
            node.setEnergie(calculEnergie(node.n, node.Rcode, node.getPamp()));
            Global.Nodes = (ObservableList<com.example.wsn.Node>) tableView.getItems();
            Global.Nodes.add(node);
            tableView.setItems(Global.Nodes);
            initialize(0);
            label1.setText("success");
        }
        catch (NumberFormatException e){
            label1.setText("Enter only numbers please");
        }
        catch (Exception e){
            label1.setText("error");
        }
    }
    @FXML
      public void initialize(int i) {
        nbitsColumn.setCellValueFactory(new PropertyValueFactory<com.example.wsn.Node, Integer>("n"));
        RcodeColumn.setCellValueFactory(new PropertyValueFactory<com.example.wsn.Node, Float>("Rcode"));
        EamplColumn.setCellValueFactory(new PropertyValueFactory<com.example.wsn.Node, Float>("Pamp"));
        EnergieColumn.setCellValueFactory(new PropertyValueFactory<com.example.wsn.Node, Float>("Energie"));

     }
    public float calculEnergie(int n, float Rcode, float Pam){
        return (Global.Tstart*Global.Pstart)+((Global.Ptxelc+Pam)*(n/(Global.R*Rcode)));
    }
    @FXML
    public NumberAxis caEnergie;
    @FXML
    public CategoryAxis caNoeud ;
    public BarChart<String, Float> BarChart;
    @FXML
    public AnchorPane results;
    @FXML
    private Label label2;
    public void initialize1() {
        try {
            int i = 1;
            for (com.example.wsn.Node node : Global.Nodes) {
                XYChart.Series<String, Float> series = new XYChart.Series<>();
                series.setName("Node" + i);
                series.getData().add(new XYChart.Data<>("", node.getEnergie()));
                BarChart.getData().add(series);
                i++;
            }
            label2.setText("Good :-)");
        }catch (Exception ignored){
            label2.setText("Please enter a valid value");
        }

    }

}