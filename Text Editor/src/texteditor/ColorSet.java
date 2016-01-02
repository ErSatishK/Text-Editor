/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.awt.Color;
import javax.swing.JColorChooser;

/**
 *
 * @author SATISH
 */
public class ColorSet extends JColorChooser{
private  TextEditor textEditor;
private  Color color;
    /**
     *
     * @param textEditor1
     * @param forgroundColor
     */
    public ColorSet(TextEditor textEditor1,Color forgroundColor) {
        this.textEditor=textEditor1;
        color=showDialog(textEditor, "Select Color", forgroundColor);
    }
    /**
     *
     * @return
     */
    public Color getTextColor(){
        return color;
    }
    
}
