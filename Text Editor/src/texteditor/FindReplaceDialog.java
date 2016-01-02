/**
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package texteditor;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * dialog provide interface for find and replace menu item and handle its
 * functioning
 *
 * @author SATISH
 */
public final class FindReplaceDialog extends JDialog implements ActionListener, KeyListener {
    //components of dialog

    private TextEditor textEditor;
    private JPanel findPanel, replacePanel;
    private JButton findButton, findNextButton, replaceButton, replaceAllButton;
    private JLabel findLabel, replaceLabel;
    private JTextField findTextField, replaceTextField;
    //variable declaration
    private static boolean found = false;
    private static boolean running=false;
    private String toFind;
    private String toReplace;
    private int startIndex = 0, endIndex = 0;
//constructor

    /**
     *
     * @param textEditor
     */
    public FindReplaceDialog(TextEditor textEditor) {
        //get reference of texteditor
        this.textEditor = textEditor;
        //set dialog properties
        setLayout(new GridLayout(2, 1, 10, 10));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(350, 300, 275, 275);
        setAlwaysOnTop(true);
        setResizable(false);
        setTitle("Find & Replace");
        setIconImage(textEditor.findReplaceTitleImage);
        //initialize component
        findPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        findPanel.getInsets(new Insets(10, 10, 10, 10));
        findLabel = new JLabel("Find");
        findButton = new JButton("Find");
        findNextButton = new JButton("Find Next");
        findTextField = new JTextField(1);
        //initialize components
        replacePanel = new JPanel(new GridLayout(3, 2, 10, 10));
        replacePanel.getInsets(new Insets(10, 10, 10, 10));
        replaceLabel = new JLabel("Replace");
        replaceButton = new JButton("Replace");
        replaceAllButton = new JButton("Replace All");
        replaceTextField = new JTextField(1);
        //add component to panel
        findPanel.add(findLabel);
        findPanel.add(new JLabel());
        findPanel.add(findTextField);
        findPanel.add(findButton);
        findPanel.add(new JLabel());
        findPanel.add(findNextButton);
        //add components to panel
        replacePanel.add(replaceLabel);
        replacePanel.add(new JLabel());
        replacePanel.add(replaceTextField);
        replacePanel.add(replaceButton);
        replacePanel.add(new JLabel());
        replacePanel.add(replaceAllButton);
        //set buttons inactive
        findButton.setEnabled(false);
        findNextButton.setEnabled(false);
        replaceButton.setEnabled(false);
        replaceAllButton.setEnabled(false);
        //add panel to dialog
        add(findPanel);
        add(replacePanel);
        //add listener
        findTextField.addKeyListener(this);
        replaceTextField.addKeyListener(this);
        findButton.addActionListener(this);
        findNextButton.addActionListener(this);
        replaceButton.addActionListener(this);
        replaceAllButton.addActionListener(this);
        //show dialog
        isRunning();
    }
    //method invoked by components of dialog

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Find":
                findText();
                break;
            case "Find Next":
                findNextText();
                break;
            case "Replace":
                replaceText();
                break;
            case "Replace All":
                replaceAllText();
                break;
        }
    }
    //method for find text

    private void findText() {
        toFind = findTextField.getText();
        if ((startIndex = textEditor.getTextArea().getText().indexOf(toFind)) != -1) {
            found = true;
            findNextButton.setEnabled(true);
            replaceButton.setEnabled(true);
            replaceAllButton.setEnabled(true);
            endIndex = startIndex + toFind.length();
            textEditor.getTextArea().select(startIndex, endIndex);
        } else {
            found = false;
            findNextButton.setEnabled(false);
            replaceButton.setEnabled(false);
            replaceAllButton.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Text Not Found", "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    //method for find next text

    private void findNextText() {
        if ((startIndex = textEditor.getTextArea().getText().indexOf(toFind, endIndex)) != -1) {
            found = true;
            findNextButton.setEnabled(true);
            replaceAllButton.setEnabled(true);
            replaceButton.setEnabled(true);
            endIndex = startIndex + toFind.length();
            textEditor.getTextArea().select(startIndex, endIndex);
        } else {
            found = false;
            findNextButton.setEnabled(false);
            replaceAllButton.setEnabled(false);
            replaceButton.setEnabled(false);
        }
    }
    //method for replace

    private void replaceText() {
        toReplace = replaceTextField.getText();
        textEditor.getTextArea().replaceSelection(toReplace);
        replaceButton.setEnabled(false);
    }
    //method for replace all

    private void replaceAllText() {
        toReplace = replaceTextField.getText();
        while (found) {
            replaceText();
            findNextText();
        }
        JOptionPane.showMessageDialog(this, "Replace Complete", "Message", JOptionPane.INFORMATION_MESSAGE);

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (!findTextField.getText().isEmpty()) {
            findButton.setEnabled(true);

        } else {
            findButton.setEnabled(false);
            findNextButton.setEnabled(false);
        }
    }
    //method invoked by dialog components

    @Override
    public void keyPressed(KeyEvent e) {
        if (!findTextField.getText().isEmpty()) {
            findButton.setEnabled(true);

        } else {
            findButton.setEnabled(false);
            findNextButton.setEnabled(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!findTextField.getText().isEmpty()) {

            findButton.setEnabled(true);
        } else {
            findButton.setEnabled(false);
            findNextButton.setEnabled(false);
        }
    }
    private void isRunning(){
        if(running==true){
            setVisible(false);
            dispose();
        }else{
            running=true;
            setVisible(true);
        }
    }
}
