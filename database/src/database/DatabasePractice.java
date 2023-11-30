package database;

import java.sql.*;

public class DatabasePractice {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		// Establishing the connection
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		}catch(ClassNotFoundException ex) {
			System.out.println("Problem in loading the driver");
			ex.printStackTrace();
		}
		
		try {
			String dbName = "Employee.accdb";
			String dbURL = "jdbc:ucanaccess://" + dbName;
			
			
			// Create and get connection through DriverManager class
			connection = DriverManager.getConnection(dbURL);
			// Creating JDBC statement
			statement = connection.createStatement();
			
			// ------------------------ Inserting a record ------------------------------
			
			String insertQuery = "INSERT INTO Employee (Emp_name,Salary) " +
								"VALUES ('Fernanda', 9000)";
			statement.executeUpdate(insertQuery);
			
			// ------------------------ Updating a record -------------------------------
			String updateQuery = "UPDATE Employee SET Salary = 12000 "
								+ "WHERE Emp_name = 'Juan'";
			statement.executeUpdate(updateQuery);
			
			// ------------------------ Deleting a record -------------------------------
			String deleteQuery = "DELETE FROM Employee WHERE Emp_name = 'Maria'";
			statement.executeUpdate(deleteQuery);
			
			// Executing SQL and retrieve data into ResultSet	
			resultSet = statement.executeQuery("SELECT * FROM EMPLOYEE");
			while(resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				Double salary = resultSet.getDouble(3);
				System.out.println("Employee #" + id + ": " + name + ", " + salary);
			}	
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			
			// Closing database connection
			try {
				if(connection != null) {
					// Clean up resources after processing
					resultSet.close();
					statement.close();
					
					// Closing connection
					connection.close();
				}
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
	}

}
