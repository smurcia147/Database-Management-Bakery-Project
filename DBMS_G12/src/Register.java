import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

public class Register extends JPanel {
	private JTextField NametextField;
	private JTextField PhonetextField;
	private JTextField AdresstextField;
	JButton RegisterButton;
	private JButton GoBackButton;
	Connect conn;

	/**
	 * Create the panel.
	 * @throws ClassNotFoundException 
	 */
	public Register() throws ClassNotFoundException {
		setLayout(null);
		
		conn = new Connect();
		JLabel NameLabel = new JLabel("Name");
		NameLabel.setBounds(123, 93, 45, 13);
		add(NameLabel);
		
		JLabel PhoneLabel_1 = new JLabel("Phone");
		PhoneLabel_1.setBounds(123, 145, 45, 13);
		add(PhoneLabel_1);
		
		JLabel AddressLabel = new JLabel("Address");
		AddressLabel.setHorizontalAlignment(SwingConstants.LEFT);
		AddressLabel.setBounds(123, 190, 45, 13);
		add(AddressLabel);
		
		NametextField = new JTextField();
		NametextField.setBounds(209, 90, 96, 19);
		add(NametextField);
		NametextField.setColumns(10);
		
		PhonetextField = new JTextField();
		PhonetextField.setBounds(209, 142, 96, 19);
		add(PhonetextField);
		PhonetextField.setColumns(10);
		
		AdresstextField = new JTextField();
		AdresstextField.setBounds(209, 187, 96, 19);
		add(AdresstextField);
		AdresstextField.setColumns(10);
		
		 RegisterButton = new JButton("Register");
		RegisterButton.setBounds(123, 235, 85, 21);
		add(RegisterButton);
		RegisterButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					conn.register(NametextField.getText(), AdresstextField.getText(), PhonetextField.getText());
					JOptionPane.showMessageDialog(null, "You registered!!!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		GoBackButton = new JButton("Go Back");
		GoBackButton.setBounds(218, 235, 85, 21);
		add(GoBackButton);
		
		

	}
	
	public JButton getBackButton() {
		return this.GoBackButton;
	}

}
