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
 *save file dialog
 * @author SATISH
 */
public class SaveFile extends JFileChooser {
    //variable to take dialog action status
    private static int savefileStatus;
    //file filter for text file
    private FileFilter ff = new FileNameExtensionFilter("Text File", "txt", "TXT");
    //cunstructor
    /**
     *
     * @param parent
     * @param currentDirectory
     */
    public SaveFile(Component parent, File currentDirectory) {
        super(currentDirectory);
        //set save file property
        setFileFilter(ff);
        setDialogTitle("Save File");
        //show save file dialog
        savefileStatus = showSaveDialog(parent);
    }
    /**
     *
     * @return
     */
    public static int getSaveFileStatus(){
        return savefileStatus;
    }
}
