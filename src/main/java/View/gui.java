package View;

import Model.statisticValues;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * Created by lucas.crandle on 5/25/2017.
 */
public class gui {

    private JFrame frame;
    private JTextField uniqueWordField,filterSizeField,checkTextInput, checkSimulationTryCount,randomSizeField;
    private JLabel statisticFalsePositivePercentage, notInSetCount,statusText,checkTextLabel, hitCountLabel, hitCount, falsePositiveCount, falsePositivePercentage, statisticHitPercentage;
    private JCheckBox checkBoxUseMurmur1, checkBoxUseMurmur2, checkBoxUseMurmur3;
    private JButton btnRunSimulation, btnRun;

    /**
     * Create the application.
     */
    public gui() {
        initialize();
        frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 400, 730);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        checkBoxUseMurmur1 = new JCheckBox("Use Murmur 1");
        checkBoxUseMurmur1.setBounds(151, 26, 113, 25);
        panel.add(checkBoxUseMurmur1);

        checkBoxUseMurmur2 = new JCheckBox("Use Murmur 2");
        checkBoxUseMurmur2.setBounds(151, 56, 113, 25);
        panel.add(checkBoxUseMurmur2);

        checkBoxUseMurmur3 = new JCheckBox("Use Murmur 3");
        checkBoxUseMurmur3.setBounds(151, 86, 113, 25);
        panel.add(checkBoxUseMurmur3);

        JLabel filterSizeLabel = new JLabel("Size Of Model Filter");
        filterSizeLabel.setBounds(8, 163, 120, 22);
        panel.add(filterSizeLabel);

        filterSizeField = new JTextField();
        filterSizeField.setColumns(10);
        filterSizeField.setBounds(148, 163, 116, 22);
        panel.add(filterSizeField);

        JLabel lblUniqueWordsTo = new JLabel("Unique Words to Try");
        lblUniqueWordsTo.setBounds(8, 195, 128, 16);
        panel.add(lblUniqueWordsTo);

        uniqueWordField = new JTextField();
        uniqueWordField.setColumns(10);
        uniqueWordField.setBounds(148, 192, 116, 22);
        panel.add(uniqueWordField);

        JLabel lblNewLabel_4 = new JLabel("Random Size");
        lblNewLabel_4.setBounds(8, 224, 113, 16);
        panel.add(lblNewLabel_4);

        randomSizeField = new JTextField();
        randomSizeField.setBounds(148, 221, 116, 22);
        panel.add(randomSizeField);

        btnRun = new JButton("Setup Filter");
        btnRun.setBounds(148, 255, 116, 25);
        panel.add(btnRun);

        checkTextLabel = new JLabel("");
        checkTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        checkTextLabel.setBounds(106, 575, 240, 16);
        panel.add(checkTextLabel);

        JLabel programStatusLabel = new JLabel("Status:");
        programStatusLabel.setBounds(8, 284, 61, 25);
        panel.add(programStatusLabel);

        statusText = new JLabel("");
        statusText.setBounds(75, 288, 400, 16);
        panel.add(statusText);



        JLabel lblTryCount = new JLabel("Try Count:");
        lblTryCount.setBounds(8, 360, 87, 16);
        panel.add(lblTryCount);

        checkSimulationTryCount = new JTextField();
        checkSimulationTryCount.setBounds(106, 357, 116, 22);
        panel.add(checkSimulationTryCount);
        checkSimulationTryCount.setColumns(10);

        btnRunSimulation = new JButton("Run Simulation");
        btnRunSimulation.setBounds(100, 404, 132, 25);
        panel.add(btnRunSimulation);

        /** Output Fields */

        hitCountLabel = new JLabel("Hit Count");
        hitCountLabel.setBounds(8, 453, 73, 16);
        panel.add(hitCountLabel);

        hitCount = new JLabel("");
        hitCount.setBounds(160, 453, 56, 16);
        panel.add(hitCount);

        JLabel notInSetCountLabel = new JLabel("Not in Set");
        notInSetCountLabel.setBounds(8,482,91,16);
        panel.add(notInSetCountLabel);

