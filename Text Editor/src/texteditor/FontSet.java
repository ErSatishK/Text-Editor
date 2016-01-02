/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author SATISH
 */
public class FontSet extends JDialog implements ActionListener{
private TextEditor textEditor;
private GraphicsEnvironment ge= GraphicsEnvironment.getLocalGraphicsEnvironment();
  private  JPanel labelPanel,choicePanel,buttonPanel;
  private  JLabel font,style,fontSize;
  private  JButton set,cancel;
  private  Integer[] sizes={10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,42,44,46,48,50,52,54,56,58,60,62,64,66,68,70};
   private String styleString[]={"PLAIN","ITALIC","BOLD"};
  private  JList<String> fontList;
   private JList<Integer> fontSizeList;
   private JList<String> fontStyleList;
   private JScrollPane scrollBar1,scrollBar2,scrollBar3;
    /**
     *
     * @param textEditor
     */
    public FontSet(TextEditor textEditor) {
        this.textEditor = textEditor;
        setLayout(new FlowLayout(FlowLayout.CENTER,20,5));
        setBounds(150, 150, 350, 325);
        setTitle("Select Font");
        setModal(true);
        labelPanel=new JPanel(new FlowLayout(FlowLayout.CENTER, 120, 10));
        choicePanel=new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));
        buttonPanel=new JPanel(new FlowLayout());
        font=new JLabel("Font",JLabel.RIGHT);
        style=new JLabel("Style",JLabel.RIGHT);
        fontSize=new JLabel("Size",JLabel.RIGHT);
        fontList=new JList<>(ge.getAvailableFontFamilyNames());
        fontList.setSelectedValue(textEditor.getTextArea().getFont().getFamily(), true);
        fontSizeList=new JList<>(sizes);
        fontSizeList.setSelectedValue(textEditor.getTextArea().getFont().getSize(), true);
        fontStyleList=new JList<>(styleString);
        scrollBar1=new JScrollPane(fontList);
        scrollBar2=new JScrollPane(fontStyleList);
        scrollBar3=new JScrollPane(fontSizeList);
        set=new JButton("Set");
        cancel=new JButton("Cancel");
        labelPanel.add(font);
        labelPanel.add(style);
        labelPanel.add(fontSize);
        choicePanel.add(scrollBar1);
        choicePanel.add(scrollBar2);
        choicePanel.add(scrollBar3);
        buttonPanel.add(set);
        buttonPanel.add(cancel);
        add(labelPanel);
        add(choicePanel);
        add(buttonPanel);
        set.addActionListener(this);
        cancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int st = Font.PLAIN,sz = textEditor.getTextArea().getFont().getSize();
        if("Set".equals(e.getActionCommand())){
            Font selectedFont=Font.decode(fontList.getSelectedValue());
            if("PLAIN".equals(fontStyleList.getSelectedValue())){
                st=Font.PLAIN;
            }
            if("ITALIC".equals(fontStyleList.getSelectedValue())){
                st=Font.ITALIC;
            }
            if("BOLD".equals(fontStyleList.getSelectedValue())){
                st=Font.BOLD;
            }
            if(fontSizeList.getSelectedValue()!=null){
                sz=fontSizeList.getSelectedValue();
            }
            selectedFont=selectedFont.deriveFont(st, sz);
            if(selectedFont!=null){
                textEditor.getTextArea().setFont(selectedFont);
                setVisible(false);
            }
            else{
                setVisible(false);
            }
        }
        if("Cancel".equals(e.getActionCommand())){
            setVisible(false);
        }
        
    }
 
}
