import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class FontDialog {
	private static Font font;
	
	/**
	 * Asks the user for the font by showing a dialog that allows the user to select a font.
	 * The user can choose the font family, style, and size.
	 * @return The font that the user chose.
	 */
	public static Font chooseFont() {
		showDialogAndChoose(null);
		return font;
	}
	
	/**
	 * Asks the user for the font by showing a dialog that allows the user to select a font.
	 * The user can choose the font family, style, and size. The default value will be set to
	 * whatever value the given font has (for example, if a font that is bold was given, the "Bold"
	 * checkbox will be checked by default).
	 * @param f The font that will be used as the default value.
	 * @return The font that the user chose.
	 */
	public static Font chooseFont(Font f) {
		showDialogAndChoose(f);
		return font;
	}
	
	/**
	 * Sets the name, style, and size of the font that will be returned.
	 * @param name The font family name that the user has chosen.
	 * @param style The style (bold, italic) that the user has chosen.
	 * @param size The point size of the font that the user has chosen.
	 */
	private static void setFont(String name, int style, int size) {
		font = new Font(name, style, size);
	}
	/**
	 * Shows a dialog that allows the user to change the font. The user can change
	 * the font family(Arial, MS Gothic, etc.), style (bold, italic), and the size in points.
	 * It uses a JComboBox for the family, JCheckBox for style, and a JSlider for the size.
	 * The modified values are substituted into the variables in this Settings class when "OK" is clicked.
	 * Accepts a default value as font. For example, if a Font instance with "Times New Roman", BOLD, 40 points
	 * was given, it will set the default value to those values. There will be no default value when null is passed.
	 */
	private static void showDialogAndChoose(Font f) {
		final JDialog dialog = new JDialog();
		dialog.setSize(280, 150);
		dialog.setLocationRelativeTo(null);
		dialog.setLayout(new BorderLayout());
		
		// Panel that contains Font family, style, and size
		JPanel fontSetting = new JPanel();
		fontSetting.setLayout(new FlowLayout());
		
		// Get all the fonts available in that environment
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		final JComboBox fontNameComboBox = new JComboBox(fonts);
		if (f != null)
			fontNameComboBox.setSelectedItem(f.getFamily());
		fontNameComboBox.setPreferredSize(new Dimension(170, fontNameComboBox.getPreferredSize().height));
		fontSetting.add(fontNameComboBox);
		
		// Panel that contains the font's style
		JPanel fontStyle = new JPanel();
		fontStyle.setLayout(new GridLayout(0, 1));
		
		final JCheckBox bold = new JCheckBox("Bold");
		if (f != null)
			bold.setSelected(f.isBold());
		final JCheckBox italic = new JCheckBox("Italic");
		if (f != null)
			italic.setSelected(f.isItalic());
		fontStyle.add(bold);
		fontStyle.add(italic);
		fontSetting.add(fontStyle);
		
		// Slider to set the size with the default value of the current point size
		final JSlider sizeSlider = new JSlider(5, 80);
		if (f != null)
			sizeSlider.setValue(f.getSize());
		sizeSlider.setMajorTickSpacing(15);
		sizeSlider.setMinorTickSpacing(5);
		sizeSlider.setPreferredSize(new Dimension(200, 30));
		final JLabel sizeLabel = new JLabel(sizeSlider.getValue() + "points");
		fontSetting.add(new JPanel() {{
			this.add(sizeSlider);
			this.add(sizeLabel);
		}});
		// Add a listener so that the size changes as the knob moves
		sizeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider js = (JSlider)e.getSource();
				sizeLabel.setText(String.format("%2d points", js.getValue()));
			}
		});
		
		// OK, Apply, and Cancel buttons, each having their own ActionListener
		JPanel buttons = new JPanel();
		final ActionListener click = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// add
				if (e.getActionCommand() == "OK") {
					// Change the font
					// Get font name
					String name = (String)fontNameComboBox.getSelectedItem();
					// Get style
					int style;
					if (bold.isSelected() && italic.isSelected())
						style = Font.BOLD + Font.ITALIC;
					else if (bold.isSelected())
						style = Font.BOLD;
					else if (italic.isSelected())
						style = Font.ITALIC;
					else
						style = Font.PLAIN;
					
					int size = sizeSlider.getValue();
					
					// Set the name, style, and size of the instance variable
					setFont(name, style, size);
					
					dialog.setVisible(false);
				}
				if (e.getActionCommand() == "Cancel")
					// Close the dialog
					dialog.setVisible(false);
			}
		};
		buttons.add(new JButton("OK") {{
			this.addActionListener(click);
		}});
		buttons.add(new JButton("Cancel") {{
			this.addActionListener(click);
		}});
		
		// Finally, add all the components
		dialog.add(fontSetting);
		dialog.add(buttons, BorderLayout.SOUTH);
		// Prevent other codes from running while the dialog is open
		dialog.setModalityType(ModalityType.DOCUMENT_MODAL);
		dialog.setVisible(true);
	}
}
