package com.example.wsn;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ResultController implements Initializable {


    @FXML
    private BarChart BarChart;
    private float Tstart;
    private float Pstart;
    private float R;
    private float Ptxelc;



    public void Variable(float Tstart,float Pstart,float R,float Ptxelc){
        this.Pstart=Pstart;
        this.Tstart=Tstart;
        this.R=R;
        this.Ptxelc=Ptxelc;
    }

    ObservableList<Float> resultats;

    public void resultat(ObservableList<Node> noeuds){
        for(Node n:noeuds){
            resultats.add((this.Tstart*this.Pstart)+((this.Ptxelc+n.getPamp())*(n.getN()/(this.R*n.getRcode()))));
        }
    }


    public BarChart getBarChart() {
        return BarChart;
    }

    public void setBarChart(BarChart BarChart) {
        this.BarChart = BarChart;
    }


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XYChart.Series serie= new XYChart.Series();
        serie.setName("energie");
        int i=1;
        for(Float f:resultats){
            serie.getData().add(new XYChart.Data("noeud"+i,f));
            i++;
        }



        BarChart.getData().addAll(serie);
    }

}