        notInSetCount = new JLabel("");
        notInSetCount.setBounds(160, 482, 91, 16);
        panel.add(notInSetCount);

        JLabel falsePositiveCountLabel = new JLabel("False Positives");
        falsePositiveCountLabel.setBounds(8, 512, 91, 16);
        panel.add(falsePositiveCountLabel);

        falsePositiveCount = new JLabel("");
        falsePositiveCount.setBounds(160, 512, 91, 16);
        panel.add(falsePositiveCount);

        JLabel falsePositivePercentageLabel = new JLabel("Percentage Of Hits");
        falsePositivePercentageLabel.setBounds(20, 540, 120, 16);
        panel.add(falsePositivePercentageLabel);

        falsePositivePercentage = new JLabel("");
        falsePositivePercentage.setBounds(160, 540, 56, 16);
        panel.add(falsePositivePercentage);

        JLabel statisticFalsePositiveLabel = new JLabel("Statistical Hit Chance");
        statisticFalsePositiveLabel.setBounds(8, 570, 130, 16);
        panel.add(statisticFalsePositiveLabel);

        statisticHitPercentage = new JLabel("");
        statisticHitPercentage.setBounds(160, 570, 56, 16);
        panel.add(statisticHitPercentage);

        JLabel statisticFalsePositivePercentageLabel = new JLabel("Statistical False Pos");
        statisticFalsePositivePercentageLabel.setBounds(8, 600, 130, 16);
        panel.add(statisticFalsePositivePercentageLabel);

        statisticFalsePositivePercentage = new JLabel("");
        statisticFalsePositivePercentage.setBounds(160, 600, 56, 16);
        panel.add(statisticFalsePositivePercentage);

        checkTextInput = new JTextField();
        checkTextInput.setBounds(106, 630 , 240, 22);
        panel.add(checkTextInput);
        checkTextInput.setColumns(10);

        JLabel lblCheckWord = new JLabel("Check Word:");
        lblCheckWord.setBounds(8, 630, 91, 16);
        panel.add(lblCheckWord);

    }

    public void setCheckSimulationVariables(statisticValues sv){
        this.hitCount.setText(sv.countInSet+"");
        this.falsePositiveCount.setText(sv.falsePositives +"");
        this.falsePositivePercentage.setText(sv.falsePositivePercentage + "%" );
        this.notInSetCount.setText(sv.attemptsNotInSet + "");
    }

    public void setStatisticHitPercentage(double hitPercentage){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        this.statisticHitPercentage.setText(df.format(hitPercentage * 100) + "%");
    }
    
    public void setStatisticFalsePositivePercentage(double falsePercentage){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        this.statisticFalsePositivePercentage.setText(df.format(falsePercentage * 100) + "%");
    }

    public void setcheckSimulationButtonListener(ActionListener a){
        this.btnRunSimulation.addActionListener(a);
    }

    public void setCheckTextStatus(String text){
        this.checkTextLabel.setText(text);
    }

    public void setProgramStatus(String text){
        this.statusText.setText(text);
    }

    public void setCheckTextListener(DocumentListener listener){
        this.checkTextInput.getDocument().addDocumentListener(listener);
    }

    public void setRunButtonListener(ActionListener listener){
        this.btnRun.addActionListener(listener);
    }
    public int getRandomSize(){
        return Integer.parseInt(randomSizeField.getText());
    }
    public boolean getUseMurmur1(){
        return checkBoxUseMurmur1.isSelected();
    }
    public boolean getUseMurmur2(){
        return checkBoxUseMurmur2.isSelected();
    }
    public boolean getUseMurmur3(){
        return checkBoxUseMurmur3.isSelected();
    }
    public int getFilterSize(){
        return Integer.parseInt(filterSizeField.getText());
    }
    public int getUniqueWordCount(){
        return Integer.parseInt(uniqueWordField.getText());
    }
    public String getCheckText(){
        return checkTextInput.getText();
    }
    public String getCheckTryCount(){
        return checkSimulationTryCount.getText();
    }

}
