import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.*;

import java.awt.BorderLayout;

import javax.swing.JPopupMenu;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Notepad implements ActionListener {

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
	JMenuItem mntmWordWrap;
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
	JTextArea textArea;
	JPopupMenu popupMenu;
	JMenuItem mntmCut_1;
	JMenuItem mntmCopy_1;
	JMenuItem mntmPaste_1;
	JFileChooser fc;
	JTextArea log;
	Boolean newFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notepad window = new Notepad();
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
	public Notepad() {
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
		mntmCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmCut);

		mntmCopy = new JMenuItem("Copy");
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmCopy);

		mntmPaste = new JMenuItem("Paste");
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmPaste);

		mntmDelete = new JMenuItem("Delete");
		mntmDelete
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		mnNewMenu.add(mntmDelete);

		separator_3 = new JSeparator();
		mnNewMenu.add(separator_3);

		mntmFind = new JMenuItem("Find...");
		mntmFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmFind);

		mntmFindNext = new JMenuItem("Find Next");
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
		mntmSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmSelectAll);

		mntmTimedate = new JMenuItem("Time/Date");
		mntmTimedate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mnNewMenu.add(mntmTimedate);

		mnFormat = new JMenu("Format");
		mnFormat.setMnemonic('O');
		menuBar.add(mnFormat);

		mntmWordWrap = new JMenuItem("Word Wrap");
		mntmWordWrap.setMnemonic('W');
		mnFormat.add(mntmWordWrap);

		mntmFont = new JMenuItem("Font...");
		mntmFont.setMnemonic('F');
		mnFormat.add(mntmFont);

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
		mnHelp.add(mntmAboutJnotepad);

		textArea = new JTextArea();
		textArea.setInheritsPopupMenu(true);
		frame.getContentPane().add(textArea, BorderLayout.CENTER);

		popupMenu = new JPopupMenu();
		addPopup(textArea, popupMenu);

		mntmCut_1 = new JMenuItem("Cut");
		popupMenu.add(mntmCut_1);

		mntmCopy_1 = new JMenuItem("Copy");
		popupMenu.add(mntmCopy_1);

		mntmPaste_1 = new JMenuItem("Paste");
		popupMenu.add(mntmPaste_1);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmNew) {
			textArea.setText("");
			newFile = true;

		}
		if (e.getSource() == mntmOpen) {
			int returnVal = fc.showOpenDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				try {
					textArea.setText(fileReader(file.getPath()));
					frame.setTitle(file.getName() + " - JNotepad");
					newFile = false;
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				log.append("Opening " + file.getName() + ".\n");
			} else {
				log.append("Open command canclled by user.\n");
			}
			log.setCaretPosition(log.getDocument().getLength());
		}
		if (e.getSource() == mntmSave) {
			if (newFile == true) {
				SaveAs();
			}
		}
		if (e.getSource() == mntmSaveAs) {
			int returnVal = fc.showSaveDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
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
				log.append("Saving: " + file.getName() + ".\n");
			} else {
				log.append("Save command cancelled b user.\n");
			}
		}
		if (e.getSource() == mntmExit) {
			System.exit(1);
		}

	}

	public void SaveAs() {

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
			// TODO Auto-generated catch block
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
