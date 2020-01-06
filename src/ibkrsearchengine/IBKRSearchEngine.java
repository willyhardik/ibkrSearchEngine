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
		
		String query = "(fname = hardik or salary = 2000) and (fname = shaunak or dept = usa)";
//		String query = "dept = india";
		QueryBuilder queryBuilder = new QueryBuilder(attributeMap);
		queryBuilder.getResult(query);
	}
}
