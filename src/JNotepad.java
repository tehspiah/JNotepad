import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.*;

import java.awt.BorderLayout;

import javax.swing.JPopupMenu;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JNotepad implements ActionListener {

	private JFrame frame;
	JMenuBar menuBar;
	JMenu mnFile;
	JMenuItem mntmNew;
	JMenuItem mntmOpen;
	JMenuItem mntmSave;
	JMenuItem mntmSaveAs;
	JMenuItem mntmPageSetup;
	JMenuItem mntmPrint;
	JMenuItem mntmExit;
	JMenu mnNewMenu;
	JMenu mnFormat;
	JMenu mnView;
	JMenu mnHelp;
	JMenuItem mntmViewHelp;
	JMenuItem mntmAboutJnotepad;
	JMenuItem mntmStatusBar;
	JMenuItem mntmFont;
	JMenuItem mntmUndo;
	JMenuItem mntmCut;
	JMenuItem mntmCopy;
	JMenuItem mntmPaste;
	JMenuItem mntmDelete;
	JMenuItem mntmFind;
	JMenuItem mntmFindNext;
	JMenuItem mntmReplace;
	JMenuItem mntmGoTo;
	JMenuItem mntmSelectAll;
	JMenuItem mntmTimedate;
	JSeparator separator;
	JSeparator separator_1;
	JSeparator separator_2;
	JSeparator separator_3;
	JSeparator separator_4;
	JSeparator separator_5;
	public static JTextArea textArea;
	JPopupMenu popupMenu;
	JFileChooser fc;
	JTextArea log;
	Boolean newFile = true;
	File openFile;
	JPanel about;
	int index = 0;
	String text, next;
	JCheckBoxMenuItem chckbxmntmWordWrap;
	JDialog dialog;
	JTextField tf;

	FontSelection fs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JNotepad window = new JNotepad();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JNotepad() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame("JNotepad");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		log = new JTextArea(5, 20);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);

		fc = new JFileChooser();

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		mnFile.setMnemonic('F');
		menuBar.add(mnFile);

		mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(this);
		mntmNew.setMnemonic('N');
		mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		mnFile.add(mntmNew);

		mntmOpen = new JMenuItem("Open...");
		mntmOpen.addActionListener(this);
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		mnFile.add(mntmOpen);

		mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(this);
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		mnFile.add(mntmSave);

		mntmSaveAs = new JMenuItem("Save As...");
		mntmSaveAs.addActionListener(this);
		mnFile.add(mntmSaveAs);

		separator = new JSeparator();
		mnFile.add(separator);

		mntmPageSetup = new JMenuItem("Page Setup...");
		mnFile.add(mntmPageSetup);

		mntmPrint = new JMenuItem("Print...");
		mntmPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				InputEvent.CTRL_MASK));
		mnFile.add(mntmPrint);

		separator_1 = new JSeparator();
		mnFile.add(separator_1);

		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(this);
		mntmExit.setMnemonic('X');
		mnFile.add(mntmExit);

		mnNewMenu = new JMenu("Edit");
		mnNewMenu.setMnemonic('E');
		menuBar.add(mnNewMenu);

		mntmUndo = new JMenuItem("Undo");
		mnNewMenu.add(mntmUndo);

		separator_2 = new JSeparator();
		mnNewMenu.add(separator_2);

		mntmCut = new JMenuItem("Cut");
		mntmCut.addActionListener(this);
		mntmCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmCut);

		mntmCopy = new JMenuItem("Copy");
		mntmCopy.addActionListener(this);
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmCopy);

		mntmPaste = new JMenuItem("Paste");
		mntmPaste.addActionListener(this);
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmPaste);

		mntmDelete = new JMenuItem("Delete");
		mntmDelete.addActionListener(this);
		mntmDelete
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		mnNewMenu.add(mntmDelete);

		separator_3 = new JSeparator();
		mnNewMenu.add(separator_3);

		mntmFind = new JMenuItem("Find...");
		mntmFind.addActionListener(this);
		mntmFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmFind);

		mntmFindNext = new JMenuItem("Find Next");
		mntmFindNext.addActionListener(this);
		mnNewMenu.add(mntmFindNext);

		mntmReplace = new JMenuItem("Replace...");
		mntmReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
				InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmReplace);

		mntmGoTo = new JMenuItem("Go To...");
		mntmGoTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
				InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmGoTo);

		separator_4 = new JSeparator();
		mnNewMenu.add(separator_4);

		mntmSelectAll = new JMenuItem("Select All");
		mntmSelectAll.addActionListener(this);
		mntmSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmSelectAll);

		mntmTimedate = new JMenuItem("Time/Date");
		mntmTimedate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mnNewMenu.add(mntmTimedate);
		mntmTimedate.addActionListener(this);

		mnFormat = new JMenu("Format");
		mnFormat.setMnemonic('O');
		menuBar.add(mnFormat);

		chckbxmntmWordWrap = new JCheckBoxMenuItem("Word Wrap");
		chckbxmntmWordWrap.addActionListener(this);
		mnFormat.add(chckbxmntmWordWrap);

		mntmFont = new JMenuItem("Font...");
		mntmFont.setMnemonic('F');
		mnFormat.add(mntmFont);
		mntmFont.addActionListener(this);

		mnView = new JMenu("View");
		mnView.setMnemonic('V');
		menuBar.add(mnView);

		mntmStatusBar = new JMenuItem("Status Bar");
		mntmStatusBar.setMnemonic('S');
		mnView.add(mntmStatusBar);

		mnHelp = new JMenu("Help");
		mnHelp.setMnemonic('H');
		menuBar.add(mnHelp);

		mntmViewHelp = new JMenuItem("View Help");
		mntmViewHelp.setMnemonic('H');
		mnHelp.add(mntmViewHelp);

		separator_5 = new JSeparator();
		mnHelp.add(separator_5);

		mntmAboutJnotepad = new JMenuItem("About JNotepad");
		mntmAboutJnotepad.addActionListener(this);
		mnHelp.add(mntmAboutJnotepad);

		about = new JPanel();
		about.add(new JLabel("(c) Michael Ng"));

		textArea = new JTextArea();
		textArea.setInheritsPopupMenu(true);
		frame.getContentPane().add(textArea, BorderLayout.CENTER);

		popupMenu = new JPopupMenu();
		addPopup(textArea, popupMenu);

		popupMenu.add(mntmCut);
		popupMenu.add(mntmCopy);
		popupMenu.add(mntmPaste);
		popupMenu.add(mntmDelete);
		popupMenu.add(mntmSelectAll);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmNew) {
			textArea.setText("");
			newFile = true;

		}
		if (e.getSource() == mntmOpen) {
			int returnVal = fc.showOpenDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				openFile = fc.getSelectedFile();
				try {
					textArea.setText(fileReader(openFile.getPath()));
					frame.setTitle(openFile.getName() + " - JNotepad");
					newFile = false;
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				log.append("Opening " + openFile.getName() + ".\n");
			} else {
				log.append("Open command canclled by user.\n");
			}
			log.setCaretPosition(log.getDocument().getLength());
		}
		if (e.getSource() == mntmSave) {
			if (newFile == true) {
				saveAs();
			} else
				save(openFile);
		}
		if (e.getSource() == mntmSaveAs) {
			saveAs();
		}
		if (e.getSource() == mntmExit) {
			System.exit(1);
		}
		if (e.getSource() == mntmCut) {
			textArea.cut();
		}
		if (e.getSource() == mntmCopy) {
			textArea.copy();
		}
		if (e.getSource() == mntmPaste) {
			textArea.paste();
		}
		if (e.getSource() == mntmDelete) {
			textArea.replaceSelection("");
		}
		if (e.getSource() == mntmFind) {

			text = textArea.getText();
			next = JOptionPane.showInputDialog(null, "Find...", "Find",
					JOptionPane.INFORMATION_MESSAGE);

			index = text.indexOf(next, 0);
			textArea.setCaretPosition(index);
			textArea.setSelectionStart(index);
			textArea.setSelectionEnd(index + next.length());
		}
		if (e.getSource() == mntmFindNext) {

			text = textArea.getText();
			next = JOptionPane.showInputDialog(null, "Find...", "Find Next",
					JOptionPane.INFORMATION_MESSAGE);
			index = text.indexOf(next, textArea.getCaretPosition());
			textArea.setCaretPosition(index);
			textArea.setSelectionStart(index);
			textArea.setSelectionEnd(index + next.length());

		}
		if (e.getSource() == mntmSelectAll) {
			textArea.selectAll();
		}
		if (e.getSource() == mntmTimedate) {
			DateFormat formatter = new SimpleDateFormat("HH:mm MM-dd-yyyy");
			Date date = new Date();
			textArea.append(formatter.format(date));
		}
		if (e.getSource() == chckbxmntmWordWrap) {
			textArea.setLineWrap(chckbxmntmWordWrap.getState());
		}
		if (e.getSource() == mntmFont) {
			fs = new FontSelection();
		}

		if (e.getSource() == mntmAboutJnotepad) {
			JOptionPane.showMessageDialog(frame, "(C) 2014 Michael Ng",
					"About", 1);
		}
	}

	private static WindowListener closeWindow = new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			e.getWindow().dispose();
		}
	};

	public void saveAs() {
		int returnVal = fc.showSaveDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			openFile = fc.getSelectedFile();
			save(openFile);
			log.append("Saving: " + openFile.getName() + ".\n");
		} else {
			log.append("Save command cancelled b user.\n");
		}
	}

	public void save(File file) {
		try {
			FileWriter fw = new FileWriter(file);
			String temp = textArea.getText();
			System.out.println(temp);
			fw.write(temp);
			newFile = false;
			frame.setTitle(file.getName() + " - JNotepad");
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public String fileReader(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		String everything = null;
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			everything = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		return everything;
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

}
