package context;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {		
	/* USE BELOW METHOD FOR YOUR DATABASE CONNECTION FOR BOTH SINGLE AND MULTILPE SQL SERVER INSTANCE(s) */
	/* DO NOT EDIT THE BELOW METHOD, YOU MUST USE ONLY THIS ONE FOR YOUR DATABASE CONNECTION */
	public Connection getConnection() throws Exception {
		String connectionUrl = (instance == null || instance.trim().isEmpty())
				? "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName
				: "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(connectionUrl, userID, password);
	}
	
	/* Insert your other code right after this comment */
	/* Change/update information of your database connection, DO NOT change name of instance variables in this class */
	private final String serverName = "localhost";
	private final String dbName = "ShoppingDB";
	private final String portNumber = "1433";
	// LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
	private final String instance = "";
	private final String userID = "sa";
	private final String password = "sa";
}
