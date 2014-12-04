import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/*
 * Class to create a new frame in order to select the font for the document
 * uses a JComboBox in order to select font sizes and fonts.
 */
public class FontSelection {
	JFrame frame;
	JPanel panel1, panel2;
	JLabel fontName, fontSize;
	JComboBox jcbFonts, jcbSizes;
	JTextArea jtxtWelcome;
	JButton ok;
	Font f;

	FontSelection() {
		frame = new JFrame(); //set up frame constraints
		frame.setTitle("Set Font");
		frame.setLocationRelativeTo(null);
		
		panel1 = new JPanel();
		fontName = new JLabel("Font Name");
		fontSize = new JLabel("Font Size");
		ok = new JButton("Ok");

		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment() //get list of avaible fonts on the system
				.getAvailableFontFamilyNames();
		jcbFonts = new JComboBox(fonts);
		jcbSizes = new JComboBox();
		
		for (int i = 10; i < 40; i++) { //sets different sizes of fonts 10 to 40 in intervals of 2
			jcbSizes.addItem(i);
			i++;
		}

		panel1.add(fontName);
		panel1.add(jcbFonts);
		panel1.add(fontSize);
		panel1.add(jcbSizes);

		panel2 = new JPanel();
		panel2.add(ok);

		frame.add(panel1, BorderLayout.NORTH);
		frame.add(panel2, BorderLayout.SOUTH);

		ListenerClass listener = new ListenerClass();
		jcbFonts.addActionListener(listener);
		jcbSizes.addActionListener(listener);
		ok.addActionListener(listener);

		frame.pack();
		frame.setVisible(true);
	}

	/*
	 * Generates UI (not used)
	 */
	public void generateUI() {
		new FontSelection();
	}

	/*
	 * Listener for the Combo Boxes and the button
	 */
	private class ListenerClass implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == ok) {
				JNotepad.textArea.setFont(new Font("serif", Font.PLAIN, Integer //sets textArea to selected fonts
						.parseInt(jcbSizes.getSelectedItem().toString())));
				frame.setVisible(false);
			}

		}
	}

}