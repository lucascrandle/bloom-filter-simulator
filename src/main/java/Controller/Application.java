package Controller;

import Model.Filter;
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
        gui g = new gui();
        Application a = new Application();
        a.setGui(g);
        a.setupListeners();
    }
    public void setGui(gui g){
        this.g = g;
    }

    public void setupListeners(){
        g.setRunButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Random r = new Random();
                f = new Filter(g.getFilterSize(), g.getUseMurmur1(), g.getUseMurmur2(), g.getUseMurmur3(),r.nextLong(),g.getFilterThreshold(), g.getUniqueWordCount());
                f.runSimulation();
            }
        });

        g.setCheckTextListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                if(f.testFilter(Integer.parseInt(g.getCheckText().replace(".","")))){
                    g.setCheckTextStatus("Possible");
                    System.out.println("Possible");
                }else{
                    g.setCheckTextStatus("Not In Set");
                    System.out.println("Not In Set");
                }
            }

            public void removeUpdate(DocumentEvent e) {
                if(f.testFilter(Integer.parseInt(g.getCheckText().replace(".","")))){
                    g.setCheckTextStatus("Possible");
                    System.out.println("Possible");
                }else{
                    g.setCheckTextStatus("Not In Set");
                    System.out.println("Not In Set");
                }
            }

            public void changedUpdate(DocumentEvent e) {
                    if(f.testFilter(Integer.parseInt(g.getCheckText().replace(".","")))){
                        g.setCheckTextStatus("Possible");
                        System.out.println("Possible");
                    }else{
                        g.setCheckTextStatus("Not In Set");
                        System.out.println("Not In Set");
                    }
                }
            });

    }

    public void runListener(){

    }


}
