import java.awt.*;
import javax.swing.*;

public class TestMargin extends JFrame {
    public TestMargin() {
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.CYAN);
        panel1.setPreferredSize(new Dimension(400, 400));

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.RED);
        panel2.setPreferredSize(new Dimension(300, 300));

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.BLACK);
        // panel3.setPreferredSize(new Dimension(200, 200));
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JLabel label1 = new JLabel("label1");
        label1.setBackground(Color.YELLOW);
        label1.setOpaque(true);
        panel3.add(label1);

        JLabel label2 = new JLabel("label2");
        label2.setBackground(Color.GREEN);
        label2.setOpaque(true);
        panel3.add(label2);

        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
    }

    public static void main(String[] args) {
        TestMargin test = new TestMargin();
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setSize(new Dimension(500, 500));
        test.setLocationRelativeTo(null);
        test.setVisible(true);
    }
}
