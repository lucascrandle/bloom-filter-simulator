package Controller;

import Model.Filter;
import Model.statisticValues;
import View.gui;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by lucas.crandle on 5/22/2017.
 */
public class Application {
    Filter f;
    gui g;

    public static void main (String[] args){
        if(args.length ==0) {
            gui g = new gui();
            Application a = new Application(g);
        }else{
            /** Command Line Support */
            if(args.length == 7){
                try {
                    int filterSize = Integer.parseInt(args[0]);
                    int randomSize = Integer.parseInt(args[1]);
                    boolean useMurmur1 =  new Boolean(args[2]);
                    boolean useMurmur2 =  new Boolean(args[3]);
                    boolean useMurmur3 =  new Boolean(args[4]);
                    int uniqueInserts = Integer.parseInt(args[5]);
                    int checkCount = Integer.parseInt(args[6]);
                    Filter f = new Filter(filterSize, randomSize, useMurmur1, useMurmur2, useMurmur3, uniqueInserts);
                    f.runSimulation();
                    statisticValues results = f.runCheckSimulation(checkCount);
                    System.out.println("Check Count: " + results.countInSet+ " false Positive: " + results.falsePositives);

                }catch (Exception e){
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }

            }else{
                System.out.println("Incorrect Numbers of Args");
            }
        }
    }

    public Application(gui g){
        this.setGui(g);
        this.setupListeners();
    }

    /**
     * Set the gui for the application
     * @param g The gui to set.
     */
    public void setGui(gui g){
        this.g = g;
    }

    /**
     * Setup the button listeners
     */
    public void setupListeners(){
        g.setRunButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    g.setProgramStatus("Running...");

                    f = new Filter(g.getFilterSize(), g.getRandomSize(), g.getUseMurmur1(), g.getUseMurmur2(), g.getUseMurmur3(), g.getUniqueWordCount());
                    f.runSimulation();
                    g.setStatisticHitPercentage(f.getHitPercentage());
                    g.setStatisticFalsePositivePercentage(f.getFalsePositivePercentage());
                    g.setProgramStatus("Complete");
                }catch (Exception ex){
                    g.setProgramStatus(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        g.setCheckTextListener(new DocumentListener() {
            public void check(){
                    g.setCheckTextStatus("Checking...");
                if(f != null) {
                    g.setCheckTextStatus(f.checkInput(g.getCheckText()));
                }else{
                    g.setCheckTextStatus("Need to Create Filter First");
                }
            }
            public void insertUpdate(DocumentEvent e) {
                check();
            }
            public void removeUpdate(DocumentEvent e) {
                check();
            }

            public void changedUpdate(DocumentEvent e) {
                check();
            }
            });

        g.setcheckSimulationButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    g.setCheckSimulationVariables(f.runCheckSimulation(Integer.parseInt(g.getCheckTryCount())));

                }catch(Exception exc){
                    g.setCheckTextStatus("Invalid try Count");
                }
            }
        });

    }


}
