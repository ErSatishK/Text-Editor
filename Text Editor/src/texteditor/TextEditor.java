/**
 * @author SATISH
 */
package texteditor;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 *
 * @author SATISH
 */
/*
 * TextEditor is simple text editor & viewer this is main class which contains
 * look and feel of main window it contains textarea,menu bar
 */
public class TextEditor extends JFrame {
	// declaring components
	// menubar panel which holds menu

	private JMenuBar menu;
	// menu
	private JMenu file, edit, format, view, language, about;
	// file menu item which will be shown in menu
	private JMenuItem newFile, openFile, saveFile, saveAsFile, propFile, pageSetupFile, printFile, closeFile;
	// edit menu item
	private JMenuItem undoEdit, redoEdit, cutEdit, copyEdit, pasteEdit, selectAllEdit, deselectAllEdit, clearEdit,
			find$replaceEdit, aboutEditor;
	// format menu item
	private JMenuItem fontFormat, colorFormat;
	// view menu item
	private JCheckBoxMenuItem word$wrapFormat, statusView;
	// language menu item
	private JRadioButtonMenuItem en, eu;
	// buttom group
	private ButtonGroup languageGroup;
	// seperator to separate menu items
	private JSeparator separator$1 = new JPopupMenu.Separator();
	private JSeparator separator$2 = new JPopupMenu.Separator();
	private JSeparator separator$3 = new JPopupMenu.Separator();
	private JSeparator separator$4 = new JPopupMenu.Separator();
	private JSeparator separator$5 = new JPopupMenu.Separator();
	// text area which holds text
	private JTextArea textArea;
	// scroll pane which have scroll bar and hold text area
	private JScrollPane scrollPane;
	// panel to hold pane
	private JPanel mainJPanel, statusJPanel;
	// status label
	private JLabel statusJLabel, countJLabel;
	// image file used to show on titles
	/**
	 *
	 */
	public final Image textEditorTitleImage = getToolkit().getImage(getClass().getResource("Icons/TextEditor.png"));
	/**
	 *
	 */
	public final Image findReplaceTitleImage = getToolkit().getImage(getClass().getResource("Icons/Search.png"));
	/**
	 *
	 */
	public final Image notFoundTitleImage = getToolkit().getImage(getClass().getResource("Icons/Stop.png"));
	/**
	 *
	 */
	public final Image fileChoserImage = getToolkit().getImage(getClass().getResource("Icons/File.png"));
	/**
	 *
	 */
	public final Image aboutTitleImage = getToolkit().getImage(getClass().getResource("Icons/Help.png"));
	/**
	 *
	 */
	public final Image propertiesImage = getToolkit().getImage(getClass().getResource("Icons/Properties.png"));
	/**
	 *
	 */
	public final Image confirmTitleImage = getToolkit().getImage(getClass().getResource("Icons/Information.png"));
	// default height and width of window
	private int height = 600, width = 500;

