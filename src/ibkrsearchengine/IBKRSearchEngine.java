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
//		String query = "(name = hardik or (salary = 2000 id > 1)) and (name = shaunak or country = usa)";
//		String query = "country != usa and name = hardik";
		String query = "salary > 3000 and country = india";
		QueryBuilder queryBuilder = new QueryBuilder(attributeMap);
		queryBuilder.getResult(query);
	}
}
