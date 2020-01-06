package ibkrsearchengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class QueryBuilder {
	
	/*
	 * you will get the query and you will return the queue
	 */
	public HashMap<String, HashSet<String>> queryMap = new HashMap();
	public HashMap<String, Attribute> attributeMap;

	public QueryBuilder(HashMap<String,Attribute> attributeMap) {
		this.attributeMap = attributeMap;
	}
	
	public void getResult(String query) {
		String subQuery = "";
		int queryNumber = 1;
		QueryProcessor queryProcessor = new QueryProcessor(attributeMap);
		ArrayList<HashMap<String,Object>> queue ;
		
		// get substring with the smallest brackets
		while (query.indexOf("(") != -1) {
			HashSet<String> resultSet = new HashSet<String>();
			System.out.println(query);
			subQuery = getSubQuery(query);
			queue = processQuery(subQuery);
			resultSet.addAll(queryProcessor.getResults(queue));
			queryMap.put("#query" + queryNumber, resultSet);
			query = query.replace("(" + subQuery + ")", "#query" + queryNumber++);
			System.out.println(resultSet);
		}

		System.out.println(query);
		HashSet<String> resultSet = new HashSet<String>();
		queue = processQuery(query);
		resultSet.addAll(queryProcessor.getResults(queue));
		queryMap.put("#query" + queryNumber, resultSet);
		query = query.replace("(" + subQuery + ")", "#query" + queryNumber++);
		System.out.println(resultSet);
		// convert substring to
	}

	private ArrayList<HashMap<String,Object>> processQuery(String query) {

		ArrayList<HashMap<String,Object>> queue = new ArrayList<>();// for some reason can't instantiate queue
		String[] operations = query.split(" or ");

		for (String operation : operations) {
			String[] subOperations = operation.split(" and ");
			HashMap<String,Object> operationMap = new HashMap();
			
			for (String subOperation : subOperations) {
				if (subOperation.startsWith("#query")) {
					operationMap.put(subOperation, queryMap.get(subOperation));
					
				} else {
					
					StringTokenizer parameter = new StringTokenizer(subOperation, "!><=", true);
					String attribute, operator, value;
//					System.out.println(parameter.countTokens());
					if (parameter.countTokens() == 3) {
						attribute = parameter.nextToken().trim();
						operator = parameter.nextToken().trim();
						value = parameter.nextToken().trim();
					} else {
						attribute = parameter.nextToken().trim();
						operator = parameter.nextToken().trim();
						operator += parameter.nextToken().trim();
						value = parameter.nextToken().trim();
					}

					if ("=".equals(operator)) {
						operationMap.put(attribute, value);
					} else if (operator == ">") {
						operationMap.put(attribute, getNode(attribute, value, false));
					}
//					else if (operator == "<") {
//						operationMap.put(attribute, getNode(attribute, value, false));
//					} else if (operator == ">=") {
//						operationMap.put(attribute, getNode(attribute, value, true));
//					} else if (operator == "<=") {
//						operationMap.put(attribute, getNode(attribute, value, true));
//					} 
					else if (operator == "!=") {
						operationMap.put("~" + attribute, value);
					}
				}
			}
			queue.add(operationMap);
		}

		return queue;

	}

	private TreeNode getNode(String attribute, String value, boolean includerRoot) {
		TreeNode searchPointer = attributeMap.get(attribute).root;
		
		while(searchPointer != null) {
			if(value.equals(searchPointer.getData())) {
				return searchPointer;
			}
			else if(value.compareTo(searchPointer.getData()) > 0) {
				searchPointer = searchPointer.left;
			}
			else {
				searchPointer = searchPointer.right;
			}
		}
		
		return searchPointer;
	}

	private String getSubQuery(String query) {
		int startIndex = -1, endIndex = -1;
		for (int characterIndex = 0; characterIndex < query.length(); characterIndex++) {
			if (query.charAt(characterIndex) == '(') {
				startIndex = characterIndex;
			} else if (query.charAt(characterIndex) == ')') {
				endIndex = characterIndex;
				break;
			}
		}

		return query.substring(startIndex + 1, endIndex);
	}
}