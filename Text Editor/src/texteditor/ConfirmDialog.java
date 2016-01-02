/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *dialog to ask confirmation of user action
 * @author SATISH
 */
public class ConfirmDialog extends JOptionPane {

    private static int response;

    /**
     *
     * @param owner
     * @param message
     * @param messageType
     * @param optionType
     */
    public ConfirmDialog(Component owner, Object message, int messageType, int optionType) {
        super(message, messageType, optionType);
        response = showConfirmDialog(owner, message, "Message", optionType, messageType);
    }
    /**
     *
     * @return
     */
    public static int getResponse(){
        return response;
    }
}
