import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class OthelloGUI {

    JFrame mainFrame;
    JPanel mainPanel;
    JLabel label = new JLabel();
    public static final int GAP = 45;
    public static final int LINE_WIDTH = 5;
    public static final int BOARD_SIZE = GAP * 8 + LINE_WIDTH * 7;
    private boolean waiting; 
    private int player;
    private boolean ended = false;

    Othello o;

    public OthelloGUI() {
        label.setText("Welcome to the game of Othello");

        mainFrame = new JFrame("Othello");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setSize(BOARD_SIZE, BOARD_SIZE + label.getPreferredSize().height * 2);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);

        mainPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                int gap = (BOARD_SIZE - LINE_WIDTH * 7) / 8;
                for (int i = GAP; i < BOARD_SIZE; i += GAP + LINE_WIDTH) {
                    g.fillRect(i, 0, LINE_WIDTH, BOARD_SIZE);
                    g.fillRect(0, i, BOARD_SIZE, LINE_WIDTH);
                }
                this.setBackground(new Color(17, 166, 42));

                int setBoardX = 0;
                int setBoardY = 0;
                int lastX = o.getLastChange()[0];
                int lastY = o.getLastChange()[1];
                String last = String.format("Row %d Column %d", lastX, lastY);
                char[][] board = o.getBoard();
                if (board == null)
                    label.setText("Invalid place");
                else {
                    for (int i = 0; i < Othello.BOARD_SIZE; i++) {
                        setBoardX = 0;
                        for (int j = 0; j < Othello.BOARD_SIZE; j++) {
                            if (board[i][j] == Othello.WHITE_SQUARE) {
                                if (i == lastX && j == lastY)
                                    g.setColor(Color.LIGHT_GRAY);
                                else
                                    g.setColor(Color.WHITE);
                                g.fillOval(setBoardX, setBoardY, gap, gap);
                            }
                            else if (board[i][j] == Othello.BLACK_SQUARE) {
                                if (i == lastX && j == lastY)
                                    g.setColor(Color.DARK_GRAY);
                                else
                                    g.setColor(Color.BLACK);
                                g.fillOval(setBoardX, setBoardY, gap, gap);
                            }

                            setBoardX += gap + LINE_WIDTH;
                        }
                        setBoardY += gap + LINE_WIDTH;
                    }
                }


                this.repaint();
            }
        };
        mainPanel.addMouseListener(new ClickListener());

        mainFrame.add(mainPanel);
        mainFrame.add(label, BorderLayout.SOUTH);

        mainFrame.setVisible(true);

        startGame();
    }

    public void startGame() {
        o = new Othello();
        o.initBoard();
        player = (int)(Math.random() * 100) > 50 ? Othello.WHITE : Othello.BLACK;
        if (player == Othello.BLACK)
            o.place();
        label.setText(o.getPlayer() + "'s Turn");
    }

    public void playerTurn(int i, int j) {
        boolean playerPassed = false;
        label.setText(o.getPlayer() + "'s Turn");
        if (!o.isFlippable(i, j, player)) {
            label.setText("Passing");
            playerPassed = true;
        }
        if (!o.place(i, j)) {
            label.setText(o.getPlayer() + "'s Turn " + "Invalid place!");
            return;
        }
        // label.setText(o.getPlayer() + "'s Turn");
        // Computer's turn if possible
        if ((!o.place() && playerPassed) || !o.isContinuable()) {
            end();
            ended = true;
        }
    }

    public void end() {
        char[][] b = o.getBoard();
        int b_cnt = 0;
        int w_cnt = 0;
        for (int i = 0; i < Othello.BOARD_SIZE; i++) {
            for (int j = 0; j < Othello.BOARD_SIZE; j++) {
                if (b[i][j] == Othello.WHITE_SQUARE)
                    w_cnt++;
                else if (b[i][j] == Othello.BLACK_SQUARE)
                    b_cnt++;
            }
        }
        String winner = w_cnt > b_cnt ? "White" : "Black";
        if (w_cnt == b_cnt)
            label.setText("The game was a tie");
        else
            label.setText(String.format("%s wins with %d : %d", winner, w_cnt, b_cnt));
    }

    public class ClickListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (ended)
                return;
            int i = e.getPoint().y * 8 / BOARD_SIZE;
            int j = e.getPoint().x * 8 / BOARD_SIZE;
            // System.out.println(x + " : " + y);
            playerTurn(i, j);
        }

        @Override
        public void mouseEntered(MouseEvent arg0) {
        }

        @Override
        public void mouseExited(MouseEvent arg0) {
        }

        @Override
        public void mousePressed(MouseEvent arg0) {
        }

        @Override
        public void mouseReleased(MouseEvent arg0) {
        }
    }

    public static void main(String[] args) {
        OthelloGUI othelloGUI = new OthelloGUI();
    }
}
