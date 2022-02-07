import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class CustomerInterface extends JPanel {

	JButton logOutButton;
	JButton MakeOrderButton_1;
	JButton CheckOrderButton_1;
	CheckOrder checkPanel;
	CheckCoupon couponPanel;
	MakeOrder makeOrderPanel;
	
	/**
	 * Create the panel.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public CustomerInterface() throws ClassNotFoundException, SQLException {
		setLayout(null);
		
		final CardLayout cl = new CardLayout();
		checkPanel = new CheckOrder();
		couponPanel = new CheckCoupon();
		makeOrderPanel = new MakeOrder();
		
		
		final JPanel OverallCustomerpanel = new JPanel(cl);
		OverallCustomerpanel.setBounds(124, 47, 485, 314);
		
		add(OverallCustomerpanel);
		
		OverallCustomerpanel.add(checkPanel,"2");
		
		OverallCustomerpanel.add(couponPanel,"3");
		OverallCustomerpanel.add(makeOrderPanel,"1");
		
		
		
		 logOutButton = new JButton("Log Out");
		 logOutButton.setBackground(Color.LIGHT_GRAY);
		 logOutButton.setForeground(Color.CYAN);
		logOutButton.setBounds(445, 16, 85, 21);
		add(logOutButton);
		
		JButton MakeOrderButton_1 = new JButton("Make Order");
		MakeOrderButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 9));
		MakeOrderButton_1.setBounds(0, 96, 114, 21);
		MakeOrderButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(OverallCustomerpanel, "1");
			}
		});
		add(MakeOrderButton_1);
		
		JButton CheckOrderButton_1 = new JButton("Check Order");
		CheckOrderButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 9));
		CheckOrderButton_1.setBounds(0, 144, 114, 21);
		CheckOrderButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(OverallCustomerpanel, "2");
			}
		});
		add(CheckOrderButton_1);
		
		JButton CheckCouponButton_1_1 = new JButton("Check Coupon");
		CheckCouponButton_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 9));
		CheckCouponButton_1_1.setBounds(0, 199, 114, 21);
		CheckCouponButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(OverallCustomerpanel, "3");
			}
		});
		add(CheckCouponButton_1_1);

	}

}
