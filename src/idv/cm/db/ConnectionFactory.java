package idv.cm.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	
	private String diriverClassName = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/db_morgan?"+"autoReconnect=true&useSSL=false"; 
	private String userName = "root";
	private String password = "1234";
	
	private static ConnectionFactory connectionFactory=null;
	
	private ConnectionFactory() {
		 try {
	           Class.forName(diriverClassName); 
	       } catch (java.lang.ClassNotFoundException e) {
	           System.err.println(e.getMessage());
	       }
	}
	public static ConnectionFactory getInstance() {
		if(connectionFactory==null) {
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}
	
	public Connection getConnection() throws SQLException {
//		Properties prop = new Properties();
//		try {
//			prop.load(new FileInputStream("D:/eclipse-ee-workspace/querydb/build/classes/idv/cm/db/JDBC.properties"));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return DriverManager.getConnection(url, prop);
		return DriverManager.getConnection(url, userName, password);
	}
	

       
}
