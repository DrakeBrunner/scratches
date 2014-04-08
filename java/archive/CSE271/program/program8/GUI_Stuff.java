
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * This is a class that shows GUI screen of the polygon.
 * The user clicks inside the window at least 3 times, then choose
 * from left click, right click, or shift + right click.
 * If s/he left clicks, another point is added to the current polygon.
 * Right clicking will store the current polygon to an ArrayList, and
 * pressing down the shift key would do the same thing except it will store
 * with the points with dots.
 * The user can save or load from a file, and a prompt will be shown if there
 * are things that the user should be aware of (like overwriting).
 *
 * @author Naoki Mizuno
 */

@SuppressWarnings("serial")
public class GUI_Stuff extends JPanel {
    private static final String FILENAME = "poly.dat";
    
	private JButton btn_clear, btn_save, btn_load;
    private ArrayList<Polygon> polyCollection;

    /**
     * Constructs a new GUI_Stuff object, an object that
     * does all the GUI stuff. There are 3 buttons: Save, Load, and Clear.
     * The initial size of the window is set to 500x500 and the background
     * is black.
     */
    public GUI_Stuff() {
        super();
        
        polyCollection = new ArrayList<Polygon>();
        // Add a polygon since it's the first one
        polyCollection.add(new Polygon());
        
        this.setPreferredSize(new Dimension(500, 500));
        this.setBackground(Color.black);
        this.setLayout(new FlowLayout());
        
        btn_clear = new JButton("Clear");
        int btnWidth = btn_clear.getPreferredSize().width;
        
        btn_clear.setBounds(250 + btnWidth / 2, 0, btnWidth, 30);
        btn_clear.setEnabled(true);
        this.add(btn_clear);
        
        btn_save = new JButton("Save");
        btn_save.setBounds(250 - 3 * btnWidth / 2, 0, btnWidth, 30);
        btn_save.setEnabled(true);
        this.add(btn_save);
        
        btn_load = new JButton("Load");
        btn_load.setBounds(250 - btnWidth / 2, 0, btnWidth, 30);
        btn_load.setEnabled(true);
        this.add(btn_load);
        
        // Add a mouse listener
        this.addMouseListener(new MouseClickResponder());
        
        // Add a button listener for the buttons to work
        ButtonResponder b = new ButtonResponder();
        btn_clear.addActionListener(b);
        btn_save.addActionListener(b);
        btn_load.addActionListener(b);
    }
    
    /**
     * Scan through all the points in all the polygons and show them all.
     * Add the dots (ovals) to the current polygon as specified.
     */
    @Override
    public void paintComponent(Graphics g) {
    	// I DID NOT KNOW THERE WERE
    	// paintComponent(g) AND paintComponents(g) WITH AN "S"!!!!!
    	// I have to be careful from now on because this took me hours and hours to figure out
    	super.paintComponent(g);
    	
    	for (int i = 0; i < polyCollection.size(); i++) {
			Polygon tmp = polyCollection.get(i);
	    	g.setColor(tmp.getColor());
			ArrayList<Point> pointsInTmp = tmp.getPoints();
			
    		for (int j = 0; j < pointsInTmp.size(); j++) {
				if (j != 0)
					g.drawLine(pointsInTmp.get(j - 1).x, pointsInTmp.get(j - 1).y,
							pointsInTmp.get(j).x, pointsInTmp.get(j).y);
				
				// Fill it if isFilled is true OR if it's the current polygon
				// (which is the last polygon in polyCollection
				if (tmp.getIsFilled() || i == polyCollection.size() - 1)
					g.fillOval(pointsInTmp.get(j).x - 4, pointsInTmp.get(j).y - 4, 8, 8);
    		}
    		
    		// Connect the last and first point if the polygon is closed
    		if (tmp.getIsClosed()) {
    			g.drawLine(pointsInTmp.get(0).x, pointsInTmp.get(0).y,
						pointsInTmp.get(pointsInTmp.size() - 1).x,
						pointsInTmp.get(pointsInTmp.size() - 1).y);
    		}
    	}
    }
    
