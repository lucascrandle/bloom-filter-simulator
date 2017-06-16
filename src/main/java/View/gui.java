package View;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lucas.crandle on 5/25/2017.
 */
public class gui {

    private JFrame frame;
    private JTextField checkTextInput;
    private JTextField textField_1;
    private JTextField filterSizeField;
    private JTextField uniqueWordField;
    private JTextField checkSimulationTryCount,randomSizeField;
    private JLabel checkTextLabel;
    private JLabel statusText;
    private JLabel hitCountLabel, hitCount, falsePositiveCount, falsePositivePercentage, statisticFalsePositive;
    private JCheckBox checkBoxUseMurmur1;
    private JCheckBox checkBoxUseMurmur2;
    private JCheckBox checkBoxUseMurmur3;
    private JButton btnRun;
    private JButton btnRunSimulation;

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
        frame.setBounds(100, 100, 400, 654);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        checkTextInput = new JTextField();
        checkTextInput.setBounds(106, 570 , 240, 22);
        panel.add(checkTextInput);
        checkTextInput.setColumns(10);

        JLabel lblCheckWord = new JLabel("Check Word:");
        lblCheckWord.setBounds(8, 570, 91, 16);
        panel.add(lblCheckWord);

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

        JLabel lblNewLabel_1 = new JLabel("Size Of Model Filter");
        lblNewLabel_1.setBounds(8, 163, 120, 22);
        panel.add(lblNewLabel_1);

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

        hitCountLabel = new JLabel("Hit Count");
        hitCountLabel.setBounds(8, 453, 73, 16);
        panel.add(hitCountLabel);

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

        hitCount = new JLabel("");
        hitCount.setBounds(160, 453, 56, 16);
        panel.add(hitCount);

        falsePositiveCount = new JLabel("");
        falsePositiveCount.setBounds(160, 482, 91, 16);
        panel.add(falsePositiveCount);

        falsePositivePercentage = new JLabel("");
        falsePositivePercentage.setBounds(160, 512, 56, 16);
        panel.add(falsePositivePercentage);

        JLabel falsePositivePercentageLabel = new JLabel("Percentage Of Hits");
        falsePositivePercentageLabel.setBounds(20, 512, 120, 16);
        panel.add(falsePositivePercentageLabel);

        statisticFalsePositive = new JLabel("");
        statisticFalsePositive.setBounds(160, 540, 56, 16);
        panel.add(statisticFalsePositive);

        JLabel statisticFalsePositiveLabel = new JLabel("Statistical Hit Chance");
        statisticFalsePositiveLabel.setBounds(8, 540, 130, 16);
        panel.add(statisticFalsePositiveLabel);

        JLabel falsePositiveCountLabel = new JLabel("False Positives");
        falsePositiveCountLabel.setBounds(8, 482, 91, 16);
        panel.add(falsePositiveCountLabel);

        JLabel lblNewLabel_4 = new JLabel("Random Size");
        lblNewLabel_4.setBounds(8, 224, 113, 16);
        panel.add(lblNewLabel_4);

        randomSizeField = new JTextField();
        randomSizeField.setBounds(148, 221, 116, 22);
        panel.add(randomSizeField);
        randomSizeField.setColumns(10);

    }

    public void setCheckSimulationVariables(int[] count){
        this.hitCount.setText(count[0]+"");
        this.falsePositiveCount.setText(count[1]+"");
        this.falsePositivePercentage.setText(count[2]+"%");
    }

    public void setStatisticFalsePositive(int falsePositive){
        this.statisticFalsePositive.setText(falsePositive + "%");
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
