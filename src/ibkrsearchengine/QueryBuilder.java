package ibkrsearchengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
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
	
	public HashSet<String> getResult(String query) {
		String subQuery = "";
		int queryNumber = 1;
		QueryProcessor queryProcessor = new QueryProcessor(attributeMap);
		ArrayList<HashMap<String,Object>> queue ;
		query = simplifyQuery(query);
//		System.out.println(query);
		
		// get substring with the smallest brackets
		while (query.indexOf("(") != -1) {
			HashSet<String> resultSet = new HashSet<String>();
			System.out.println(query);
			subQuery = getSubQuery(query);
			queue = processQuery(subQuery);
			resultSet.addAll(queryProcessor.getResults(queue));
			queryMap.put("#query" + queryNumber, resultSet);
			query = query.replace("(" + subQuery + ")", "#query" + queryNumber++);
			System.out.println(resultSet+"----"+queryMap);
		}

		System.out.println(query);
		HashSet<String> resultSet = new HashSet<String>();
		queue = processQuery(query);
		resultSet.addAll(queryProcessor.getResults(queue));
		queryMap.put("#query" + queryNumber, resultSet);
		query = query.replace("(" + subQuery + ")", "#query" + queryNumber++);
		System.out.println("Final result Set"+resultSet);
		
		return resultSet; // convert substring to
	}

	private String simplifyQuery(String query) {
		String newQuery = "", tempQuery = "";
		String[] tokens = query.split(" ");
		boolean querySplitNeeded = false;
		
		for (int tokenIndex = 0 ; tokenIndex < tokens.length ; tokenIndex++ ) {
			if("and".equals(tokens[tokenIndex]) || "or".equals(tokens[tokenIndex])) {
				if(querySplitNeeded) {
					String copyOfTempQuery = tempQuery;
					tempQuery = tempQuery.replace(">=", ">");
					tempQuery = tempQuery.replace("<=", "<");

					copyOfTempQuery = copyOfTempQuery.replace(">=", "=");
					copyOfTempQuery = copyOfTempQuery.replace("<=", "=");
					newQuery = newQuery + " ( " + tempQuery + " or " + copyOfTempQuery + " ) " + tokens[tokenIndex] ;
//					System.out.println(tempQuery);
					querySplitNeeded = false;
				}
				else {
					newQuery = newQuery +" "+ tempQuery +" "+ tokens[tokenIndex];
				}
				tempQuery = "";
			}
			else {
				if(">=".equals(tokens[tokenIndex]) || "<=".equals(tokens[tokenIndex])) {
					querySplitNeeded = true;
				}
				tempQuery = tempQuery +" "+ tokens[tokenIndex];
			}
//			System.out.println(tokens[tokenIndex]);

		}
		if(querySplitNeeded) {
			String copyOfTempQuery = tempQuery;
			tempQuery = tempQuery.replace(">=", ">");
			tempQuery = tempQuery.replace("<=", "<");

			copyOfTempQuery = copyOfTempQuery.replace(">=", "=");
			copyOfTempQuery = copyOfTempQuery.replace("<=", "=");
			newQuery = newQuery + "(" + tempQuery + "or" + copyOfTempQuery + ")";
//			System.out.println(tempQuery);
			querySplitNeeded = false;
		}
		else {
			newQuery = newQuery + tempQuery;
		}
//		System.out.println(newQuery);
		return newQuery;
	}

	private ArrayList<HashMap<String,Object>> processQuery(String query) {

		ArrayList<HashMap<String,Object>> queue = new ArrayList<>();// for some reason can't instantiate queue
		String[] operations = query.split(" or ");

		for (String operation : operations) {
			String[] subOperations = operation.split(" and ");
			HashMap<String,Object> operationMap = new HashMap();
			
			for (String subOperation : subOperations) {
//				System.out.println(subOperation);
				subOperation = subOperation.trim();
				if (subOperation.startsWith("#query")) {
//					System.out.println("df"+queryMap.get(subOperation));
					operationMap.put(subOperation, queryMap.get(subOperation));
				} 
				else {
					
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
					}
					else if (">".equals(operator)) {
						operationMap.put(attribute, getNode(attribute, value, false));
//						System.out.println("entered to get node"+getNode(attribute, value, false).data);
					}
					else if ("<".equals(operator)) {
						operationMap.put("~"+attribute, getNode(attribute, value, false));
//						System.out.println("entered to get node"+getNode(attribute, value, false).data);
					}
//					else if (">=".equals(operator)) {
//						operationMap.put(attribute, getNode(attribute, value, true));
//					} 
//					else if ("<=".equals(operator)) {
//						operationMap.put(attribute, getNode(attribute, value, true));
//					} 
					else if ("!=".equals(operator)) {
						operationMap.put("~" + attribute, value);
					}
				}
//				System.out.println(operationMap);
			}
			queue.add(operationMap);
		}

		return queue;

	}

	private TreeNode getNode(String attribute, String value, boolean includerRoot) {
		TreeNode searchPointer = attributeMap.get(attribute).root;
		while(searchPointer != null) {
//			System.out.println(searchPointer.data);
			if(value.equals(searchPointer.getData())) {
				return searchPointer;
			}
			else if(value.compareTo(searchPointer.getData()) > 0) {
				searchPointer = searchPointer.right;
			}
			else {
				searchPointer = searchPointer.left;
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