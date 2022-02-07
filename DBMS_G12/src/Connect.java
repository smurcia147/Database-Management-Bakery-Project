import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Statement;

public class Connect {
	
	public static Connection connection;
	String customerID;
	String employeeID;
	
	
	
	//IF problems cause too many connection make setconnection method and add the same connection from homeframe
	
	public Connect() throws ClassNotFoundException {
		String connectionUrl ="jdbc:sqlserver://localhost;databaseName=FINAL2;integratedSecurity=true;loginTimeout=30;";
		//CHANGE DATABASE NAME HERE!!! 
		
		
		try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		System.out.println("Driver loaded!");
		 connection = DriverManager.getConnection(connectionUrl);
		System.out.println("MSSQL Connection Success");
		PreparedStatement statement =  connection.prepareStatement("SELECT * FROM dbo.Coupon");
		
		System.out.println(statement);
		ResultSet r = statement.executeQuery();
		 
		while(r.next()) {
		System.out.println(r.getString(1)+r.getString(2)+r.getString(3)+r.getString(4));
		}
		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
		e.printStackTrace();
		}}
		
	public Connection getConnection() {
		return this.connection;
	}
	
	public ResultSet getAllOrders() throws SQLException {
		//user = this.user;
		ResultSet result = null;

		String sql = "SELECT * FROM [order]";
		PreparedStatement stat = connection.prepareStatement(sql);
		

		result =  stat.executeQuery();
	

		return result;
	}
	
	public void setcurrentCustomerID(String s) {
		this.customerID= s;
	}
	
	public void setcurrentEmployeeID(String s) {
		this.employeeID=s;
	}
	
	
	//FOR CHECK ORDER PAGE
	
	public ResultSet getOrderswithStatus(String s) throws SQLException {
		ResultSet result = null;

		
		String sql = "SELECT * FROM [order] JOIN ORDER_DETAIL ON [order].Order_ID= ORDER_DETAIL.Order_ID WHERE Order_DETAIL.Status = ?";
		
//		String sql = "SELECT * FROM [order] JOIN ORDER_DETAIL ON [order].Order_ID= ORDER_DETAIL.Order_ID WHERE Order_DETAIL.Status = ?";
		PreparedStatement stat = connection.prepareStatement(sql);
		stat.setString(1, s);

		result =  stat.executeQuery();
		
		return result;
	
	}
	
	public ResultSet getOrderswithID(String s) throws SQLException {
		ResultSet result = null;

		
		
		String sql = "SELECT * FROM [order] JOIN ORDER_DETAIL ON [order].Order_ID= ORDER_DETAIL.Order_ID WHERE Order_DETAIL.Order_ID = ? ";
//		String sql = "SELECT * FROM [order] JOIN ORDER_DETAIL ON [order].Order_ID= ORDER_DETAIL.Order_ID WHERE Order_DETAIL.Order_ID = ? AND  Customer_ID = ? ";
		PreparedStatement stat = connection.prepareStatement(sql);
		stat.setString(1, s);
		//stat.setString(2, this.customerID);
		

		result =  stat.executeQuery();
		
		return result;
	
	}
	
	public ResultSet getOrderswithDate(String s) throws SQLException {
		ResultSet result = null;

		String sql = "SELECT * FROM [order] JOIN ORDER_DETAIL ON [order].Order_ID= ORDER_DETAIL.Order_ID WHERE Order_DETAIL.Date_Order = ? ";
		PreparedStatement stat = connection.prepareStatement(sql);
		
		stat.setString(1, s);

		result =  stat.executeQuery();
		
		return result;
	
	}
	
	
	
	///ORDERS BUT FOR CUSTOMER
	
	public ResultSet getAllOrdersforC(String c) throws SQLException {
		//user = this.user;
		ResultSet result = null;

		String sql = "SELECT * FROM [order] WHERE [order].Customer_ID = ? ";
		
		PreparedStatement stat = connection.prepareStatement(sql);
		stat.setString(1, c);

		result =  stat.executeQuery();
	

		return result;
	}
	
	public ResultSet getOrderswithStatusforC(String s,String CID) throws SQLException {
		ResultSet result = null;

		
		String sql = "\r\n"
				+ " SELECT [ORDER].Order_ID,Customer_ID, Total, [Status] FROM [order] JOIN ORDER_DETAIL ON [order].Order_ID= ORDER_DETAIL.Order_ID WHERE Order_DETAIL.Status = ? AND [order].Customer_ID = ?";
		
//		String sql = "SELECT * FROM [order] JOIN ORDER_DETAIL ON [order].Order_ID= ORDER_DETAIL.Order_ID WHERE Order_DETAIL.Status = ?";
		PreparedStatement stat = connection.prepareStatement(sql);
		stat.setString(1, s);
		stat.setString(2, CID );

		result =  stat.executeQuery();
		
		return result;
	
	}
	
	public ResultSet getOrderswithIDforC(String s,String CID) throws SQLException {
		ResultSet result = null;

		
		
	//	String sql = "SELECT * FROM [order] JOIN ORDER_DETAIL ON [order].Order_ID= ORDER_DETAIL.Order_ID WHERE Order_DETAIL.Order_ID = ? AND [order].Customer_ID = ?";
	
		System.out.println(this.customerID);
		String sql = "SELECT * FROM [order] JOIN ORDER_DETAIL ON [order].Order_ID= ORDER_DETAIL.Order_ID WHERE Order_DETAIL.Order_ID = ? AND  [order].Customer_ID = ? ";
		PreparedStatement stat = connection.prepareStatement(sql);
		stat.setString(1, s);
		stat.setString(2, CID);
		

		result =  stat.executeQuery();
		
		return result;
	
	}
	
	public ResultSet getOrderswithDateforC(String s,String CID) throws SQLException {
		ResultSet result = null;

		String sql = "SELECT * FROM [order] JOIN ORDER_DETAIL ON [order].Order_ID= ORDER_DETAIL.Order_ID WHERE Order_DETAIL.Date_Order = ? AND [order].Customer_ID = ? ";
		PreparedStatement stat = connection.prepareStatement(sql);
		
		stat.setString(1, s);
		stat.setString(2, CID);

		result =  stat.executeQuery();
		
		return result;
	
	}
	
	
	//END ORDERS FOR CUST
	
