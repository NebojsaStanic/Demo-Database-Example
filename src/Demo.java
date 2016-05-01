import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo {

	public static void main(String[] args) throws SQLException {
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Demo","root","");
			
			PreparedStatement stmt  = conn.prepareStatement("select * from employees where salary>? and department=?");
			
			stmt.setDouble(1, 80000);
			stmt.setString(2, "Legal");
			
			ResultSet  myRs = stmt.executeQuery();
			display(myRs);
			
			System.out.println("\nReuse the prepared statement: salary>25000 and department=HR");
			
			stmt.setDouble(1, 25000);
			stmt.setString(2, "hr");
			myRs = stmt.executeQuery();
			display(myRs);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	
	private  static void display(ResultSet rs) throws SQLException{
		while(rs.next()){
			String last_name = rs.getString("last_name");
			String first_name = rs.getString("first_name");
			double salary = rs.getDouble("salary");
			String department = rs.getString("department");
			
			System.out.printf("%s, %s, %.2f, %s\n",last_name,first_name,salary,department);
			
		}
	}

}
