package ibkrsearchengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import ibkrsearchengine.QueryBuilder;
import ibkrsearchengine.DatabasePreprocessing;

public class IBKRSearchEngine {
	public static void main(String[] args) {
//		DatabaseReader databaseReader = new DatabaseReader();
//		HashMap<String,Employee> employeeRecords = databaseReader.getDatabase();
//		ArrayList<String> attributeList = databaseReader.getAttributes();
//		DatabasePreprocessing databasePreprocessing = new DatabasePreprocessing(employeeRecords, attributeList);
//		HashMap<String, Attribute> attributeMap = (HashMap<String, Attribute>) databasePreprocessing.getObjectMap();

		
		
		
//		String query = "(name = hardik or (salary = 3000 or id < 2)) and (name = shaunak or country = usa)";
//		String query = "country != usa and name = hardik";
//		String query = "salary > 3000 and country = india";
		
		
//		QueryBuilder queryBuilder = new QueryBuilder(attributeMap);
//		queryBuilder.getResult(query);
		
		DatabaseReader databaseReader = new DatabaseReader();
//		HashMap records = databaseReader.getDatabase();
		ArrayList<String> attributeList = databaseReader.getAttributeList();
//		System.out.println(attributeList);
		HashMap<String, SearchEngineInterface> databaseMap = databaseReader.adaptToSearchEngine();
		System.out.println(databaseMap);
				
		
		DatabasePreprocessing databasePreprocessing = new DatabasePreprocessing(databaseMap, attributeList);
		HashMap<String, Attribute> attributeMap = (HashMap) databasePreprocessing.getObjectMap();

//		String query = "(name = hardik or (salary = 3000 or id < 2)) and (name = shaunak or country = usa)";
//		String query = "country != usa and name = hardik";
//		String query = "salary > 3000 and country = india";
		String query = "(name = hardik or (emailId = i@j or pid > 2)) and (name = shaunak or address = dahisar)";
		
		QueryBuilder queryBuilder = new QueryBuilder(attributeMap);
		Iterator resultIterator = queryBuilder.getResult(query).iterator();
		
		System.out.println("\n\n\n The query result is ");
		while(resultIterator.hasNext()) {
			System.out.println(databaseMap.get(resultIterator.next()));
		}
		

	}
}
