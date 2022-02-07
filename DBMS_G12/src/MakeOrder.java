import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.border.MatteBorder;

public class MakeOrder extends JPanel {
	private JTextField AmountTextField;
	private JTextField couponTextField;
	private JTextField NotesTextField;
	private JTable table;
	String customerID;
	Connect conn;
	JComboBox<Object> comboBox;
	ArrayList<String> product;
	ArrayList<String> quantity;
	ArrayList<String> notes;
	int productNumber;
	String OrderID;
	


	/**
	 * Create the panel.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	 MakeOrder() throws ClassNotFoundException, SQLException {
		
		 product = new ArrayList<String>();
		 quantity = new ArrayList<String>();
		 notes = new ArrayList<String>();
		 productNumber = 1;
		 
		 conn = new Connect();
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product");
		lblNewLabel.setBounds(25, 73, 45, 13);
		add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setBounds(90, 68, 86, 23);
		add(comboBox);
		
		JLabel Amountlabel = new JLabel("Amount");
		Amountlabel.setHorizontalAlignment(SwingConstants.LEFT);
		Amountlabel.setBounds(25, 114, 45, 13);
		add(Amountlabel);
		
		AmountTextField = new JTextField();
		AmountTextField.setBounds(91, 111, 85, 19);
		add(AmountTextField);
		AmountTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Coupon ID");
		lblNewLabel_1.setBounds(237, 73, 67, 13);
		add(lblNewLabel_1);
		
		couponTextField = new JTextField();
		couponTextField.setBounds(315, 70, 96, 19);
		add(couponTextField);
		couponTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Notes");
		lblNewLabel_2.setBounds(237, 114, 45, 13);
		add(lblNewLabel_2);
		
		NotesTextField = new JTextField();
		NotesTextField.setBounds(315, 114, 96, 19);
		add(NotesTextField);
		NotesTextField.setColumns(10);
		
		table = new JTable();
		table.setBounds(80, 207, 307, 83);
		add(table);
		
		JButton addButton = new JButton("Add");
		addButton.setBounds(91, 166, 85, 21);
		add(addButton);
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					addProduct();
					JOptionPane.showMessageDialog(null,"Product Added! When you click purchase it will be added to the system along with the other products");
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		JButton PurchaseButton = new JButton("Purchase");
		PurchaseButton.setBounds(287, 166, 85, 21);
		add(PurchaseButton);
		
		PurchaseButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Purchase();
					JOptionPane.showMessageDialog(null,"Thank you for your purchase");
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		JLabel lblNewLabel_4 = new JLabel("Make Order");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
		lblNewLabel_4.setBackground(Color.GREEN);
		lblNewLabel_4.setBounds(88, 10, 252, 34);
		add(lblNewLabel_4);

		
		ResultSet products = conn.getAllProducts();
		
		while(products.next()) {
			comboBox.addItem(products.getObject(1));
		}
	}
	
	
	///CHECK
	public void setCustomerID(String s) {
		this.customerID = s;
	}
	
	public void addProductstoCombo() throws SQLException {
		ResultSet r = conn.getAllProducts();
		while(r.next()) {
			comboBox.addItem(r.getObject(1).toString());
		}
		
		
		
	}
	///Methods to buy
	public void Purchase() throws NumberFormatException, SQLException {
		int total = 0;
		
		if(this.couponTextField.getText().equals("")==false) {
		String coupondiscount = conn.getCouponDiscount(this.couponTextField.getText()); 
		
		
		for(int i = 0; i < product.size(); i++) {
			OrderID=conn.getCurrentOrderID();
			conn.addToOrderDetail(OrderID);
			//Still need to update the total price
			total += Integer.parseInt(quantity.get(i)) * conn.getProductPrice(product.get(i))  ;
			conn.UpdateOrderTotal(OrderID, total, coupondiscount, this.NotesTextField.getText());
			}}
		
		else {
			for(int i = 0; i < product.size(); i++) {
				
				OrderID=conn.getCurrentOrderID();
				conn.addToOrderDetail(OrderID);
				//Still need to update the total price
				total += Integer.parseInt(quantity.get(i)) * conn.getProductPrice(product.get(i))  ;
				
			
				conn.UpdateOrderTotal(OrderID, total, "0", this.NotesTextField.getText());}
			
			
		}
		
		
	
	
		
		
	}
	
	public void addProduct() throws SQLException {
		if(productNumber == 1) {
			conn.createOrder(this.customerID);
			OrderID = conn.getCurrentOrderID();
		//	product.add( "Get");
			
		}
		
		if( this.AmountTextField.getText().equals("") ==  false && comboBox.getSelectedItem().toString().equals("") == false) {
			//product.add(1, "Get");
			product.add( this.comboBox.getSelectedItem().toString());
			quantity.add( this.AmountTextField.getText());
			
			
			productNumber ++;
		}
	}


	
	
}
