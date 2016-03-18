import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import jdk.internal.org.objectweb.asm.tree.analysis.Analyzer;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Base {
    List<String> NotQuestion;
    List<String> Question;
    int QuestionNumber;
    chartClass chartMaker;
    ObservableList<PieChart.Data> pieChartData;
    public Base() {
        chartMaker = new chartClass();
        this.NotQuestion = new ArrayList<String>();
        this.Question = new ArrayList<String>();

    }

    public void addToBase(String zdanie){

        this.addToList(zdanie);
    }

    private void addToList(String zdanie){
        List<String> tmp;
      if(this.QuestionOrNot(zdanie)==true){
            tmp = this.Question;
          this.QuestionNumber++;
      }
        else
            tmp = this.NotQuestion;



        String[] arr = zdanie.split(" ");
        for ( String ss : arr) {
              tmp.add(ss);

    }


    }
    private boolean QuestionOrNot(String zdanie){
        // 1 - Question
        // 0 - Not Question
         boolean tmp;
        if(zdanie.contains("?")){
            tmp = true;
        }
        else
            tmp = false;

    return tmp;
}




}
