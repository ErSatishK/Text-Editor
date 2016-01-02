/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *Action Listener class for file menu items
 * @author SATISH
 */
public class FileMenuAction implements ActionListener {
    //instance refereneditor class
   private TextEditor textEditor;
    //file discriptor
   private File fp = new File("./");
    //reader & writer
   private BufferedReader in;
   private FileWriter out;
    //string contains property of file as name,size,directory,last modification
   private String fileprop = "";
   private PrintFile pf;
    //constructor
    /**
     *
     * @param textEditor1
     */
    public FileMenuAction(TextEditor textEditor1) {
        //take refenece to current instance
        this.textEditor = textEditor1;
        pf=new PrintFile(textEditor);
    }
    //actionPerformed method invoked by click on menu items
    @Override
    public void actionPerformed(ActionEvent e) {
        //specify which menu item is invoked
        String command = e.getActionCommand();
        switch (command) {
            case "New":
                if (Editor.getTextSize() > 0 && Editor.getSaved() == false) {
                    new ConfirmDialog(textEditor, "Save file", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION);
                    if (ConfirmDialog.getResponse() == JOptionPane.CANCEL_OPTION) {
                    }
                    if (ConfirmDialog.getResponse() == JOptionPane.NO_OPTION) {
                        newFile();
                    }
                    if (ConfirmDialog.getResponse() == JOptionPane.YES_OPTION) {
                        saveFile();
                        newFile();
                    }
                } else {
                    newFile();
                }
                break;
            case "Close":
                if (Editor.getSaved() != true) {
                    new ConfirmDialog(textEditor, "Save file", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION);
                    if (ConfirmDialog.getResponse() == JOptionPane.CANCEL_OPTION) {
                    }
                    if (ConfirmDialog.getResponse() == JOptionPane.NO_OPTION) {
                        System.exit(0);
                    }
                    if (ConfirmDialog.getResponse() == JOptionPane.YES_OPTION) {
                        saveFile();
                        System.exit(0);
                    }
                } else {
                    System.exit(0);
                }
                break;
            case "Save":
                saveFile();
                break;
            case "Open":
                if (fp.isFile() && (Editor.getSaved() == false)) {
                    new ConfirmDialog(textEditor, "Save file", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION);
                    if (ConfirmDialog.getResponse() == JOptionPane.CANCEL_OPTION) {
                    }
                    if (ConfirmDialog.getResponse() == JOptionPane.NO_OPTION) {
                        try {
                            openFile();
                        } catch (FileNotFoundException ex) {
                            new ErrDialog(ex, JOptionPane.ERROR_MESSAGE, JOptionPane.OK_OPTION);
                        }
                    }
                    if (ConfirmDialog.getResponse() == JOptionPane.YES_OPTION) {
                        saveFile();
                        try {
                            openFile();
                        } catch (FileNotFoundException ex) {
                            new ErrDialog(ex, JOptionPane.ERROR_MESSAGE, JOptionPane.OK_OPTION);
                        }
                    }
                } else {
                    try {
                        openFile();
                    } catch (FileNotFoundException ex) {
                       new ErrDialog(ex, JOptionPane.ERROR_MESSAGE, JOptionPane.OK_OPTION);
                    }
                }
                break;
            case "Properties":
                if (fp.isFile()) {
                    fileprop = "File Name : " + fp.getName() + "\n";
                    fileprop += "File Path  : " + fp.getPath() + "\n";
                    fileprop += "File Size  : " + fp.length() + "Bytes\n";
                    fileprop += "Modified  : " + (new Date(fp.lastModified())).toString() + "\n";
                    JOptionPane.showMessageDialog(textEditor, fileprop, "File Properties", JOptionPane.PLAIN_MESSAGE);
                }
                break;
                case "Save As" :
                    File tmp=fp;
                    fp=new File("./");
                    saveFile();
                    if(SaveFile.getSaveFileStatus()==JFileChooser.CANCEL_OPTION){
                        fp=tmp;
                    }
                    break;
                    case "Page Setup":
                        if(fp.isFile()){
                            pf.pageDialog();
                        }
                        break;
                        case "Print":
                            if(fp.isFile()){
                                
                                pf.printFile();
                            }
                            break;

        }
    }
    //method to save an ungetSaved() file
    private void saveFile() {
        if (fp.isFile()) {
            try {
                out = new FileWriter(fp);
                textEditor.getTextArea().write(out);
                out.close();

                Editor.setSaved(true);
            } catch (IOException ex) {
               new ErrDialog(ex, JOptionPane.ERROR_MESSAGE, JOptionPane.OK_OPTION);
            }
        } else {
            SaveFile savef = new SaveFile(textEditor, fp);
            if (SaveFile.getSaveFileStatus() == JFileChooser.APPROVE_OPTION) {
                try {
                    fp = savef.getSelectedFile();
                    fp=new File(fp.getPath()+".txt");
                    out = new FileWriter(fp);
                    textEditor.setTitle("Text Editor - " + fp.getName());
                    textEditor.getTextArea().write(out);
                    out.close();
                    Editor.setSaved(true);
                } catch (IOException ex) {
                    new ErrDialog(ex, JOptionPane.ERROR_MESSAGE, JOptionPane.OK_OPTION);
                }
            }
        }
    }
    //method to open a file
    private void openFile() throws FileNotFoundException {
        OpenFile openf = new OpenFile(textEditor, fp);
        if (OpenFile.getOpenFileStatus() == JFileChooser.APPROVE_OPTION) {
            try {
                fp = openf.getSelectedFile();
                textEditor.setTitle("Text Editor - " + fp.getName());
                in = new BufferedReader(new FileReader(fp));
                while (in.ready()) {
                    textEditor.getTextArea().read(in, fp);
                }
                textEditor.getTextArea().setCaretPosition(textEditor.getTextArea().getText().length());
                in.close();
            } catch (IOException ex) {
                new ErrDialog(ex, JOptionPane.ERROR_MESSAGE, JOptionPane.OK_OPTION);
            }
        }
    }
    //method to create new file
    private void newFile() {
        textEditor.getTextArea().setText(null);
        textEditor.setTitle("Text Editor");
        fp = new File("./");
    }
}
