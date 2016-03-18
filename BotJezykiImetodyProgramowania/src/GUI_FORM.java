import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import javax.swing.text.StringContent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Damian on 2015-05-06.
 */

public class GUI_FORM {
    private JFrame mainWin;
    private JPanel panel1;
    private JPanel DownPanel;
    private JPanel CenterPanel;
    private JLabel UpJabel;
    private JTabbedPane PanelGlowny;
    private JPanel Czat;
    private JPanel Opcje;
    private JPanel StatystykiBota;
    private JCheckBox botMaSieUczycCheckBox;
    private JCheckBox maKorzystacZWlasnejCheckBox;
    private JComboBox comboBox1;
    private JLabel graphLabel;
    private JCheckBox korzystaZWlasnejBazyCheckBox;
    private Bot bocik;
    public chartClass mychart;
    public  ImageIcon graph;
    public File graphFile;

    public GUI_FORM(JFrame mainWindow, String botName, final Bot thisbot) {
        graphLabel = new JLabel("");
        graphFile = new File("C:\\Users\\Damian\\IdeaProjects\\BotJezykiImetodyProgramowania\\src\\graph.jpg");
        mainWindow.setLocationRelativeTo(null);
        mychart= new chartClass();
        this.bocik = thisbot;
        mainWindow.setResizable(false);
        mainWin = mainWindow;
        mainWin.setSize(450, 300);
        mainWin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWin.add(panel1);
        maKorzystacZWlasnejCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisbot.zmienKorzystanieZBazyDanych();
            }
        });
        botMaSieUczycCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisbot.zmienUczenieSie();
            }
        });
        this.makeTextArea(thisbot);
        mainWin.setVisible(true);
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                thisbot.zmienNGram((Integer.parseInt(String.valueOf((String) comboBox1.getSelectedItem()))));

            }
        });


        mychart.UpStatsAndChart(bocik.myBotBase.NotQuestion);
        try {
            ChartUtilities.saveChartAsJPEG(graphFile, mychart.chart, 300, 200);

        } catch (Exception e) {
            System.out.println("Problem occurred creating chart.");
        }

        graphLabel.setText("");
        graph = new ImageIcon(graphFile.getPath());
        graphLabel.setIcon(graph);
   }


    private void makeTextArea(final Bot thisobt) {
        this.Czat.setSize(400, 200);
        this.Czat.setVisible(true);
        final JTextArea textArea = new JTextArea(5, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(380, 150));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        final JTextField userInputField = new JTextField(30);
        ActionListener Wyslij = new ActionListener() {
            public synchronized void actionPerformed(ActionEvent event) {
                String fromUser = userInputField.getText();
                if (fromUser.isEmpty() == false && fromUser != null && !fromUser.equals("") && !fromUser.equals(" ")) {
                    textArea.append("TY: " + fromUser + "\n");
                    textArea.setCaretPosition(textArea.getDocument().getLength());
                    userInputField.setText("");
                    textArea.append("BOT: " + thisobt.odpowiedz(fromUser) + "\n");
                    mychart.UpStatsAndChart(bocik.myBotBase.NotQuestion);
                    try {
                        ChartUtilities.saveChartAsJPEG(graphFile, mychart.chart, 300, 200);

                    } catch (Exception e) {

                        System.out.println("Problem: Nie mogę zaktualizować chart'a.");
                    }

                }
                graphLabel.setIcon(new ImageIcon());


            }
        };
        userInputField.addActionListener(Wyslij);
        this.Czat.setLayout(new FlowLayout());
        this.Czat.add(userInputField, SwingConstants.CENTER);
        this.Czat.add(scrollPane, SwingConstants.CENTER);
        JButton WyslijButton = new JButton("Wyslij");
        WyslijButton.addActionListener((ActionListener) Wyslij);
        this.Czat.add(WyslijButton);
    }
}
