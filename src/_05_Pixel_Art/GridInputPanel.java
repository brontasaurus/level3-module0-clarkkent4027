package _05_Pixel_Art;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GridInputPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField windowWidthField;
	private JTextField windowHeightField;
	private JTextField rowsField;
	private JTextField colsField;
	private JButton submitButton;

	PixelArtMaker pam;

	public GridInputPanel(PixelArtMaker pam) {
		this.pam = pam;
		//int response = JOptionPane.showOptionDialog(null, "Would you like to load previous art?",
		//		"Make your choice, or DIE!", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, null, null, null);
		//if (response == 0) {
			int ww = 0;
			int wh = 0;
			int c = 0;
			int r = 0;
			System.out.println("yes");
			try {
				BufferedReader br = new BufferedReader(new FileReader("src/_05_Pixel_Art/Pixels"));
				String line = br.readLine();
				if (line != null) {
					ww = Integer.parseInt(line);
					line = br.readLine();
					wh = Integer.parseInt(line);
					line = br.readLine();
					c = Integer.parseInt(line);
					line = br.readLine();
					r = Integer.parseInt(line);
					br.close();
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			pam.loadGridData(ww, wh, c, r);
			pam.gp.load();

//		} else if (response == 1)
//
//		{
//			System.out.println("no");
//			this.GIP(pam);
//		} else {
//			this.GIP(pam);
//		}
	}

	public void GIP(PixelArtMaker pam) {
		this.pam = pam;
		windowWidthField = new JTextField(5);
		windowHeightField = new JTextField(5);
		rowsField = new JTextField(5);
		colsField = new JTextField(5);
		submitButton = new JButton("Submit");
		add(new JLabel("\tscreen width:"));
		add(windowWidthField);
		add(new JLabel("\tscreen height:"));
		add(windowHeightField);
		add(new JLabel("\ttotal rows:"));
		add(rowsField);
		add(new JLabel("\ttotal columns:"));
		add(colsField);
		add(submitButton);

		submitButton.addActionListener((e) -> submit());
	}

	private void submit() {
		boolean valid = false;
		int w = -1;
		int h = -1;
		int r = -1;
		int c = -1;
		try {
			w = Integer.parseInt(windowWidthField.getText());
			h = Integer.parseInt(windowHeightField.getText());
			r = Integer.parseInt(rowsField.getText());
			c = Integer.parseInt(colsField.getText());

			if (w <= 0 || h <= 0 || r <= 0 || c <= 0) {
				invalidateInput();
			} else {
				valid = true;
			}
		} catch (NumberFormatException e) {
			invalidateInput();
		}

		if (valid) {
			pam.submitGridData(w, h, r, c);
		}
	}

	private void invalidateInput() {
		JOptionPane.showMessageDialog(null, "Be sure all fields are complete with positive numbers.", "ERROR", 0);
	}

}
