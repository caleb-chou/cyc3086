package pwgen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JButton;

public class Pwgen {

	private JFrame frame;
	private JTextField textField;

	private short length;
	private boolean capital,lowercase,numbers,symbols;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pwgen window = new Pwgen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Pwgen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		
		JLabel pw_label = new JLabel("New Password");
		panel.add(pw_label);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(16);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(new Password(length,symbols,numbers,lowercase,capital).toString() );
			}
		});
		panel.add(btnGenerate);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JSpinner numchar = new JSpinner();
		panel_1.add(numchar);
		numchar.setValue(12);
		
		JCheckBox chckbxSymbols = new JCheckBox("Symbols");
		panel_1.add(chckbxSymbols);
		
		JCheckBox chckbxNumbers = new JCheckBox("Numbers");
		panel_1.add(chckbxNumbers);
		
		JCheckBox chckbxLowercaseLetters = new JCheckBox("Lowercase Letters");
		panel_1.add(chckbxLowercaseLetters);
		
		JCheckBox chckbxCapitalLetters = new JCheckBox("Capital Letters");
		panel_1.add(chckbxCapitalLetters);
	}

}

class Password {
	String pass;
	Password(short len, boolean sym, boolean num, boolean lower, boolean upper) {
		pass = create(len,sym,num,lower,upper);
	}
	private String create(short len, boolean sym, boolean num, boolean lower, boolean upper) {
		String temp = "";
		
		return temp;
	}
	public String toString() {
		return pass;
	}
}
