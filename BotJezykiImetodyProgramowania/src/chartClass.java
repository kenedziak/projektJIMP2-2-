import javafx.scene.chart.PieChart;

import java.io.File;
import java.util.List;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.data.general.DefaultPieDataset;
import java.io.File;
/**
 * Created by Damian on 2015-06-13.
 */



public class chartClass {
    bestRecordsTable recordsData;
    JFreeChart chart;

    public void UpStatsAndChart(List<String> wl){
        int i = 0;
        if(wl.isEmpty()){
            DefaultPieDataset pieDataset = new DefaultPieDataset();
            pieDataset.setValue("nic", 100);
            chart= ChartFactory.createPieChart("Statystyki slow", pieDataset, true, true, false);
        }
            else{

        recordsData = new bestRecordsTable(1000);
        String tmp = wl.get(i);
        while(i < wl.size()) {
            recordsData.wordAdder(tmp);
            i++;
            if(i==wl.size()) break;
            tmp = wl.get(i);
        }

        segregateBestRecords(recordsData);
        this.updateChart();
    }}

    public void show(){
        for(int i = 0; i < recordsData.intable;i++){
            System.out.println(recordsData.word[i]+ "  " + recordsData.frequency[i]);

        }


    }
        private void segregateBestRecords(bestRecordsTable recordsData){
            for (int i = 0; i < recordsData.intable; i++) {
                for (int j = 0; j < recordsData.intable - 1; j++) {
                    if (recordsData.frequency[j] < recordsData.frequency[j + 1]) {
                        int temp;
                        String tmp;
                        tmp = recordsData.word[j + 1];
                        recordsData.word[j + 1] = recordsData.word[j];
                        recordsData.word[j] = tmp;
                        temp = recordsData.frequency[j + 1];
                        recordsData.frequency[j + 1] = recordsData.frequency[j];
                        recordsData.frequency[j] = temp;
                    }
                }
            }}

        public void  updateChart (){
            DefaultPieDataset pieDataset = new DefaultPieDataset();
            for(int i= 0 ; i < 10; i++ ) {
                if(i > recordsData.intable-1) break;
                pieDataset.setValue(recordsData.word[i], recordsData.frequency[i]);
            }

            this.chart = ChartFactory.createPieChart("Statystyki slow",pieDataset,true,true,false);


        }



}



