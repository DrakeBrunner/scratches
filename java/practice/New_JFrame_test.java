import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

/**
 * This is a simple test program that tries to create a new JFrame window when
 * a button is pressed.
 * @author Naoki Mizuno
 */

public class New_JFrame_test {
    private static final JFrame f1 = new JFrame("First frame");
    private static JFrame f2 = new JFrame("Second frame");
    private static final JButton button = new JButton("Click me!");
    private static final JButton button2 = new JButton("Click me, too!");
    private static final JLabel j = new JLabel();
    private static ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Set the first frame to invisible and show another frame
            if (e.getSource() == button) {
                f1.setVisible(false);
                f2.setLocationRelativeTo(null);
                j.setText((int)(Math.random() * 100) + "");
                f2.setVisible(true);
            }
            else if (e.getSource() == button2) {
                f2.setVisible(false);
                f1.setLocationRelativeTo(null);
                j.setText((int)(Math.random() * 100) + "");
                f1.setVisible(true);
            }
        }
    };
    public static void main(String[] args) {
        button.addActionListener(al);
        f1.add(button);

        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f1.setLayout(new FlowLayout());
        f1.add(j);
        j.setText((int)(Math.random() * 100) + "");
        f1.setSize(new Dimension(100, 50));
        f1.pack();
        f1.setVisible(true);
        f1.setLocationRelativeTo(null);

        f2.setSize(new Dimension(100, 50));
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        // f2.setLocation(d.width / 2 - f2.getWidth(), d.height / 2 - f2.getHeight());
        button2.addActionListener(al);
        f2.add(button2);
        f2.add(j);
        j.setText((int)(Math.random() * 100) + "");
        f2.setLayout(new FlowLayout());
        f2.pack();
        f2.setVisible(false);
        f2.setLocationRelativeTo(null);
    }
}
