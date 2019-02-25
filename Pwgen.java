package pwgen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
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
		
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(new Password((Integer) numchar.getValue(),chckbxSymbols.isSelected(),chckbxNumbers.isSelected(),chckbxLowercaseLetters.isSelected(),chckbxCapitalLetters.isSelected()).toString() );
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
		pass = create(len,sym,num,lower,upper);
	}
	private String create(int len, boolean sym, boolean num, boolean lower, boolean upper) {
		ArrayList<String> resources = new ArrayList<String>();
		if(sym)  resources.add(this.sym);
		if(num)  resources.add(this.nums);
		if(lower)resources.add(this.lower);
		if(upper)resources.add(this.upper);
		String pw = "";
		rand = new Random();
		sb = new StringBuilder();
		while (pw.length() < len) {
			int cat = (int) (rand.nextFloat() * resources.size());
			//System.out.println(cat);
			char[] temp = resources.get(cat).toCharArray();
			int index = (int) (rand.nextFloat() * temp.length);
			pw += temp[index];
		}
		return pw;
	}
	public String toString() {
		return pass;
	}
}
