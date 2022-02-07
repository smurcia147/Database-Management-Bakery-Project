import java.sql.Connection; import java.sql.DriverManager; import java.sql.SQLException; import java.util.Properties;

//Read more: https://javarevisited.blogspot.com/2016/09/javasqlsqlexception-no-suitable-driver-mysql-jdbc-localhost.html#ixzz6xOGinsqs
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

public class Mock {

	static final String ms_sql_conn_string = "jdbc:sqlserver://localhost;";
	static final String ms_sql_db = "database="+"Company;";
	static final String ms_sql_user = "user="+" root;";
	static final String ms_sql_pass= "password="+";";

	
	
	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		
		connection_sqlserver();
		

	
	}
	
	public static void connection_sqlserver() throws ClassNotFoundException {
	//sql server authentication
	String connectionUrl =ms_sql_conn_string +ms_sql_db +ms_sql_user +ms_sql_pass+ "encrypt=true;"+ "trustServerCertificate=false;"+ "loginTimeout=30;"+"integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
	//windows authentication
	//String connectionUrl ="jdbc:sqlserver://localhost;databaseName= Company;integratedSecurity=true;";
	
	
	try {
	//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	System.out.println("Driver loaded!");
	Connection connection = DriverManager.getConnection(connectionUrl);
	System.out.println("MSSQL Connection Success");
	Statement statement = (Statement) connection.createStatement();
	String selectSql = "SELECT top 10 * FROM [COMPANY].[dbo].[EMPLOYEE]";
	System.out.println("My student id is ");
	ResultSet resultSet = ((java.sql.Statement) statement).executeQuery(selectSql);
	//System.out.println(resultSet.next());
	while(resultSet.next()) {
	System.out.println(resultSet.getString("Fname"));
	}
	}
	// Handle any errors that may have occurred.
	catch (SQLException e) {
	e.printStackTrace();
	}

}}
