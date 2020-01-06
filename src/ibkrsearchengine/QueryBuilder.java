package ibkrSearchEngine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class QueryBuilder {
	/*
	 * you will get the query and you will return the queue
	 */
	public HashMap<String, ? extends Object> queryMap = new HashMap();

	public void getResult(String query) {
		String subQuery = "";
		int queryNumber = 1;
		QueryProcessor queryProcessor = new QueryProcessor();
		
		// get substring with the smallest brackets
		while (query.indexOf("(") != -1) {
			HashSet<String> resultSet = new HashSet<String>();
			subQuery = getSubQuery(query);
			Queue queue = processQuery(subQuery);
			resultSet.addAll(queryProcessor.getResults(queue, attributeMap));
			queryMap.put("#query" + queryNumber, resultSet);

			query = query.replace("(" + subQuery + ")", "#query" + queryNumber++);
		}

		HashSet<String> resultSet = new HashSet<String>();
		processQuery(subQuery);
		resultSet.addAll(queryProcessor.getResults(queue, attributeMap));
		queryMap.put("#query" + queryNumber, resultSet);
		query = query.replace("(" + subQuery + ")", "#query" + queryNumber++);

		// convert substring to queue
		// pass it to getResult
	}

	private Queue processQuery(String query) {

		Queue<HashMap> queue = new Queue();
		String[] operations = query.split(" or ");

		for (String operation : operations) {
			String[] subOperations = operation.split(" and ");
			HashMap<String, ? extends Object> operationMap = new HashMap();
			
			for (String subOperation : subOperations) {
				if (subOperation.startsWith("#query")) {
					operationMap.put(subOperation, queryMap.get(subOperation));
					
				} else {
					
					StringTokenizer parameter = new StringTokenizer(subOperation, "!><=", true);
					String attribute, operator, value;
					if (parameter.countTokens() == 3) {
						attribute = parameter.nextToken();
						operator = parameter.nextToken();
						value = parameter.nextToken();
					} else {
						attribute = parameter.nextToken();
						operator = parameter.nextToken();
						operator += parameter.nextToken();
						value = parameter.nextToken();
					}

					if (operator == "=") {
						operationMap.put(attribute, value);
					} else if (operator == ">") {
						operationMap.put(attribute, getNode(attribute, value, false));
					} else if (operator == "<") {
						operationMap.put(attribute, getNode(attribute, value, false));
					} else if (operator == ">=") {
						operationMap.put(attribute, getNode(attribute, value, true));
					} else if (operator == "<=") {
						operationMap.put(attribute, getNode(attribute, value, true));
					} else if (operator == "!=") {
						operationMap.put("~" + attribute, value);
					}
				}
			}
			queue.add(operationMap);
		}

		return queue;

	}

	private Node getNode(String attribute, String value, boolean includerRoot) {
		TreeNode searchPointer = attributeMap.get(attribute).tree;
		
		while(searchPointer != null) {
			if(searchPointer == value) {
				return searchPointer;
			}
			else if(searchPointer.data > value) {
				searchPointer = searchPointer.left;
			}
			else {
				searchPointer = searchPointer.right;
			}
		}
		
		return node;
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