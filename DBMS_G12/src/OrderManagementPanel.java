import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class OrderManagementPanel extends JPanel {
	private JTextField OrderIDField;
	private JTextField OrderDateField;
	private JTextField CustomerIDField;
	private JTable table;
	String currentEmployee;
	Connect conn;
	JComboBox comboBox;
	/**
	 * Create the panel.
	 * @throws ClassNotFoundException 
	 */
	public OrderManagementPanel() throws ClassNotFoundException {
		conn = new Connect();
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("OrderID");
		lblNewLabel.setBounds(10, 71, 75, 13);
		add(lblNewLabel);
		
		OrderIDField = new JTextField("'ORDER ID'");
		OrderIDField.setBounds(95, 68, 96, 19);
		add(OrderIDField);
		OrderIDField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel(" Date");
		lblNewLabel_1.setBounds(12, 94, 73, 13);
		add(lblNewLabel_1);
		
		OrderDateField = new JTextField("'DATE'");
		OrderDateField.setBounds(95, 91, 96, 19);
		add(OrderDateField);
		OrderDateField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Customer");
		lblNewLabel_2.setBounds(10, 115, 75, 13);
		add(lblNewLabel_2);
		
		CustomerIDField = new JTextField("ID");
		CustomerIDField.setBounds(95, 112, 96, 19);
		add(CustomerIDField);
		CustomerIDField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Status");
		lblNewLabel_3.setBounds(10, 138, 75, 13);
		add(lblNewLabel_3);
		
		comboBox = new JComboBox();
		comboBox.setBounds(95, 133, 96, 23);
		add(comboBox);
		
		JButton ShowALLButton = new JButton("Show All");
		ShowALLButton.setBounds(10, 161, 85, 21);
		add(ShowALLButton);
		
		ShowALLButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showALLOrders();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(106, 161, 85, 21);
		add(searchButton);
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					search();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		JButton RemoveButton = new JButton("Remove");
		RemoveButton.setBounds(201, 161, 85, 21);
		add(RemoveButton);
		RemoveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					conn.removeOrder(OrderIDField.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 192, 490, 110);
		add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_4 = new JLabel("Order Management");
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
		lblNewLabel_4.setBackground(Color.GREEN);
		lblNewLabel_4.setBounds(12, 10, 252, 34);
		add(lblNewLabel_4);
		
		comboBox.addItem("");
		comboBox.addItem("Delivered");
		comboBox.addItem("Preparing");
		comboBox.addItem("Delivering");

	}
	
	
	
	public void setEmployeeID(String s) {
		this.currentEmployee = s;
	}
	
	public void showALLOrders() throws SQLException {
	ResultSet results = conn.getAllOrdersforManagement();
	JOptionPane.showMessageDialog(null, "This are all the order! If you wish to learn more about each individual order use the search method.");
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] { { "Order_ID", "C_ID", "Total", "Discount","Extra_Request","Date_Order","Date_Arrived","Status" },

				}, new String[] { "-------------", "-------------", "-------------","-----------","------------","--------","---------","---------" });
		table.setModel(model);

		
			while (results.next()) {

				model.addRow(new Object[] { results.getString(1), results.getString(2),
						results.getString(3), results.getString(4), results.getString("Extra_Request"),results.getString(7),results.getString(8),results.getObject("Status")

//				

				}); }
	}
	
	
	public void search() throws SQLException {
		ResultSet requestInfo;
		
		
		if(OrderIDField.getText().equals("'ORDER ID'")== false) {
			
			requestInfo = conn.getOrderswithID(this.OrderIDField.getText());
			
			
			
			DefaultTableModel model = new DefaultTableModel(
					new Object[][] { { "Order_ID", "Customer_ID", "Total", "Discount","Extra_Request" },

					}, new String[] { "-------------", "-------------", "-------------", "-------------"  });
			table.setModel(model);

			
				while (requestInfo.next()) {

					model.addRow(new Object[] { requestInfo.getString(1), requestInfo.getString(2),
							requestInfo.getString(3), requestInfo.getString(4),requestInfo.getString(5)

//					

					}); 
					
				}
				}
			
			
			
			
		 else if(comboBox.getSelectedItem()!= "") {
			
			requestInfo= conn.getOrderswithStatus("Delivering");
			

			
			
			DefaultTableModel model = new DefaultTableModel(
					new Object[][] { { "Order_ID", "Customer_ID", "Total", "Discount","Extra_Request" },

					}, new String[] { "Order_ID", "Customer_ID", "Total", "Discount","Extra_Request" });
			table.setModel(model);

			
				while (requestInfo.next()) {

					model.addRow(new Object[] { requestInfo.getString(1), requestInfo.getString(2),
							requestInfo.getString(3), requestInfo.getString(4),requestInfo.getString(5)

//					

					}); 
					
				}
			
			
			
		} else if(this.OrderDateField.getText().equals("'DATE'")==false) {
			
			requestInfo = conn.getOrderswithDate(this.OrderDateField.getText());
			

			
			
			DefaultTableModel model = new DefaultTableModel(
					new Object[][] { { "Order_ID", "Customer_ID", "Total", "Discount", },

					}, new String[] { "Order_ID", "Customer_ID", "Total", "Discount", });
			table.setModel(model);

			
				while (requestInfo.next()) {

					model.addRow(new Object[] { requestInfo.getString(1), requestInfo.getString(2),
							requestInfo.getString(3), requestInfo.getString(4),requestInfo.getString(5)

//					

					}); 
					
				}
			
			
		} else if (this.CustomerIDField.getText().equals("ID" )== false) {
requestInfo = conn.searchByCustomerID(this.CustomerIDField.getText());
			

			
			
			DefaultTableModel model = new DefaultTableModel(
					new Object[][] { { "Order_ID", "Customer_ID", "Total", "Discount","Date" },

					}, new String[] { "Order_ID", "Customer_ID", "Total", "Discount","Date" });
			table.setModel(model);

			
				while (requestInfo.next()) {

					model.addRow(new Object[] { requestInfo.getString(1), requestInfo.getString(2),requestInfo.getString(3), requestInfo.getString(4)

//					

					}); 
		}}
	
	this.CustomerIDField.setText("ID");
	this.OrderDateField.setText("'DATE'");
	this.OrderIDField.setText("'ORDER ID'");
	
	}
	
	
	
}
