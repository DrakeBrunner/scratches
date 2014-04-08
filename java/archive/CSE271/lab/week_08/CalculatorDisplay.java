import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorDisplay extends JFrame implements ActionListener {
	private JTextField input;
	private int result, arg;
	private char op;

	public CalculatorDisplay() {
		super("Calculator");

		this.setBounds(700, 300, 500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Initialize all
		result = arg = op = 0;

		// Create a place for the calculator display
		// Number of columns
		input = new JTextField(20);
		// Don't allow editing
		input.setEditable(false);
		// Right-side Alignment
		input.setHorizontalAlignment(JTextField.RIGHT);
		input.setText("0");

		this.add(input, BorderLayout.NORTH);

		// JPanel
		JPanel buttonPanel = new JPanel();
		// Use GridLayout instead of the default FlowLayout
		// The second parameter doesn't matter if other than 0
		buttonPanel.setLayout(new GridLayout(4, 0));

		String text = "123+456-789*0/=C";

		for (int i = 0; i < 16; i++) {
			// Add buttons
			JButton b = new JButton(text.substring(i, i + 1));
			b.setForeground(new Color(255, 0, 0));
			b.setBackground(new Color(255, 255, 0));
			b.addActionListener(this);
			b.setActionCommand(b.getText());
			buttonPanel.add(b);
		}

		this.add(buttonPanel);
		
		this.pack();

		// The last thing to do
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		char cmd = ae.getActionCommand().charAt(0);
		if (Character.isDigit(cmd) && input.getText().length() < 9) {
			// Build the number and display it
			arg = 10 * arg + (cmd - '0');
			input.setText("" + arg);
		} else if (cmd == 'C') {
			// Clear everything
			result = arg = op = 0;
			input.setText("" + arg);
		} else {
			// Perform the appropriate calculation
			// and show the result
			try {
				switch (op) {
				case 0:
					result = arg;
					break;
				case '+':
					result += arg;
					break;
				case '-':
					result -= arg;
					break;
				case '*':
					result *= arg;
					break;
				case '/':
					result /= arg;
				}
				input.setText("" + result);
				arg = 0;
				op = (cmd == '=' ? 0 : cmd);
			} catch (Exception e) {
				// What to do if the math goes wrong:
				input.setText("ERROR!");
				result = arg = op = 0;
			}
		}
	} // End actionPerformed()
}