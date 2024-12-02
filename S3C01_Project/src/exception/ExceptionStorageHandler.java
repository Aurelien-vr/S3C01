package exception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExceptionStorageHandler {
	
	public static void LogException(Exception exception, Connection connection) {
		PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.exception_log(Exception_type,Sql_error_code,Exception_message,Error_code) VALUES (?,?,?,?);";
		
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, exception.getClass().getCanonicalName());
			statement.setString(3, exception.getMessage());
			
			if(exception instanceof SQLException) {
				SQLException sqlException = (SQLException) exception;
				statement.setString(2, sqlException.getSQLState());
				statement.setInt(4, sqlException.getErrorCode());
			}else {
				statement.setString(2, null);
				statement.setInt(4, 0);
			}
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
