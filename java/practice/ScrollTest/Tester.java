import java.awt.*;
import javax.swing.*;

public class Tester extends JFrame {
    public Tester() {
        JPanel panel = new JPanel();
        panel.add(new JButton("b1"));
        panel.add(new JButton("b2"));
        panel.add(new JButton("b3"));
        panel.add(new JButton("b4"));
        panel.add(new JButton("b5"));
        panel.add(new JButton("b6"));

        JScrollPane pane = new JScrollPane();
        pane.setPreferredSize(new Dimension(100, 100));

        // Important! pane.setViewportView(panel) doesn't work!
        JViewport vp = new JViewport();
        vp.setView(panel);
        pane.setViewport(vp);

        // Add panel with the buttons
        JPanel master = new JPanel();
        master.add(pane);

        this.add(master);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Tester();
    }
}
