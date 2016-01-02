/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.StringTokenizer;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 * listener for text area
 *
 * @author SATISH
 */
public class Editor implements CaretListener, KeyListener {
    //instance of text area

    private TextEditor textEditor;
    private StringTokenizer stk;
    //declaration of variables
    private static String countWord, countLine;
    private static boolean saved = false;
    private static int textSize = 0;
    private int startPosition = -1;
    private int endPosition = -1;
    private static int selected = -1;
    //cunstructor

    /**
     *
     * @param textEditor1
     */
    public Editor(TextEditor textEditor1) {
        this.textEditor = textEditor1;
    }
    //various methods invoked by text area action

    @Override
    public void caretUpdate(CaretEvent e) {
        startPosition = e.getDot();
        endPosition = e.getMark();
        selected = startPosition - endPosition;
        countLine = " Line " + textEditor.getTextArea().getLineCount();
        stk = new StringTokenizer(textEditor.getTextArea().getText(), " \n");
        countWord = " Word " + stk.countTokens();
        textEditor.getCountLabel().setText(countLine + countWord);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        saved = false;
        textSize = textEditor.getTextArea().getText().length();
        countLine = " Line " + textEditor.getTextArea().getLineCount();
        stk = new StringTokenizer(textEditor.getTextArea().getText(), " \n");
        countWord = " Word " + stk.countTokens();
        textEditor.getCountLabel().setText(countLine + countWord);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        countLine = " Line " + textEditor.getTextArea().getLineCount();
        stk = new StringTokenizer(textEditor.getTextArea().getText(), " \n");
        countWord = " Word " + stk.countTokens();
        textEditor.getCountLabel().setText(countLine + countWord);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        countLine = " Line " + textEditor.getTextArea().getLineCount();
        stk = new StringTokenizer(textEditor.getTextArea().getText(), " \n");
        countWord = " Word " + stk.countTokens();
        textEditor.getCountLabel().setText(countLine + countWord);

    }

    /**
     *
     * @return
     */
    public static boolean getSaved() {
        return saved;
    }

    /**
     *
     * @param status
     * @return
     */
    public static boolean setSaved(boolean status) {
        saved = status;
        return saved;
    }

    /**
     *
     * @return
     */
    public static int getTextSize() {
        return textSize;
    }
}
