import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.CardLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class EmployeePanel extends JPanel {
	JButton LogOutButton;
	OrderManagementPanel order;
	CustomerManagementPanel cust;
	/**
	 * Create the panel.
	 * @throws ClassNotFoundException 
	 */
	public EmployeePanel() throws ClassNotFoundException {
		setLayout(null);
		
		final CardLayout cl = new CardLayout();
		
		
		 order = new OrderManagementPanel();
		 cust = new CustomerManagementPanel();
		
		final JPanel Actionpanel = new JPanel(cl);
		Actionpanel.setBounds(148, 26, 522, 317);
		add(Actionpanel);
		
		Actionpanel.add(order,"1");
		Actionpanel.add(cust,"2");
		
		JButton CustomerManagementBut = new JButton("Customer Management");
		CustomerManagementBut.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 8));
		CustomerManagementBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cl.show(Actionpanel, "2");
			}
		});
		CustomerManagementBut.setBounds(0, 68, 139, 48);
		add(CustomerManagementBut);
		
		JButton OrderManagementButton = new JButton("Order Management");
		OrderManagementButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 8));
		OrderManagementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(Actionpanel, "1");
			}
		});
		OrderManagementButton.setBounds(0, 147, 139, 48);
		add(OrderManagementButton);
		
		 LogOutButton = new JButton("Log Out");
		 LogOutButton.setBackground(Color.LIGHT_GRAY);
		LogOutButton.setForeground(Color.CYAN);
		LogOutButton.setBounds(365, 0, 85, 21);
		add(LogOutButton);
		
		
		
	
		
		
		
		

	}
	
	public JButton getLogoutButton() {
		return this.LogOutButton;
	}
}
