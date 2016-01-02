/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author SATISH
 */
public class FormatMenuAction implements ActionListener{
   private TextEditor textEditor;
    

    /**
     *
     * @param textEditor
     */
    public FormatMenuAction(TextEditor textEditor) {
        this.textEditor = textEditor;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        String command=e.getActionCommand();
        switch(command){
            case "Word Wrap":
                if(textEditor.getTextArea().getLineWrap()){
                 textEditor.getTextArea().setLineWrap(false);   
                }else{
                textEditor.getTextArea().setLineWrap(true);
                }
                break;
                case "Font":
                    fontChooser();
                    break;
                    case "Color":
                        colorChooser();
                        break;
        }
    }
    private void colorChooser(){
        ColorSet colorSet=new ColorSet(textEditor,textEditor.getTextArea().getForeground());
        if(colorSet.getTextColor()!=null){
            textEditor.getTextArea().setForeground(colorSet.getTextColor());
        }
    }
    private void fontChooser(){
        FontSet fontSet=new FontSet(textEditor);
        fontSet.setVisible(true);
    }
    
}
