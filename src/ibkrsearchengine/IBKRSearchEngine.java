package ibkrsearchengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class IBKRSearchEngine {
	public static void main(String[] args) {
		DatabaseReader databaseReader = new DatabaseReader();
		HashMap<String,Employee> employeeRecords = databaseReader.getDatabase();
		ArrayList<String> attributes = databaseReader.getAttributes();
//		for(String key : employeeRecords.keySet()) {
//			System.out.println(key);
//		}
		
//		DatabasePreprocessing databasePreprocessing = new DatabasePreprocessing();
		
	}
}
