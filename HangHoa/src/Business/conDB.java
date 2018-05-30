/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author Viet Anh Phan
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Viet Anh Phan
 */
public class conDB {
    private static Connection connection=null;
    public static Connection getConnection() throws Exception{
        //load driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=HTKTT;user=sa;password=123";
        try {			
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			//throw new Exception("Can not connect to DB");
                        e.printStackTrace();
		}

        return connection;
    }
    public ResultSet executeQuery(String sql) throws Exception {
		ResultSet result = null;

		try {
			Connection connect = getConnection();

			PreparedStatement pre = connect .prepareStatement(sql);
			result = pre.executeQuery();
		} catch (SQLException ex) {
			System.out.println("Error" + ex.getMessage());
		}
		return result;
	}
    public int executenonquery(String sql) throws SQLException {
		int result = 0;
		try {
			Connection con = getConnection();
			PreparedStatement pre = con.prepareStatement(sql);
			result = pre.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage() + " query:" + sql);
                      
		}
		return result;
	}
    public void close() {
		if (connection != null) {
			try {
				if (!connection.isClosed()) {
					connection.close();
				}
			} catch (Exception e) {
				System.out.println("Error" + e.getMessage());
			}
		}                                          
		connection = null;
	}
}

