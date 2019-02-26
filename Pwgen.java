package pwgen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JButton;

public class Pwgen {

	private JFrame frame;
	private JTextField textField;
	private JTextField txtenterCharacters;
	
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
		textField.setColumns(32);
		
		JButton btnGenerate = new JButton("Generate");
		
		panel.add(btnGenerate);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JLabel lblLentgh = new JLabel("Lentgh");
		panel_1.add(lblLentgh);
		
		JSpinner numchar = new JSpinner();
		panel_1.add(numchar);
		numchar.setValue(12);
		
		JCheckBox chckbxSymbols = new JCheckBox("Symbols");
		panel_1.add(chckbxSymbols);
		
		JCheckBox chckbxNumbers = new JCheckBox("Numbers");
		panel_1.add(chckbxNumbers);
		
		JCheckBox chckbxLowercaseLetters = new JCheckBox("Lowercase Letters");
		panel_1.add(chckbxLowercaseLetters);
		chckbxLowercaseLetters.setSelected(true);
		
		JCheckBox chckbxCapitalLetters = new JCheckBox("Capital Letters");
		panel_1.add(chckbxCapitalLetters);
		chckbxCapitalLetters.setSelected(true);
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		JLabel lblCustomCharacterSet = new JLabel("Custom Character Set");
		panel_2.add(lblCustomCharacterSet);
		
		txtenterCharacters = new JTextField();
		panel_2.add(txtenterCharacters);
		txtenterCharacters.setColumns(27);
		
		JCheckBox chckbxCustom = new JCheckBox("Use Custom");
		panel_2.add(chckbxCustom);
		
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(
						(!chckbxCustom.isSelected()) ? 
						new Password(
								(Integer) numchar.getValue(),
								chckbxSymbols.isSelected(),chckbxNumbers.isSelected(),
								chckbxLowercaseLetters.isSelected(),
								chckbxCapitalLetters.isSelected())
						.toString() : new Password(txtenterCharacters.getText(), (Integer) numchar.getValue()).toString()
						);
			}
		});
	}

}

class Password {
	private String lower = "abcdefghijklmnopqrstuvwxyz";
	private String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String nums  = "1234567890";
	private String sym   = "!@#$%^&*();:\'\",./?\\";
	private Random rand;
	private StringBuilder sb;
	
	String pass;
	Password(int len, boolean sym, boolean num, boolean lower, boolean upper) {
		try {
			pass = create(len,sym,num,lower,upper);
		} catch (Exception e ) {
			pass = "Please select some characters.";
		}
	}
	Password(String custom,int pwlen) {
		try {
			pass = create(custom,pwlen);
		} catch (Exception e ) {
			pass = "Please enter some characters.";
		}
	}
	private String create(int len, boolean sym, boolean num, boolean lower, boolean upper) {
		ArrayList<String> resources = new ArrayList<String>();
		if(sym)   resources.add(this.sym);
		if(num)   resources.add(this.nums);
		if(lower) resources.add(this.lower);
		if(upper) resources.add(this.upper);
		rand = new Random();
		sb = new StringBuilder();
		while (sb.length() < len) {
			int cat = (int) (rand.nextFloat() * resources.size());
			String temp = resources.get(cat);
			int index = (int) (rand.nextFloat() * temp.length());
			sb.append(temp.charAt(index));
		}
		return sb.toString();
	}
	private String create(String custom, int pwlen) {
		String temp = custom.replaceAll("\\s", "");
		int len = temp.length();
		rand = new Random();
		sb = new StringBuilder();
		while (sb.length() < pwlen) {
			int index = (int) (rand.nextFloat() * len);
			sb.append(temp.charAt(index));
		}
		return sb.toString();
	}
	public String toString() {
		return pass;
	}
}
