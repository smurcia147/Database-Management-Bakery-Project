import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class CustomerManagementPanel extends JPanel {

	private JTextField CustomerIDField;
	private JTextField NameField;
	private JTextField PhoneField;
	private JTable table;
	private JTextField AddresstextField;
	String currentEmployee;
	Connect conn;
	JButton ShowALLButton;
	
//IF problems cause too many connection make setconnection method and add the same connection from homeframe
	/**
	 * Create the panel.
	 * @throws ClassNotFoundException 
	 */
	public CustomerManagementPanel() throws ClassNotFoundException {
		
		conn = new Connect();
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer ID");
		lblNewLabel.setBounds(12, 69, 75, 13);
		add(lblNewLabel);
		
		CustomerIDField = new JTextField();
		CustomerIDField.setBounds(95, 66, 96, 19);
		add(CustomerIDField);
		CustomerIDField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(12, 92, 73, 13);
		add(lblNewLabel_1);
		
		NameField = new JTextField();
		NameField.setBounds(95, 89, 96, 19);
		add(NameField);
		NameField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Phone");
		lblNewLabel_2.setBounds(10, 115, 75, 13);
		add(lblNewLabel_2);
		
		PhoneField = new JTextField();
		PhoneField.setBounds(95, 112, 96, 19);
		add(PhoneField);
		PhoneField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setBounds(10, 138, 75, 13);
		add(lblNewLabel_3);
		
		 ShowALLButton = new JButton("Show All");
		ShowALLButton.setBounds(10, 161, 85, 21);
		add(ShowALLButton);
		ShowALLButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showAllCustomers();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	});
		
		
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
					conn.RemoveCustomer(CustomerIDField.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 192, 267, 98);
		add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		AddresstextField = new JTextField();
		AddresstextField.setBounds(95, 135, 96, 19);
		add(AddresstextField);
		AddresstextField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Customer Management");
		lblNewLabel_4.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
		lblNewLabel_4.setBackground(Color.GREEN);
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setBounds(20, 10, 252, 34);
		add(lblNewLabel_4);

	}
	
	public void setEmployeeID(String s) {
		this.currentEmployee = s;
	}
	
	public void showAllCustomers() throws SQLException {
		ResultSet results = conn.getAllCustomers();
		
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] { { "Customer_ID", "C_Name", "C_Phone", "C_Address", },

				}, new String[] { "-------------", "-------------", "-------------", "------------"});
		table.setModel(model);

		
			while (results.next()) {

				model.addRow(new Object[] { results.getString(1), results.getString(2),
						results.getString(3), results.getString(4)

//				

				}); 
	}
	
	}
	
	
	public void search() throws SQLException {
		if(this.CustomerIDField.getText().equals("")==false) {
		ResultSet results =	conn.searchByCustomerID(this.CustomerIDField.getText());
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] { { "Customer_ID", "C_Name", "C_Phone", "C_Address", },

				}, new String[] { "-------------", "-------------", "-------------","------------", });
		table.setModel(model);

		
			while (results.next()) {

				model.addRow(new Object[] { results.getString(1), results.getString(2),
						results.getString(3), results.getString(4)

//				

				}); 
	}	
		
		} else if (this.NameField.getText().equals("")==false) {
			ResultSet results = conn.searchbyName(this.NameField.getText());
			DefaultTableModel model = new DefaultTableModel(
					new Object[][] { { "Customer_ID", "C_Name", "C_Phone", "C_Address", },

					}, new String[] { "-------------", "-------------", "-------------", "-------------" });
			table.setModel(model);

			
				while (results.next()) {

					model.addRow(new Object[] { results.getString(1), results.getString(2),
							results.getString(3), results.getString(4)

//					

					}); 
		}
		} else if(this.PhoneField.getText().equals("")==false) {
			ResultSet results = conn.searchByPhone(this.PhoneField.getText());
			DefaultTableModel model = new DefaultTableModel(
					new Object[][] { { "Customer_ID", "C_Name", "C_Phone", "C_Address", },

					}, new String[] { "-------------", "-------------", "-------------","-------------" });
			table.setModel(model);

			
				while (results.next()) {

					model.addRow(new Object[] { results.getString(1), results.getString(2),
							results.getString(3), results.getString(4)

//					

					}); 
		}
		} else {
		ResultSet results =	conn.searchbyAddress(this.AddresstextField.getText());
			DefaultTableModel model = new DefaultTableModel(
					new Object[][] { { "Customer_ID", "C_Name", "C_Phone", "C_Address", },

					}, new String[] { "-------------", "-------------", "-------------","-------------" });
			table.setModel(model);

			
				while (results.next()) {

					model.addRow(new Object[] { results.getString(1), results.getString(2),
							results.getString(3), results.getString(4)

//					

					}); 
		}
		}
	}
}
