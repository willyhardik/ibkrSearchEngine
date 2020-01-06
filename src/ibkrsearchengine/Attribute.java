package ibkrsearchengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Attribute<E> {
	
	public String attributeName;
	public HashMap<String, LinkedList<String>> attributeMap;
	public TreeNode root;
	
	public Attribute(String attributeName, List<Employee> list) {
		this.attributeMap = new HashMap<String, LinkedList<String>>();
		this.attributeName = attributeName;
		buildPositiveAttributeMap(list);
		buildNegativeAttributeMap(list);
		this.root = buildAttributeTree(list);
	
	}

	public TreeNode buildAttributeTree(List<Employee> list) {
		
		List<String> keyList = new ArrayList<>(attributeMap.keySet());
		Collections.sort(keyList);
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
		
		String[] uniqueValue = new String[attributeMap.size()];
		int uniqueValueIndex = 0;
		for(String value : attributeMap.keySet()) {
			uniqueValue[uniqueValueIndex++] = value;
		}
		
		for(String value : uniqueValue) {
			
			String negative_value = "~".concat(value);
			list.removeAll(attributeMap.get(value));
			LinkedList<String> rowList = new LinkedList<>();
			for(Employee employee : list) {
				rowList.add(employee.getId());
			}
			attributeMap.put(negative_value, rowList);
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
	
	public Map<String, LinkedList<String>> getAttributeMap() {
		
		return attributeMap;
	}
	
}
