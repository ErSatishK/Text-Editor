/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *open file dialog
 * @author SATISH
 */
public class OpenFile extends JFileChooser {
    //variable to take dialog action status
    private static int openfileStatus;
    //filter for text file
   private FileFilter ff = new FileNameExtensionFilter("Text File", "txt", "TXT");
    //cunstructor
    /**
     *
     * @param parent
     * @param currentDirectory
     */
    public OpenFile(Component parent, File currentDirectory) {
        super(currentDirectory);
        //set file dialog property
        setFileSelectionMode(JFileChooser.FILES_ONLY);
        setFileFilter(ff);
        setAcceptAllFileFilterUsed(false);
        setDialogTitle("Open File");
        //show open dialog
        openfileStatus =showOpenDialog(parent);
    }
    /**
     *
     * @return
     */
    public static int getOpenFileStatus(){
        return openfileStatus;
    }
}
