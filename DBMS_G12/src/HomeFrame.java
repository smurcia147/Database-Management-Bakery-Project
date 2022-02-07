import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Color;

public class HomeFrame {

	
	
	String currentCustomerID;
	String currentEmployeeID;
	
	private JFrame frame;
	Register registerPanel = new Register();
	LoginPanel loginPanel = new LoginPanel();
	CustomerInterface customer;
	EmployeePanel ep = new EmployeePanel();
	private final JButton EmployeeLoginButton = new JButton("Employee Login");
	Connect conn = new Connect();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeFrame window = new HomeFrame();
					window.frame.setVisible(true);
					
				//	window.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public HomeFrame() throws ClassNotFoundException, SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	private void initialize() throws ClassNotFoundException, SQLException {
		
	customer=	new CustomerInterface();
		frame = new JFrame();
		frame.setBounds(100, 100, 911, 537);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final CardLayout cl = new CardLayout();
		
		final JPanel Overall = new JPanel(cl);
		
		frame.getContentPane().add(Overall);
		loginPanel.getLoginButton().setLocation(196, 172);
		loginPanel.getLoginButton().setSize(138, 29);
		loginPanel.getRegisterButton().setLocation(196, 277);
		loginPanel.getRegisterButton().setSize(138, 29);
		loginPanel.getLoginButton().setText("Customer LogIn");
		loginPanel.getRegisterButton().setText("Customer Register");
		loginPanel.getRegisterButton().setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 9));
		loginPanel.getLoginButton().setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 9));
		loginPanel.getRegisterButton().setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		loginPanel.getRegisterButton().setForeground(SystemColor.textText);
		
		
		Overall.add(loginPanel,"1");
		Overall.add(registerPanel,"2");
		Overall.add(customer,"3");
		Overall.add(ep,"4");
		cl.show(Overall, "1");
		
		
		
		
		
		registerPanel.getBackButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(Overall, "1");
			}
			
		});
		
		
		customer.logOutButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(Overall, "1");
			}
			
		});
		
		
		ep.getLogoutButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(Overall, "1");
			}
			
		});
		
		
		loginPanel.LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cl.show(Overall, "3");
				currentCustomerID = loginPanel.getCurrentUser();
				customer.couponPanel.setCustomerID(loginPanel.getIDField().getText());
				customer.checkPanel.setCustomerID(loginPanel.getIDField().getText());
				customer.couponPanel.setCustomerID(loginPanel.getIDField().getText());
				customer.makeOrderPanel.setCustomerID(loginPanel.getIDField().getText());
				JOptionPane.showMessageDialog(null, "NOTE: you will only be able to see the information related to the account you logged in");
			
			//	System.out.println(customer.couponPanel.customerID);
				
				
				
				
		 	}
		 });
		EmployeeLoginButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 9));
		EmployeeLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(Overall, "4");
				
				
				ep.order.setEmployeeID(loginPanel.getIDField().getText());
				ep.cust.setEmployeeID(loginPanel.getIDField().getText());
				customer.makeOrderPanel.setCustomerID(loginPanel.getIDField().getText());
				JOptionPane.showMessageDialog(null, "NOTE: As a manager you will see information of all orders and all customers");
			}
		});
		EmployeeLoginButton.setBounds(196, 223, 138, 29);
		
		loginPanel.add(EmployeeLoginButton);
		
		loginPanel.getRegisterButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cl.show(Overall, "2");
				
				
				registerPanel.RegisterButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JOptionPane.showMessageDialog(registerPanel, "Added Succesfully");
						cl.show(Overall, "1");
						
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}
					
				});
		 	}
		 });
	
	
	
	}

}
