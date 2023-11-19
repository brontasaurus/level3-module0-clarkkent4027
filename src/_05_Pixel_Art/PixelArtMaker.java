package _05_Pixel_Art;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PixelArtMaker implements MouseListener{
    private JFrame window;
    private GridInputPanel gip;
    public GridPanel gp;
    ColorSelectionPanel csp;
    public void start() {
		int response = JOptionPane.showOptionDialog(null, "Would you like to load previous art?",
				"Make your choice, or DIE!", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, null, null, null);
		
        window = new JFrame("Pixel Art");
        window.setLayout(new FlowLayout());
        window.setResizable(false);
        
        
		if (response == 0) { //skip gridinputpanel, instead load the dimension data
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

			loadGridData(ww, wh, c, r);
			gp.load();

		}
		else {
        
        gip = new GridInputPanel(this);	
        window.add(gip);
		}
        
        
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        
    }

    public void submitGridData(int w, int h, int r, int c) {
        gp = new GridPanel(w, h, r, c);
        csp = new ColorSelectionPanel(gp);
        window.remove(gip);
        window.add(gp);
        window.add(csp);
        gp.repaint();
        gp.addMouseListener(this);
        window.pack();
    }
    
    public void loadGridData(int w, int h, int r, int c) {
    	System.out.println("loadGridData");
    	 //window = new JFrame("Pixel Art");
         //window.setLayout(new FlowLayout());
         //window.setResizable(false);
         //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         //window.setVisible(true);
        gp = new GridPanel(w, h, r, c);
        csp = new ColorSelectionPanel(gp);
        window.add(gp);
        window.add(csp);
        }
        else
        {
        	System.out.println("window == null");
        }
        gp.repaint();
        gp.addMouseListener(this);
        window.pack();
    }

    public static void main(String[] args) {
        new PixelArtMaker().start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gp.setColor(csp.getSelectedColor());
        System.out.println(csp.getSelectedColor());
        gp.clickPixel(e.getX(), e.getY());
        gp.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
