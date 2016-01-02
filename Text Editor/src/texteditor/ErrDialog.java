/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import javax.swing.JOptionPane;

/**
 *
 * @author SATISH
 */
public class ErrDialog extends JOptionPane{

    /**
     *
     * @param message
     * @param messageType
     * @param optionType
     */
    public ErrDialog(Object message, int messageType, int optionType) {
        super(message, messageType, optionType);
        showMessageDialog(this, message, "Error", messageType);
    }
    
}
