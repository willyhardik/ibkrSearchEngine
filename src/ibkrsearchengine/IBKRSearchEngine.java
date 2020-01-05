package ibkrsearchengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class IBKRSearchEngine {
	public static void main(String[] args) {
		DatabaseReader databaseReader = new DatabaseReader();
		HashMap<String,Employee> employeeRecords = databaseReader.getDatabase();
		ArrayList<String> attributeList = databaseReader.getAttributes();
	
		DatabasePreprocessing databasePreprocessing = new DatabasePreprocessing(employeeRecords, attributeList);
		HashMap<String, Attribute> attributeMap = (HashMap<String, Attribute>) databasePreprocessing.getObjectMap();
		
		for(String key : attributeMap.keySet()) {
			System.out.println(key);
		}
	}
}
