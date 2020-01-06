package ibkrsearchengine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


public class QueryProcessor {

	public HashMap<String, Attribute> attributeMap;
	
	public QueryProcessor(HashMap<String, Attribute> attributeMap) {
		this.attributeMap = attributeMap;
	}
	
	public HashSet<String> getResults(ArrayList<HashMap<String,Object>> queue) {
		HashSet<String> resultSet = new HashSet<String>();
		Iterator subQueryIterator = queue.iterator();

		while (subQueryIterator.hasNext()) {
			HashMap<String, Object> operationMap = (HashMap) subQueryIterator.next();
			HashSet<String> tempResultSet = new HashSet<String>();
			
			for (String attributeName : operationMap.keySet()) {
				
				if(operationMap.get(attributeName) instanceof String) {
					tempResultSet = equalOperation(attributeName, (String) operationMap.get(attributeName), tempResultSet);
				}
//				else if(operationMap.get(key) instanceof TreeNode) {
//					greaterThanOperation(key, operationMap.get(key), attributeMap);
//				}
				else if(operationMap.get(attributeName) instanceof HashSet) {
//					System.out.println("Af");
					HashSet<String> queryResultSet = (HashSet<String>) operationMap.get(attributeName);
//					System.out.println(queryResultSet);
					if(tempResultSet.isEmpty()) {
						tempResultSet.addAll(queryResultSet);
					}
					else{
						Iterator recordIterator = queryResultSet.iterator();
						while (recordIterator.hasNext()) {
							String row = (String) recordIterator.next(); 
							if(!tempResultSet.contains(row)) {
								tempResultSet.remove(row);
							}
						}	
					}
					
//					System.out.println(tempResultSet);
				} 
//				Iterator resultIterator = resultSet.iterator();
//				while(resultIterator.hasNext()) {
//					String row = (String) resultIterator.next();
//					if(!tempResultSet.contains(row)) {
//						
//					}
//				}
			}
			resultSet.addAll(tempResultSet);
		}
		return resultSet;
	}

//	public void getResult(PriorityQueue queue, HashMap<String, Attribute> attributeMap, HashMap votecache) {
//		//loop through queue 
//		HashSet<String> resultSet = new HashSet<String>();
//		Iterator queueIterator = queue.iterator();
//		
//		
//		while(queueIterator.hasNext()) {
//			Object obj = queueIterator.next();
//			resultSet.addAll(getAndOperationResults(attributeMap, obj.map, votecache.clone()));
//		}
//		// fetch the object
//		
//		// iterate over the object's hashmap
//		
//		// fetch the linked list from the map
//		
//		// vote the items in the map
//	}
//
//	public HashSet<String> getAndOperationResults(HashMap<String, Attribute> attributeMap,
//			HashMap<String, String> operationMap, HashMap<String, Integer> votecache) {
//		HashSet<String> tempResultSet = new HashSet<String>();
//
//		String attribute, operator, value = "";
//
//		for (Map.Entry<String, String> operation : operationMap.entrySet()) {
//			operationcount++;
//			attribute = operation.parameter;
//			operator = operation.operator;
//			value = operation.value;
//
//			if (operator == "=") {
//				tempResultSet.addAll(
//						equalOperation(attribute, value, operationMap.size(), attributeMap, votecache, tempResultSet));
//			} else if (operator == ">") {
//				tempResultSet.addAll(greaterThanOperation(attribute, value, operationMap.size(), attributeMap,
//						votecache, tempResultSet));
//			} else if (operator == "<") {
//				tempResultSet.addAll(lessThanOperation(attribute, value, operationMap.size(), attributeMap, votecache,
//						tempResultSet));
//			} else if (operator == "!=") {
//				tempResultSet.addAll(notEqualOperation(attribute, value, operationMap.size(), attributeMap, votecache,
//						tempResultSet));
//			}
//
//		}
//		return tempResultSet;
//	}
//
//	private HashSet<String> notEqualOperation(String attribute, String value, int OperationCount,
//			HashMap<String, String> attributeMap, HashMap<String, Integer> votecache, HashSet<String> tempResultSet) {
//
//		for (Map.Entry<String, LinkedList> operation : attributeMap.entrySet()) {
//
//		}
//
//		return tempResultSet;
//	}
//
	public HashSet<String> greaterThanOperation(String attribute, TreeNode node, 
			HashMap<String, String> attributeMap) {
		HashSet<String> tempResultSet = new HashSet<String>();
		
		
		
		
		return tempResultSet;
	}
//
//	private HashSet<String> lessThanOperation(String attribute, String value, int operationCount,
//			HashMap<String, String> attributeMap, HashMap<String, Integer> votecache, HashSet<String> tempResultSet) {
//
//		return tempResultSet;
//	}
//
	public HashSet<String> equalOperation(String attribute, String value, HashSet<String> resultSet) {
		System.out.println("-"+attribute + "-" + value + "-");
		LinkedList<String> valueList = (LinkedList) attributeMap.get(attribute).attributeMap.get(value);
		System.out.println(attributeMap.get(attribute));
		System.out.println(attribute + "-" +valueList);
		Iterator recordIterator = valueList.iterator();
		
		if(resultSet.isEmpty()) {
			while (recordIterator.hasNext()) {
				resultSet.add((String) recordIterator.next());
			}	
			return resultSet;
		}
		else {
			while (recordIterator.hasNext()) {
				String row = (String) recordIterator.next(); 
				if(!resultSet.contains(row)) {
					resultSet.remove(row);
				}
					
			}
		}
		return resultSet;
	}
}
