/**
 * Created by Damian on 2015-06-13.
 */
public class bestRecordsTable {
    int intable;
    int size;
    String []  word;
    int    []  frequency;

    public bestRecordsTable() {
        this.size = 100;
        this.word = new  String [size];
        this.frequency = new int[size];
        this.intable = 0;
    }
    public bestRecordsTable(int s) {
        this.size = s;
        this.word = new  String [size];
        this.frequency = new int[size];
        this.intable = 0;
    }



    public void wordAdder (String k){
        int c= -1;
        boolean madeActions = false;
        for(int i=0 ; i < intable ; i++ ){
            if(word[i].equals(k)) {
                frequency[i] ++;
                madeActions = true;
                break;
            }
        }
        if(!madeActions){
            this.word[intable] = k;
            this.frequency[intable] ++;
            intable++;
        }



    }


}