public void	setCustomerID(String s){
		this.customerID = s;
	}

public String getCustomerID() {
	return this.customerID;
}
	
	//FOR MAKE ORDER
public ResultSet getAllProducts() throws SQLException {
	String sql = "SELECT R_Name FROM dbo.RECIPE";
	PreparedStatement stat = connection.prepareStatement(sql);
	return stat.executeQuery();
}
//END MAKE ORDER


//FOR CUSTOMER MANAGEMTN


public ResultSet getAllCustomers() throws SQLException {
	
	String sql = "SELECT  * FROM dbo.CUSTOMER";
	PreparedStatement stat = connection.prepareStatement(sql);
	return stat.executeQuery();
	
	
	
}

public void RemoveCustomer(String id) throws SQLException {
	String sql = "DELETE FROM [CUSTOMER] WHERE Customer_ID = ? ";
	PreparedStatement stat = connection.prepareStatement(sql);
	stat.setString(1, id);
	stat.execute();
	
	JOptionPane.showMessageDialog(null, "Removed Customer with id  " + id );
	
	
}


public ResultSet searchByCustomerID(String customer) throws SQLException {

	String sql = "SELECT  * FROM dbo.CUSTOMER WHERE Customer_ID = ?";
	PreparedStatement stat = connection.prepareStatement(sql);
	stat.setString(1, customer);
	return stat.executeQuery();
	
}

public ResultSet searchByPhone(String number) throws SQLException {
	String sql = "SELECT * from [Customer] WHERE [CUSTOMER].C_Phone = ?";
	PreparedStatement stat = connection.prepareStatement(sql);
	stat.setString(1, number);
	return stat.executeQuery();
}

public ResultSet searchbyName(String name) throws SQLException {
	String sql = "SELECT  * FROM dbo.CUSTOMER WHERE C_Name = ?";
	PreparedStatement stat = connection.prepareStatement(sql);
	stat.setString(1, name);
	return stat.executeQuery();
}

public ResultSet searchbyAddress(String address) throws SQLException {
	
	//This doesnt work????
	
	String sql = "SELECT * from [Customer] WHERE [CUSTOMER].C_Address LIKE '/% ? /%'";
	PreparedStatement stat = connection.prepareStatement(sql);
	stat.setString(1, address);
	return stat.executeQuery();
}

public void removeCustomer(String customerID) throws SQLException {
	String sql = "DELETE FROM dbo.CUSTOMER WHERE Customer_ID = ?";
	PreparedStatement stat = connection.prepareStatement(sql);
	stat.setString(1, customerID);
	stat.execute();
	
}

//END CUSTOMER MANAGMENT


//ORDER MANAGEMETN

public ResultSet getAllOrdersforManagement() throws SQLException {
	String sql = "SELECT  * FROM [ORDER] JOIN [ORDER_DETAIL] ON [ORDER].Order_ID = [ORDER_DETAIL].Order_ID";
	PreparedStatement stat = connection.prepareStatement(sql);
	return stat.executeQuery();
}