	// constructor
	/**
	 *
	 */
	public TextEditor() {
		super("Text Editor");
		// calculate height and width using display screensize
		height = (int) getToolkit().getScreenSize().getHeight() / 2;
		width = (int) getToolkit().getScreenSize().getWidth() / 2;
		// set window properties
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(10, 10));
		setBounds(200, 200, width, height);
		setIconImage(textEditorTitleImage);
		// initialize components
		mainJPanel = new JPanel(new BorderLayout(20, 20));
		statusJPanel = new JPanel(new BorderLayout(20, 20));
		textArea = new JTextArea(null, null, 20, 20);
		scrollPane = new JScrollPane(textArea);
		// initialize components
		statusJLabel = new JLabel("Ready", JLabel.LEFT);
		countJLabel = new JLabel("Line 0,Word 0", JLabel.RIGHT);
		// initialize components
		menu = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");
		format = new JMenu("Format");
		view = new JMenu("View");
		language = new JMenu("Language");
		about = new JMenu("About");
		// initialize components
		newFile = new JMenuItem("New");
		openFile = new JMenuItem("Open");
		saveFile = new JMenuItem("Save");
		saveAsFile = new JMenuItem("Save As");
		propFile = new JMenuItem("Properties");
		pageSetupFile = new JMenuItem("Page Setup");
		printFile = new JMenuItem("Print");
		closeFile = new JMenuItem("Close");
		// initialize component
		cutEdit = new JMenuItem("Cut");
		copyEdit = new JMenuItem("Copy");
		pasteEdit = new JMenuItem("Paste");
		selectAllEdit = new JMenuItem("Select All");
		deselectAllEdit = new JMenuItem("Deselect All");
		clearEdit = new JMenuItem("Clear");
		find$replaceEdit = new JMenuItem("Find & Replace");
		// initialize component
		word$wrapFormat = new JCheckBoxMenuItem("Word Wrap", false);
		fontFormat = new JMenuItem("Font");
		colorFormat = new JMenuItem("Color");
		// initialize component
		statusView = new JCheckBoxMenuItem("Status Bar", true);
		// initialize component
		aboutEditor = new JMenuItem("About...");
		// initialize component
		en = new JRadioButtonMenuItem("en", true);
		eu = new JRadioButtonMenuItem("eu");
		// initialize component
		languageGroup = new ButtonGroup();
		languageGroup.add(en);
		languageGroup.add(eu);
		// assign shortcut keys for menu - ALT + VK_Key
		file.setMnemonic(KeyEvent.VK_F);
		edit.setMnemonic(KeyEvent.VK_E);
		format.setMnemonic(KeyEvent.VK_O);
		view.setMnemonic(KeyEvent.VK_V);
		about.setMnemonic(KeyEvent.VK_A);
		// assign shortcut keys for menu items - CTRL + VK_Key
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		closeFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
		// assign shortcut keys
		cutEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
		copyEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
		pasteEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
		selectAllEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
		find$replaceEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
		// assign shortcut keys
		word$wrapFormat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));

		// add menu item to menu
		file.add(newFile);
		file.add(openFile);
		file.add(saveFile);
		file.add(saveAsFile);
		file.add(separator$4);
		file.add(propFile);
		file.add(pageSetupFile);
		file.add(printFile);
		file.add(separator$1);
		file.add(closeFile);
		// add menu items to menu
		edit.add(cutEdit);
		edit.add(copyEdit);
		edit.add(pasteEdit);
		edit.add(separator$2);
		edit.add(selectAllEdit);
		edit.add(deselectAllEdit);
		edit.add(clearEdit);
		edit.add(separator$3);
		edit.add(find$replaceEdit);
		// add menu items to menu
		format.add(word$wrapFormat);
		format.add(fontFormat);
		format.add(colorFormat);
		// add menu items to menu
		view.add(statusView);
		// add menu items to menu
		about.add(aboutEditor);
		// add menu to menu bar panal
		language.add(en);
		language.add(eu);
		// add menu to menu bar panal
		menu.add(file);
		menu.add(edit);
		menu.add(format);
		menu.add(view);
		menu.add(about);
		menu.add(language);
		// set menu bar for window
		setJMenuBar(menu);
		// add pane to panel
		mainJPanel.add(scrollPane);
		statusJPanel.add(statusJLabel, BorderLayout.WEST);
		statusJPanel.add(countJLabel, BorderLayout.EAST);
		// add panel to window
		getContentPane().add(mainJPanel, BorderLayout.CENTER);
		getContentPane().add(statusJPanel, BorderLayout.SOUTH);
		// actionListener for menu item action
		// listenerr for file menu items
		FileMenuAction fileListener = new FileMenuAction(this);
		newFile.addActionListener(fileListener);
		openFile.addActionListener(fileListener);
		saveFile.addActionListener(fileListener);
		saveAsFile.addActionListener(fileListener);
		propFile.addActionListener(fileListener);
		pageSetupFile.addActionListener(fileListener);
		printFile.addActionListener(fileListener);
		closeFile.addActionListener(fileListener);
		// listener for edit menu items
		EditMenuAction editListener = new EditMenuAction(this);
		cutEdit.addActionListener(editListener);
		copyEdit.addActionListener(editListener);
		pasteEdit.addActionListener(editListener);
		selectAllEdit.addActionListener(editListener);
		deselectAllEdit.addActionListener(editListener);
		clearEdit.addActionListener(editListener);
		find$replaceEdit.addActionListener(editListener);
		// listener for format menu
		FormatMenuAction formatListener = new FormatMenuAction(this);
		word$wrapFormat.addActionListener(formatListener);
		fontFormat.addActionListener(formatListener);
		colorFormat.addActionListener(formatListener);
		// listener for text area
		Editor editor = new Editor(this);
		textArea.addCaretListener(editor);
		textArea.addKeyListener(editor);
		// listener for language
		Message message = new Message(this);
		en.addItemListener(message);
		eu.addItemListener(message);
		// listener for view
		statusView.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					statusJPanel.setVisible(true);
				} else {
					statusJPanel.setVisible(false);
				}
			}
		});
		// listener for about menu item
		aboutEditor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, Message.getText("About"), "About Text Editor",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// initialize main window
				setDefaultLookAndFeelDecorated(false);
				new TextEditor().setVisible(true);
			}
		});
	}

	/**
	 *
	 * @return
	 */
	public JTextArea getTextArea() {
		return textArea;
	}

	/**
	 *
	 * @return
	 */
	public JLabel getCountLabel() {
		return countJLabel;
	}

	/**
	 *
	 * @return
	 */
	public JLabel getStatusLabel() {
		return statusJLabel;
	}

	public JMenuItem getNewFileMenuItem() {
		return newFile;
	}

	public JMenuItem getOpenFileMenuItem() {
		return openFile;
	}

	public JMenuItem getPropFileMenuItem() {
		return propFile;
	}

	public JMenuItem getPrintFileMenuItem() {
		return printFile;
	}

	public JMenuItem getSaveFileMenuItem() {
		return saveFile;
	}

	public JMenuItem getSaveAsFileMenuItem() {
		return saveAsFile;
	}

	public JMenuItem getCloseFileMenuItem() {
		return closeFile;
	}

	public JMenuItem getPageSetupFileMenuItem() {
		return pageSetupFile;
	}
}
