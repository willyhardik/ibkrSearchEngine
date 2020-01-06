package ibkrsearchengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import ibkrsearchengine.QueryBuilder;

public class IBKRSearchEngine {
	public static void main(String[] args) {
		DatabaseReader databaseReader = new DatabaseReader();
		HashMap<String,Employee> employeeRecords = databaseReader.getDatabase();
		ArrayList<String> attributeList = databaseReader.getAttributes();
	
		DatabasePreprocessing databasePreprocessing = new DatabasePreprocessing(employeeRecords, attributeList);
		HashMap<String, Attribute> attributeMap = (HashMap<String, Attribute>) databasePreprocessing.getObjectMap();
		
		Attribute a = attributeMap.get("name");
		System.out.println(a);
		String query = "(name = hardik or salary = 1000) and (name = shaunak or country = japan)";
		
		QueryBuilder queryBuilder = new QueryBuilder(attributeMap);
		queryBuilder.getResult(query);
	}
}
