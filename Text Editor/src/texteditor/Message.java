/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author SATISH
 */
public class Message implements ChangeListener, ItemListener{
    private TextEditor textEditor;
    private static final String BUNDLE_EN="about_en";
    private static final String BUNDLE_EU="about_eu";
    private static ResourceBundle MSGBUNDLE= ResourceBundle.getBundle(BUNDLE_EN);

    /**
     *
     * @param te
     */
    public Message(TextEditor te) {
        textEditor=te;
    }
    /**
     *
     * @param res
     * @return
     */
    public static String getText(String res){
        try{
          return MSGBUNDLE.getString(res);  
        }catch(MissingResourceException e){
            return "error";
        }
        
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource()=="en"){
           MSGBUNDLE= ResourceBundle.getBundle(BUNDLE_EN); 
        }
        if(e.getSource()=="eu"){
            MSGBUNDLE= ResourceBundle.getBundle(BUNDLE_EU);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JRadioButtonMenuItem lan=(JRadioButtonMenuItem)e.getItemSelectable();
        if(lan.getText()=="en"){
            MSGBUNDLE= ResourceBundle.getBundle(BUNDLE_EN);
            textEditor.revalidate();
        }
        if(lan.getText()=="eu"){
            MSGBUNDLE= ResourceBundle.getBundle(BUNDLE_EU);
        }
    }
}
