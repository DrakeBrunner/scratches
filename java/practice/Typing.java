import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// TODO: Make private instance variables for the colors
// TODO: Put the letters into a JLabel that represents the word so that the words will no be separated when coming to the end of the line.
// |words being sep|
// |arated among li|

public class Typing extends JFrame implements KeyListener {

    // public static final String word = "Hello world";
    private boolean finished = false;
    public static final String FILENAME = "dic.txt";
    private int totalNumOfLetters = 0;
    private ArrayList<String> words = new ArrayList<String>();
    private ArrayList<JLabel> labels = new ArrayList<JLabel>();
    private static int cnt = 0;
    private static int miss = 0;

    public Typing() {
        super();

        setSize(800, 400);

        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());

        // Add words from dictionary
        try {
            Scanner input = new Scanner(new File(FILENAME));
            while (input.hasNext())
                words.add(input.next());
        }
        catch (FileNotFoundException e) {
            // TODO: When no dictionary file is found
            e.printStackTrace();
        }

        // Add all the words to one String
        for (int i = 0; i < words.size(); i++)
            totalNumOfLetters += words.get(i).length() + 1;

        for (int w = 0; w < words.size(); w++) {
            String word = words.get(w);
            for (int i = 0; i < word.length(); i++) {
                JLabel tmp = new JLabel(Character.toString(word.charAt(i)));
                tmp.setFont(new Font("Arial", Font.BOLD, 40));
                tmp.setForeground(Color.BLUE);
                labels.add(tmp);
                p.add(tmp);
            }
            // TODO: Change white space to letter and set the color to the
            // color of background so that the user is able to see it being
            // hilighted when miss typing.
            JLabel tmp = new JLabel(" ");
            tmp.setFont(new Font("Arial", Font.BOLD, 40));
            labels.add(tmp);
            p.add(tmp);
        }

        // To listen for key press
        addKeyListener(this);

        getContentPane().add(p);

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (finished)
            return;

        char pressed = e.getKeyChar();

        String s;
        if ((s = labels.get(cnt).getText()) != null) {
            if (pressed == s.charAt(0))
                labels.get(cnt).setForeground(Color.RED);
            // When the user presses the wrong key
            else {
            	// TODO: Change the color for a moment but set it back to the original color (blue)
                labels.get(cnt).setForeground(Color.GREEN);
                miss++;
                return;
            }
        }
        repaint();

        // Remember where the last index was
        cnt++;

        // Finish if the user finishes typing all the words (the last letter is always a white space)
        if (cnt == totalNumOfLetters - 1) {
            JOptionPane.showMessageDialog(null,
                    "Congratulations, you successfully typed all the words with " + miss + " mistakes!",
                    "Mission Accomplished", JOptionPane.INFORMATION_MESSAGE);
            finished = true;
        }
    }

    public static void main(String[] args) {
        Typing t = new Typing();
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t.setLocationRelativeTo(null);

        t.setVisible(true);
    }

}
