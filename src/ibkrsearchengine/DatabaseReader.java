package ibkrsearchengine;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseReader {
	/*
	 * This will have the juhi's code you will have no input
	 * 
	 * you should return the hashmap for getdatabase() return list of attributes for
	 * getAttributes()
	 */
	public HashMap<String,Employee> getDatabase() {
		HashMap<String,Employee> employeeRecords = new HashMap<>();
		try {
			
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Empl","postgres","hardik");
			Statement stmt =(Statement) con.createStatement();
//			stmt.executeUpdate("USE Employee");
			
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery("Select * from Employee");
			
			while(rs.next()) {
				String id = rs.getString(1);
				String fname = rs.getString(2);
			 	String lname = "ibkr";
				String dept = rs.getString(3);
			 	String salary = rs.getString(4);
				
				Employee employeeObject = new Employee(id, fname, lname, dept, salary);
				employeeRecords.put(id, employeeObject);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return employeeRecords;
	}

	public ArrayList<String> getAttributes() {
		ArrayList<String> attributeList = new ArrayList<String>();
		attributeList.add("id");
		attributeList.add("fname");
		attributeList.add("lname");
		attributeList.add("dept");
		attributeList.add("salary");
		return attributeList;
	}
}