    public class MouseClickResponder implements MouseListener {

    	/**
    	 * Whenever the left mouse button is clicked, a point should be
    	 * added to the polygon
    	 */
		@Override
		public void mouseClicked(MouseEvent e) {
			Polygon polygon = polyCollection.get(polyCollection.size() - 1);
			
			if (e.getButton() == MouseEvent.BUTTON1)
				polygon.addPoint(e.getX(), e.getY());
			else if (e.getButton() == MouseEvent.BUTTON3 && polygon.getNumOfPoints() >= 3) {
				// Check whether the "Shift" key is pressed
				if (e.isShiftDown())
					polygon.setIsFilled(true);
				
				polygon.setIsClosed(true);

				// Draw the lines BEFORE the message
                repaint();

                JOptionPane.showMessageDialog(null,
                        "New " + polygon.getPoints().size() + "-gon got recorded.\n"
                        + polyCollection.size() + " polygons in all.");

				// Add new polygon for next round
				polyCollection.add(new Polygon());
			}
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    }
    
    public class ButtonResponder implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Checking whether the button clicks are recognized
			// System.out.println("Button " + e.getSource() + " was clicked!");
			if (e.getSource() == btn_save)
				write();
			else if (e.getSource() == btn_load)
				load();
			else if (e.getSource() == btn_clear)
				clear();

			repaint();
		}
    }
    
    /**
     * Writes all the polygons that are in drawn on the frame to a file named "poly.dat".
     * It saves the color, whether it's filled, whether it's closed (or completed), and
     * all the coordinates of the points.
     */
	public void write() {
		File f = new File(FILENAME);
		// Check if the file already exists
		if (f.exists()) {
			int choice = JOptionPane.showConfirmDialog(btn_load,
					"There is already a file.\nAre you sure you want to overwrite?",
					"Overwrite?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			// Do nothing if the user chooses not to overwrite
			if (choice == 1)
				return;
		}
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
			// Write the result of toString for each polygon
			for (int i = 0; i < polyCollection.size(); i++)
				bw.write(polyCollection.get(i).toString());
			
			bw.flush();
			bw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads all the information from a file named "poly.dat". The file should contain
	 * the color, whether it's filled, whether the polygon is closed, and all the coordinates
	 * of the points. If the file does not exist, it shows a message telling that to the user.
	 */
	public void load() {
		// If we check if there's some points in the first (automatically
		// added in constructor) polygon, we know if the user has drawn any point yet
		if (polyCollection.get(0).getNumOfPoints() != 0
				|| polyCollection.size() != 1) {
			int choice = JOptionPane.showConfirmDialog(btn_load,
					"There are already some points.\nAre you sure you want to overwrite these?",
					"Overwrite?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			// When the user chooses "No"
			// (0 is returns when the user chooses "Yes")
			if (choice == 1)
				return;
		}
			
		try {
			Scanner reader = new Scanner(new File(FILENAME));
			
			// Don't clear it till now in case the user doesn't want to overwrite it!
			polyCollection.clear();
			// Read in polygons until it reaches EOF
			while (reader.hasNext())
				polyCollection.add(Polygon.readPolygon(reader));
		}
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(btn_load, "Error opening file: " + FILENAME
					+ " (most likely the file didn't exist)");
			// e.printStackTrace();
		}
	}
	
    /**
     * Clears all the polygons in the ArrayList.
     * After that, add a new polygon to the cleared ArrayList so that
     * we can start drawing a new set of polygons.
     */
	public void clear() {
		polyCollection.clear();
        // Add a polygon since it's the first one after clearing
        polyCollection.add(new Polygon());
	}
	
    // The main method that displays the frame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Polygon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GUI_Stuff());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
