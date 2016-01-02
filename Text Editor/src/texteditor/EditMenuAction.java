/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *ActionListener class for edit menu items
 * @author SATISH
 */
public class EditMenuAction implements ActionListener {
    //instance of texteditor class
   private TextEditor textEditor;
    //string to hold selected text
   private String selectedText;
    //constructor
    /**
     *
     * @param textEditor
     */
    public EditMenuAction(TextEditor textEditor) {
        //take reference to current instance
        this.textEditor = textEditor;
    }
    //actionPerformed method invoked by click on menu items
    @Override
    public void actionPerformed(ActionEvent e) {
        //get menu item name which invoked method
        String command = e.getActionCommand();
        switch (command) {
            case "Cut":
                cutText();
                break;
            case "Copy":
                copyText();
                break;
            case "Paste":
                pasteText();
                break;
            case "Select All":
                textEditor.getTextArea().selectAll();
                break;
            case "Deselect All":
                textEditor.getTextArea().select(0, 0);
                break;
            case "Clear":
                textEditor.getTextArea().setText(null);
                break;
            case "Find & Replace":
                new FindReplaceDialog(textEditor);
                break;
        }
    }
    //method for cut
    private void cutText() {
        if ((textEditor.getTextArea().getSelectedText()) != null) {
            selectedText = textEditor.getTextArea().getSelectedText();
            textEditor.getTextArea().replaceSelection("");
            Editor.setSaved(false);
        }
    }
    //method for copy
    private void copyText() {
        if ((textEditor.getTextArea().getSelectedText()) != null) {
            selectedText = textEditor.getTextArea().getSelectedText();
        }
    }
    //method for paste
    private void pasteText() {
        if (selectedText != null) {
            textEditor.getTextArea().insert(selectedText, textEditor.getTextArea().getCaretPosition());
            Editor.setSaved(false);
        }
    }
}