public ResultSet searchOrderbyCustomerID(String id) throws SQLException {
	String sql = "SELECT  * FROM [ORDER] JOIN [ORDER_DETAIL] ON [ORDER].Order_ID = [ORDER_DETAIL].Order_ID WHERE [order].Customer_ID = ?";
	PreparedStatement stat = connection.prepareStatement(sql);
	stat.setString(1, id);
	return stat.executeQuery();
}




public void removeOrder(String OrderID) throws SQLException {
	String sql = "DELETE FROM [ORDER] WHERE [Order_ID] = ?";
	PreparedStatement stat = connection.prepareStatement(sql);
	stat.setString(1, OrderID);
	stat.execute();
	JOptionPane.showMessageDialog(null, "Order Deleted");
}


//To register

public void register(String name, String address, String phone) throws SQLException {
	String sql = "INSERT INTO [Customer](C_Name,C_Phone,C_Address) VALUES(?,?,?)";
	PreparedStatement stat = connection.prepareStatement(sql);
	stat.setString(1, name);
	stat.setString(2, phone);
	stat.setString(3, address);
	stat.execute();
	
}


//For MAKE ORDER


public void createOrder(String customerID) throws SQLException {
	
	String sql = "INSERT INTO [ORDER](Customer_ID,Total,Discount,Extra_Request) VALUES(?,0,0,0)";
	PreparedStatement stat = connection.prepareStatement(sql);
	stat.setString(1, customerID);
	stat.execute();
	
	
}

public void addToProduct(String OrderID, String Product, int Quantity) throws SQLException {
	
	
	for(int i = 0; i < Quantity ; i++) {
	String sql = "INSERT INTO dbo.ORDER_PRODUCT_LIST VALUES(?,?)";
	PreparedStatement stat = connection.prepareStatement(sql);
	stat.setString(1,OrderID );
	if(Product=="honey cake")
		stat.setString(2, "1000000001");
	else 
		stat.setString(2, "1000000002");
	
	stat.execute();
	}
	
}

public String getCurrentOrderID() throws SQLException {
	
			String sql = "SELECT max([ORDER].Order_ID) FROM [ORDER]";
	PreparedStatement stat = connection.prepareStatement(sql);	
	ResultSet result = stat.executeQuery();
	result.next();
	
	System.out.println("OrderID"+result.getObject(1).toString());
	
	return result.getObject(1).toString();
	
	
			
}

public int getProductPrice(String productName) throws SQLException {
	String sql = "SELECT [PRODUCT].Price FROM [PRODUCT] JOIN [RECIPE] ON [Product].Recipe_ID =  [Recipe].Recipe_ID WHERE [RECIPE].R_Name = ?";
	PreparedStatement stat = connection.prepareStatement(sql);	
	stat.setString(1, productName);
	ResultSet result = stat.executeQuery();
	result.next();
	
	return Integer.parseInt(result.getObject(1).toString());
}

public void UpdateOrderTotal(String OrderID,int total, String discount, String ExtraRequest) throws SQLException {
	String sql = "UPDATE [ORDER] set Total = ?, Discount = ? , Extra_Request = ? WHERE Order_ID = ?";
	PreparedStatement stat = connection.prepareStatement(sql);	
	stat.setInt(1, total);
	stat.setString(2, discount);
	 stat.setString(3, ExtraRequest);
	 stat.setString(4, OrderID);
	 stat.execute();
}

public String getCouponDiscount(String CouponID) throws SQLException {
	String sql = "SELECT [COUPON].Price_Cut FROM COUPON WHERE Coupon_ID = ?";
	PreparedStatement stat = connection.prepareStatement(sql);	
	stat.setString(1, CouponID);
	ResultSet result = stat.executeQuery();
	result.next();
	
	if(result.getObject(1).toString() != null) {
	return result.getObject(1).toString();}
	else {
		return "0";
	}
}
public void addToOrderDetail(String OrderID) throws SQLException {
	
	
	String sql = "INSERT INTO dbo.ORDER_DETAIL VALUES(?,?,?,?,?)";
	PreparedStatement stat = connection.prepareStatement(sql);
	stat.setString(1,OrderID);
	stat.setString(2, "2121-05-31");
	stat.setString(3, "2021-06-01");
	stat.setString(4, "2021-06-01");
	stat.setString(5, "Delivering");
	
	stat.execute();
	}
public void addCouponToCustomer(String CustomerID,String CouponID) throws SQLException{
	
	String sql="ㄑ";
	PreparedStatement stat=connection.prepareStatement(sql);
	stat.setString(1, customerID);
	stat.setString(2, CouponID);
}

}

