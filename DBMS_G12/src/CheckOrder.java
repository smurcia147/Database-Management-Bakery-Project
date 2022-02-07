import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class CheckOrder extends JPanel {
	private JTextField OrderIDField;
	private JTextField OrderDateField;
	private JTable table;
	JComboBox comboBox;
	Connect connect;
String customerID;
	/**
	 * Create the panel.
	 * @throws ClassNotFoundException 
	 */
	public CheckOrder() throws ClassNotFoundException {
		
		connect = new Connect();
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Order ID");
		lblNewLabel.setBounds(10, 61, 62, 13);
		add(lblNewLabel);
		
		OrderIDField = new JTextField("'ORDER ID'");
		OrderIDField.setBounds(98, 58, 96, 19);
		add(OrderIDField);
		OrderIDField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Order Date");
		lblNewLabel_1.setBounds(10, 90, 74, 13);
		//add(lblNewLabel_1);
		
		OrderDateField = new JTextField("'DATE'");
		OrderDateField.setColumns(10);
		OrderDateField.setBounds(98, 87, 96, 19);
		//add(OrderDateField);
		
		JLabel lblNewLabel_2 = new JLabel("Status");
		lblNewLabel_2.setBounds(251, 90, 45, 13);
		//add(lblNewLabel_2);
		
		comboBox = new JComboBox();
		comboBox.setBounds(302, 85, 74, 23);
		//add(comboBox);
		comboBox.addItem("");
		comboBox.addItem("Delivered");
		comboBox.addItem("Preparing");
		comboBox.addItem("Delivering");
		
		JButton SearchButton = new JButton("Search");
		SearchButton.setBounds(280, 136, 85, 21);
		
		SearchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					search();
					OrderDateField.setText("'DATE'");
					OrderIDField.setText("'ORDER ID'");
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		add(SearchButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 167, 422, 104);
		add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton ShowAllButton = new JButton("Show All");
		ShowAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				ResultSet requestInfo = null;
				try {
					requestInfo = connect.getAllOrdersforC(customerID);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				
				DefaultTableModel model = new DefaultTableModel(
						new Object[][] { { "Order_ID", "Customer_ID", "Total", "Discount","Extra_Request" },

						}, new String[] { "-------------", "-------------", "-------------", "-------------"  });
				table.setModel(model);

				try {
					while (requestInfo.next()) {

						model.addRow(new Object[] { requestInfo.getString(1), requestInfo.getString(2),
								requestInfo.getString(3), requestInfo.getString(4),requestInfo.getString(5)

//						

						});

					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			

				});
		ShowAllButton.setBounds(109, 136, 85, 21);
		
		
		add(ShowAllButton);
		
		JLabel lblNewLabel_4 = new JLabel("Check Order");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
		lblNewLabel_4.setBackground(Color.GREEN);
		lblNewLabel_4.setBounds(101, 10, 252, 34);
		add(lblNewLabel_4);

	}
	
	public void search() throws SQLException {
		ResultSet requestInfo;
		
		
		if(OrderIDField.getText().equals("'ORDER ID'")== false) {
			
			requestInfo = connect.getOrderswithIDforC(this.OrderIDField.getText(),this.customerID);
			
			
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
			
			requestInfo= connect.getOrderswithStatusforC(comboBox.getSelectedItem().toString(),this.customerID);
			

		
			
			DefaultTableModel model = new DefaultTableModel(
					new Object[][] { { "Order_ID", "Customer_ID", "Total", "Status" },

					}, new String[] { "Order_ID", "Customer_ID", "Total", "Status"  });
			table.setModel(model);

			
				while (requestInfo.next()) {

					model.addRow(new Object[] { requestInfo.getString(1), requestInfo.getString(2),
							requestInfo.getString(3), requestInfo.getObject(4)

//					

					}); 
					
				}
			
			
			
		} else if(this.OrderDateField.getText() != "'DATE'") {
			
			requestInfo = connect.getOrderswithDateforC(this.OrderDateField.getText(),this.customerID);
			

			
			
			DefaultTableModel model = new DefaultTableModel(
					new Object[][] { { "Order_ID", "Customer_ID", "Total", "Discount", },

					}, new String[] { "-------------", "-------------", "-------------", "-------------", });
			table.setModel(model);

			
				while (requestInfo.next()) {

					model.addRow(new Object[] { requestInfo.getString(1), requestInfo.getString(2),
							requestInfo.getString(3), requestInfo.getString(4),requestInfo.getString(5)

//					

					}); 
					
				}
			
			
		} else {
			
		}
	}
	

////CHECK
	public void setCustomerID(String s) {
		this.customerID = s;
		
	}


}


