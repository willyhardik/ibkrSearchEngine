package ibkrsearchengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Attribute<E> {
	
	public String attributeName;
	public HashMap<String, LinkedList<String>> attributeMap = new HashMap<String, LinkedList<String>>();
	public TreeNode root;
	
	public Attribute(String attributeName, List<Employee> list) {
		
		this.attributeName = attributeName;
		if(attributeName.startsWith("~")) {
			buildNegativeAttributeMap(list);
		}
		else {
			buildPositiveAttributeMap(list);
		}
		this.root = buildAttributeTree(list);
		this.attributeMap = getAttributeMap();
	}

	public TreeNode buildAttributeTree(List<Employee> list) {
		
		List<String> keyList = new ArrayList<>(attributeMap.keySet());
		if(attributeName.startsWith("~")) {
			Collections.sort(keyList, Collections.reverseOrder());
		}
		else {
			Collections.sort(keyList);
		}
		
		return sortedListToTree(keyList, 0, keyList.size() - 1);
	}

	public TreeNode sortedListToTree(List<String> keyList, int start, int end) {
		
		if(start > end) {
			return null;
		} 
		
		int mid = (start + end)/2;
		TreeNode treeNode = new TreeNode(keyList.get(mid));
		treeNode.left = sortedListToTree(keyList, start, mid - 1);
		treeNode.right = sortedListToTree(keyList, mid + 1, end);
		return treeNode;
	}

	public void buildNegativeAttributeMap(List<Employee> list) {
		
		HashSet<String> uniqueSet = new HashSet<String>();
		
		for(Employee employee : list) {
			String attributeValue = employee.getValue(attributeName.substring(1));
			if(!uniqueSet.contains(attributeValue)) {
				uniqueSet.add(attributeValue);
				attributeMap.put(attributeValue, new LinkedList<String>());
			}
		}
		
		Iterator uniqueSetIterator = uniqueSet.iterator();
		while(uniqueSetIterator.hasNext()) {
			String attributeValue = (String) uniqueSetIterator.next();
			for(Employee employee :list) {
				if(!attributeValue.equals(employee.getValue(attributeName.substring(1)))) {
					attributeMap.get(attributeValue).add(employee.getId());
				}
			}
//			System.out.println(attributeName +" "+ attributeValue +" "+ attributeMap.get(attributeValue));
		}
		
	}

	public void buildPositiveAttributeMap(List<Employee> list) {
		
		// Create map<unique values, linkedList> based on attributeName
		for(Employee employee : list) {
			String positive_value = employee.getValue(attributeName);//element.getValue(attributeName);
//			System.out.println(positive_value);
			
			if(attributeMap.containsKey(positive_value)) {
				attributeMap.get(positive_value).add(employee.getId());
			}
			else {
				LinkedList<String> linkedList = new LinkedList<>();
				linkedList.add(employee.getId());
				attributeMap.put(positive_value, linkedList);
			}
		}
	}

	public String getAttributeName() {
		
		return attributeName;
	}
	
	public HashMap<String, LinkedList<String>> getAttributeMap() {
		
		return attributeMap;
	}
	
}
