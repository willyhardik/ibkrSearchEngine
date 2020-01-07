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
				else if(operationMap.get(attributeName) instanceof TreeNode) {
					HashSet<String> dataSet = new HashSet<String>();
					HashSet<String> operationTotalSet = new HashSet<String>();
					
					if(attributeName.startsWith("~")) {
						dataSet = lessThanOperation(attributeName, (TreeNode)operationMap.get(attributeName));
						Iterator dataIterator = dataSet.iterator();
						while(dataIterator.hasNext()) {
							LinkedList<String> valueList = (LinkedList) attributeMap.get(attributeName.substring(1)).attributeMap.get(dataIterator.next());
							Iterator recordIterator = valueList.iterator();
							while(recordIterator.hasNext()) {
								operationTotalSet.add((String) recordIterator.next());
							}
//							System.out.println("dsa"+operationTotalSet);
						}
					}
					else {
						dataSet = greaterThanOperation(attributeName, (TreeNode)operationMap.get(attributeName));
						Iterator dataIterator = dataSet.iterator();
						while(dataIterator.hasNext()) {
							LinkedList<String> valueList = (LinkedList) attributeMap.get(attributeName).attributeMap.get(dataIterator.next());
							Iterator recordIterator = valueList.iterator();
							while(recordIterator.hasNext()) {
								operationTotalSet.add((String) recordIterator.next());
							}
//							System.out.println("dsa"+operationTotalSet);
						}
					}
					
					
//					System.out.println("asd"+operationTotalSet);
					if(tempResultSet.isEmpty()) {
						tempResultSet.addAll(operationTotalSet);
					}
					else{
						HashSet<String> intersectionSet = new HashSet<String>();
						Iterator recordIterator = operationTotalSet.iterator();
						while (recordIterator.hasNext()) {
							String row = (String) recordIterator.next(); 
							if(tempResultSet.contains(row)) {
								intersectionSet.add(row);
							}
						}	
						tempResultSet = intersectionSet;
					}
//					System.out.println("qwe"+tempResultSet);
				}
				else if(operationMap.get(attributeName) instanceof HashSet) {
					HashSet<String> queryResultSet = (HashSet<String>) operationMap.get(attributeName);
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
				} 
			}
			resultSet.addAll(tempResultSet);
		}
		return resultSet;
	}

	
	
	
	public HashSet<String> greaterThanOperation(String attribute, TreeNode node) {
		String nodeValue = node.data;
		HashSet<String> tempResultSet = new HashSet<String>();
		TreeNode rootNode = attributeMap.get(attribute).root; 
		
		while(rootNode != null) {
//			System.out.println("DSF"+rootNode.data);
			if(rootNode.data.compareTo(nodeValue) > 0 ) {
				tempResultSet.add(rootNode.data);
				tempResultSet.addAll(getNodesFromTree(rootNode.right));
				rootNode = rootNode.left;
			}
			else {
				rootNode = rootNode.right;
			}
//			System.out.println("DSFdsf"+tempResultSet);	
		}
//		System.out.println(tempResultSet);
		return tempResultSet;
	}
	
	public HashSet<String> lessThanOperation(String attribute, TreeNode node) {
		String nodeValue = node.data;
		HashSet<String> tempResultSet = new HashSet<String>();
		TreeNode rootNode = attributeMap.get(attribute).root; 
		
		while(rootNode != null) {
//			System.out.println("DSF"+rootNode.data);
			if(rootNode.data.compareTo(nodeValue) < 0 ) {
				tempResultSet.add(rootNode.data);
				tempResultSet.addAll(getNodesFromTree(rootNode.right));
				rootNode = rootNode.left;
			}
			else {
				rootNode = rootNode.right;
			}
//			System.out.println("DSFdsf"+tempResultSet);	
		}
//		System.out.println(tesmpResultSet);
		return tempResultSet;
	}
	
	
	public HashSet<String> getNodesFromTree(TreeNode newNode) {
		HashSet<String> tempResultSet = new HashSet<String>();
		Stack<TreeNode> nodeStack = new Stack<TreeNode>();
		while(!nodeStack.isEmpty() || newNode != null) {
			if(newNode != null) {
				nodeStack.push(newNode);
				newNode = newNode.left;
			}
			else {
				newNode = nodeStack.pop();
//				System.out.println(newNode.data);
				tempResultSet.add(newNode.data);
				newNode = newNode.right;
				
			}
		}
		return tempResultSet;
	}
	

	public HashSet<String> equalOperation(String attribute, String value, HashSet<String> resultSet) {
//		System.out.println("-"+attribute + "-" + value + "-");
		LinkedList<String> valueList = (LinkedList) attributeMap.get(attribute).attributeMap.get(value);
//		System.out.println(attributeMap.get(attribute));
//		System.out.println(attribute + "-" +valueList);
		Iterator recordIterator = valueList.iterator();
		HashSet<String> tempResultSet = new HashSet<String>();
		
		
		if(resultSet.isEmpty()) {
			while (recordIterator.hasNext()) {
				resultSet.add((String) recordIterator.next());
			}	
			return resultSet;
		}
		else {
			while (recordIterator.hasNext()) {
				String row = (String) recordIterator.next(); 
				if(resultSet.contains(row)) {
					tempResultSet.add(row);
				}
			}
			return tempResultSet;
		}
		
	}
}
