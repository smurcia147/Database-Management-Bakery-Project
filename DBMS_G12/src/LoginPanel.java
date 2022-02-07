import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class LoginPanel extends JPanel {
	private JTextField IDtextField;
	JButton LoginButton;
	JButton RegisterButton;
	private JLabel lblNewLabel;
	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		setLayout(null);
		
		JLabel IDLabel = new JLabel("ID");
		IDLabel.setBounds(190, 106, 45, 13);
		add(IDLabel);
		
		IDtextField = new JTextField();
		IDtextField.setBounds(263, 103, 96, 19);
		add(IDtextField);
		IDtextField.setColumns(10);
		
		 LoginButton = new JButton("LogIn");
		
		LoginButton.setBounds(173, 169, 85, 21);
		add(LoginButton);
		
		 RegisterButton = new JButton("Register");
		RegisterButton.setBounds(173, 224, 85, 21);
		add(RegisterButton);
		
		lblNewLabel = new JLabel("TAIPEI BAKERY");
		lblNewLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(131, 33, 297, 63);
		add(lblNewLabel);

	}
	
	public JButton getLoginButton() {
		return this.LoginButton;
	}
	
	public JButton getRegisterButton() {
		return this.RegisterButton;
	}
	
	public String getCurrentUser() {
		return this.IDtextField.getText();
	}
	public JTextField getIDField() {
		return this.IDtextField;
	}

}
