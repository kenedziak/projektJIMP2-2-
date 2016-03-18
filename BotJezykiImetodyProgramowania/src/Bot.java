import javax.swing.*;
import javax.xml.stream.events.EndDocument;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Damian on 2015-05-06.
 */
public class Bot {
        File bottxtBase;
        int nGram=2;
        boolean uczSie=false;
        boolean uzyjBazy=false;
        GUI_FORM botGUI;
        Base myBotBase;
        Random rn;


    public  Bot (String botName){
        this.myBotBase = new Base();
        this.botGUI = new GUI_FORM(new JFrame(),botName,this);
        bottxtBase =  new File("C:\\Users\\Damian\\IdeaProjects\\BotJezykiImetodyProgramowania\\src\\BotBase");
    }


public void zmienKorzystanieZBazyDanych(){
    this.uzyjBazy = !this.uzyjBazy;
    System.out.println("Kozystanie z bazy Danych: "+this.uzyjBazy);
    String sentence = " ";
    try {
        Scanner tmpScanner = new Scanner (bottxtBase);
        if(this.uzyjBazy){
            while(tmpScanner.hasNextLine()){
                sentence= tmpScanner.nextLine();
                    System.out.println(sentence);
                myBotBase.addToBase(sentence);

            }
        }

    } catch (FileNotFoundException e) {
        System.out.println("Problem z plikiem danych");

    }


}
    public void zmienUczenieSie(){
        this.uczSie = !this.uczSie;
        System.out.println("Uczenie sie bota: "+this.uczSie);

    }
    public void zmienNGram(int newN){
        this.nGram =  newN;
        System.out.println("Bot n-gram: "+this.nGram);
    }


    public  String   odpowiedz(String zdanie){
        String sentence;
        int tmp=-1;
        int randtmp=-1;
        // Random randomint = new Random();
        //tmp = randomint.nextInt(2);
        randtmp = new Random().nextInt(2);
        if(uczSie==true){
            tmp=myBotBase.NotQuestion.size();
            myBotBase.addToBase(zdanie);
            try {
                PrintWriter save = new PrintWriter(bottxtBase);
                save.println(zdanie);
                save.close ();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        System.out.println(randtmp);
        if(randtmp == 1){
            sentence = this.makeQuestion();
        }
            else {
            sentence =  this.makeAnswer();
        }
        return sentence;

    }
    public String makeAnswer(){
        String Answer = new String("");
        if(myBotBase.NotQuestion.isEmpty()) return Answer;

        Random rn = new Random();
        int SizeOfAnswer = 7;
        int NumberOfAction = SizeOfAnswer/this.nGram;
        for(int i=0 ; i < NumberOfAction;i++){
            int randomInt = rn.nextInt((myBotBase.NotQuestion.size()) + 1);
            for(int j=0;j <this.nGram  &&  randomInt < myBotBase.NotQuestion.size() ;j++){
                    Answer = Answer + " " + myBotBase.NotQuestion.get(randomInt);

                    randomInt++;
                }

        }

        return Answer;

    }


     public String  makeQuestion(){
         String Question = "";
         if(myBotBase.Question.isEmpty()) return Question;
         Random rn = new Random();
         int k=0;
         int nrZdania=0;
         int randomInt = rn.nextInt(myBotBase.QuestionNumber );
         while(randomInt != nrZdania) {
             if (myBotBase.Question.get(k).contains("?")) {
                 nrZdania++;

             }
             k++;

         }
           while(!Question.contains("?")){
                Question = Question +" "+ myBotBase.Question.get(k);
                k++;
            }

         return Question;
         }

     }










