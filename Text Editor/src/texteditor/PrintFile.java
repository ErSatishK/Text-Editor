/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;

/**
 *
 * @author SATISH
 */
public class PrintFile{
   private TextEditor textEditor;
    private PrinterJob printerJob;
   private PrintService printService;
    private PageFormat pageFormat;
    /**
     *
     * @param textEditor
     */
    public PrintFile(TextEditor textEditor) {
        this.textEditor = textEditor;
        printerJob=PrinterJob.getPrinterJob();
        printService=printerJob.getPrintService();
        pageFormat=printerJob.defaultPage();
    }
    /**
     *
     */
    public void printFile(){
        if(printService!=null){
            printerJob.setPrintable(textEditor.getTextArea().getPrintable(null, null),pageFormat);
            if(printerJob.printDialog()){
                try {
                    printerJob.print();
                } catch (PrinterException ex) {
                    Logger.getLogger(PrintFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    /**
     *
     */
    public void pageDialog(){
        pageFormat=printerJob.pageDialog(pageFormat);
    }
    
}
