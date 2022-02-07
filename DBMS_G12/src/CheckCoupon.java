import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckCoupon extends JPanel {
	private JTextField CouponField;
	private JTable table;
	Connect exec;
	private JTable CouponTable;
	Connection connect;
	String customerID;

	/**
	 * Create the panel.
	 * @throws ClassNotFoundException 
	 */
	public CheckCoupon() throws ClassNotFoundException {
		exec= new Connect();
		connect = exec.getConnection();
		
		System.out.println(exec.customerID);
		
		
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CouponID");
		lblNewLabel.setBounds(39, 78, 85, 34);
		add(lblNewLabel);
		
		CouponField = new JTextField();
		CouponField.setBounds(125, 86, 96, 19);
		add(CouponField);
		CouponField.setColumns(10);
		
		JButton ShowAllButton = new JButton("Show All");
		ShowAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					checkCouponsInfo(customerID);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		ShowAllButton.setBounds(39, 123, 85, 21);
		add(ShowAllButton);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBounds(258, 123, 85, 21);
		add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					searchCoupon(CouponField.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		table = new JTable();
		table.setBounds(39, 170, 364, 108);
		add(table);
		
		JLabel lblNewLabel_4 = new JLabel("Check Coupon");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
		lblNewLabel_4.setBackground(Color.GREEN);
		lblNewLabel_4.setBounds(91, 10, 252, 34);
		add(lblNewLabel_4);
		
		CouponTable = new JTable();
		CouponTable.setBounds(39, 170, 364, 108);
		add(CouponTable);
		
		

	}
	
	
	public void checkCouponsInfo(String id) throws SQLException {
		id = this.customerID;
		
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] { { "Coupon_ID", "Price_Cut", "Percentage_Cut", "Expire_Date" },

				}, new String[] { "-------------", "-------------", "-------------", "-------------"  });
		table.setModel(model);
		
		
		///JUST TO CHECK ID
		this.printuser();
		
		
//		Statement stat = (Statement) connect.createStatement();
//		Object x= stat.execute();
	PreparedStatement stat = connect.prepareStatement("SELECT * FROM dbo.coupon JOIN dbo.COUPON_POSSESSION ON dbo.COUPON.Coupon_ID = dbo.COUPON_POSSESSION.Coupon_ID WHERE dbo.COUPON_POSSESSION.Customer_ID = ?");
	stat.setString(1, id);
	ResultSet requestInfo = stat.executeQuery();
		
		while (requestInfo.next()) {

			model.addRow(new Object[] { requestInfo.getString(1), requestInfo.getString(2),
					requestInfo.getString(3), requestInfo.getString(4),

//				

			});

		}
}

public void searchCoupon(String ID) throws SQLException {
	String sql = "SELECT * FROM dbo.coupon where Coupon_ID= ? ";
	PreparedStatement stat = connect.prepareStatement(sql);
	stat.setString(1, CouponField.getText());
	ResultSet results = stat.executeQuery();
	
	DefaultTableModel model = new DefaultTableModel(
			new Object[][] { { "Coupon_ID", "Price_Cut", "Percentage_Cut", "Expire_Date" },

			}, new String[] { "Coupon_ID", "Price_Cut", "Percentage_Cut", "Expire_Date"  });
	table.setModel(model);
	
	while(results.next()) {
		model.addRow(new Object[] { results.getString(1), results.getString(2),
				results.getString(3), results.getString(4),

//			

		});
		
		
	}
	
}
//////JUST TO CHECK THE CUSTOMER ID
public void setCustomerID(String s) {
	this.customerID = s;
}

public void printuser() {
	System.out.println(this.customerID);
}

}
