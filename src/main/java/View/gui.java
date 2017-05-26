package View;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import java.awt.*;
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
    private JTextField resetThreshold;
    private JLabel checkTextLabel;
    private JLabel statusText;
    private JCheckBox checkBoxUseMurmur1;
    private JCheckBox checkBoxUseMurmur2;
    private JCheckBox checkBoxUseMurmur3;
    private JButton btnRun;

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
        frame.setBounds(100, 100, 878, 654);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        checkTextInput = new JTextField();
        checkTextInput.setBounds(106, 523, 240, 22);
        panel.add(checkTextInput);
        checkTextInput.setColumns(10);

        checkBoxUseMurmur1 = new JCheckBox("Use Murmur 1");
        checkBoxUseMurmur1.setBounds(151, 26, 113, 25);
        panel.add(checkBoxUseMurmur1);

        checkBoxUseMurmur2 = new JCheckBox("Use Murmur 2");
        checkBoxUseMurmur2.setBounds(151, 56, 113, 25);
        panel.add(checkBoxUseMurmur2);

        checkBoxUseMurmur3 = new JCheckBox("Use Murmur 3");
        checkBoxUseMurmur3.setBounds(151, 86, 113, 25);
        panel.add(checkBoxUseMurmur3);

        JLabel lblCheckWord = new JLabel("Check Word:");
        lblCheckWord.setBounds(8, 526, 91, 16);
        panel.add(lblCheckWord);

        JLabel lblResetPoint = new JLabel("Reset Threshold");
        lblResetPoint.setBounds(8, 134, 128, 16);
        panel.add(lblResetPoint);

        resetThreshold = new JTextField();
        resetThreshold.setBounds(148, 131, 116, 22);
        panel.add(resetThreshold);
        resetThreshold.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Size Of Model.Filter");
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

        btnRun = new JButton("Run");
        btnRun.setBounds(167, 247, 97, 25);
        panel.add(btnRun);

        checkTextLabel = new JLabel("Check Text");
        checkTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        checkTextLabel.setBounds(106, 558, 240, 16);
        panel.add(checkTextLabel);

        JLabel programStatusLabel = new JLabel("Status:");
        programStatusLabel.setBounds(22, 331, 61, 25);
        panel.add(programStatusLabel);

        statusText = new JLabel("");
        statusText.setBounds(106, 335, 56, 16);
        panel.add(statusText);
    }

    public void setCheckTextStatus(String text){
        this.checkTextLabel.setText(text);
    }
    public String getCheckText(){
        return checkTextInput.getText();
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

    public boolean getUseMurmur1(){
        return checkBoxUseMurmur1.isSelected();
    }
    public boolean getUseMurmur2(){
        return checkBoxUseMurmur2.isSelected();
    }
    public boolean getUseMurmur3(){
        return checkBoxUseMurmur3.isSelected();
    }
    public int getFilterThreshold(){
        return Integer.parseInt(resetThreshold.getText());
    }
    public int getFilterSize(){
        return Integer.parseInt(filterSizeField.getText());
    }
    public int getUniqueWordCount(){
        return Integer.parseInt(uniqueWordField.getText());
    }
}
