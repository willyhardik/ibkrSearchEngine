package ibkrsearchengine;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseReader {
	public HashMap<String,Employee> employeeRecords = new HashMap<>();
	public HashMap<String,Person> personRecords = new HashMap<>();
	public HashMap<String, SearchEngineInterface> records = new HashMap<String, SearchEngineInterface>();
	public ArrayList<String> attributeList = new ArrayList<String>();
	
	public DatabaseReader() {
		fetchDataFromDatabase();
		createAttributeList();
	}
	
	
	public void fetchDataFromDatabase() {
		// TODO Auto-generated method stub
//		try {
//			
//			Class.forName("org.postgresql.Driver");
//			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Empl","postgres","hardik");
//			Statement stmt =(Statement) con.createStatement();
//			
//			ResultSet rs = ((java.sql.Statement) stmt).executeQuery("Select * from Employee");
//			
//			while(rs.next()) {
//				String id = rs.getString(1);
//				String name = rs.getString(2);
//				String country = rs.getString(3);
//			 	String salary = rs.getString(4);
//				Employee employeeObject = new Employee(id, name, country, salary);
//				employeeRecords.put(id, employeeObject);
//			}
//			
//		} catch (Exception e) {
//			System.out.println(e);
//		}
		
		
		/*
		 * Employee database is like this 
		 *  "1";"hardik"    ;"india"	;"1000"
			"2";"shaunak" 	;"india"	;"2000"
			"3";"dhananjay"	;"japan "	;"3000"
			"4";"juhi"		;"usa"		;"4000"
			"5";"chirag"	;"usa"		;"5000"

		 */
		
		
		personRecords.put("1", new Person("1","hardik","dahisar","a@b"));
		personRecords.put("2", new Person("2","shaunak","borivali","c@d"));
		personRecords.put("3", new Person("3","chirag","ulhasnagar","e@f"));
		personRecords.put("4", new Person("4","dhanajay","santacruz","g@h"));
		personRecords.put("5", new Person("5","juhi","pune","i@j"));
		
	}
	
	
	public void createAttributeList() {
		
//		attributeList.add("id");
//		attributeList.add("name");
//		attributeList.add("country");
//		attributeList.add("salary");
		
		attributeList.add("pid");
		attributeList.add("name");
		attributeList.add("address");
		attributeList.add("emailId");

	}

	public ArrayList<String> getAttributeList(){
		return attributeList;
	}
	
	

	public HashMap<String, SearchEngineInterface> adaptToSearchEngine() {
//		for(Employee employee :   employeeRecords.values()){
//			EmployeeAdapter employeeAdapter = new EmployeeAdapter(employee);
//			records.put(employeeAdapter.getId(), employeeAdapter);
//		}
		
		for(Person person :   personRecords.values()){
			PersonAdapter personAdapter = new PersonAdapter(person);
			records.put(personAdapter.getPid(), personAdapter);
		}
			
		return records;
	}
}
