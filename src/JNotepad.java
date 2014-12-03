import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class JNotepad implements ActionListener {

	JFrame frame;
	JMenuBar menu;
	JMenu file, edit, format, view, help;
	JMenuItem newFile, open, save, saveAs, pageSetup, print, exit;
	
	JNotepad(){
		frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menu = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");
		format = new JMenu("Format");
		view = new JMenu("View");
		help = new JMenu("Help");
		
		newFile = new JMenuItem("New");
		open = new JMenuItem("Open...");
		save = new JMenuItem("Save");
		saveAs = new JMenuItem("Save As...");
		pageSetup = new JMenuItem("Page Setup...");
		print = new JMenuItem("print...");
		exit = new JMenuItem("Exit");
		
		file.add(newFile);
		newFile.setMnemonic('n');
		file.add(open);
		file.add(save);
		file.add(saveAs);
		file.addSeparator();
		file.add(pageSetup);
		pageSetup.setMnemonic('u');
		file.add(print);
		file.addSeparator();
		file.add(exit);
		exit.setMnemonic('x');
		menu.add(file);
		
		menu.add(edit);
		
		menu.add(format);
		
		menu.add(view);
		
		menu.add(help);
		
		frame.setJMenuBar(menu);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new JNotepad();
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
