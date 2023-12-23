package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
	private static String url = "jdbc:mysql://localhost:3306/agencia";
	
	
	
    public static Connection conexao = null;
    public static Connection getConnection() {
    	if(conexao == null) {
    		
    		
    		try {
    		    Properties prop = new Properties();
    		    prop.setProperty("useSSL","false");
    			prop.setProperty("user","root");
				prop.setProperty("password","12345");
				conexao = DriverManager.getConnection(url, prop);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}
    		return conexao;
    		
    	}else {
    		return conexao;
    	}
    	
    }
}


