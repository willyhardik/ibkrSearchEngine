package ibkrsearchengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import ibkrsearchengine.QueryBuilder;
import ibkrsearchengine.DatabasePreprocessing;

public class IBKRSearchEngine {
	public static void main(String[] args) {
		DatabaseReader databaseReader = new DatabaseReader();
		HashMap<String,Employee> employeeRecords = databaseReader.getDatabase();
		ArrayList<String> attributeList = databaseReader.getAttributes();
	
		DatabasePreprocessing databasePreprocessing = new DatabasePreprocessing(employeeRecords, attributeList);
		HashMap<String, Attribute> attributeMap = (HashMap<String, Attribute>) databasePreprocessing.getObjectMap();

//		LinkedList<String> valueList = (LinkedList) attributeMap.get("~fname").attributeMap.get("1");
//		System.out.println("DSF"+valueList);
//		
		String query = "(fname = hardik or (salary = 2000 and lname = ibkr )) and (fname = shaunak or dept = usa)";
//		String query = "dept != usa and fname = hardik";
//		String query = "id >= 4 and dept = usa";
		QueryBuilder queryBuilder = new QueryBuilder(attributeMap);
		queryBuilder.getResult(query);
	}
}
