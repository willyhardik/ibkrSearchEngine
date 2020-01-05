package ibkrsearchengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Attribute<E> {
	
	private String attributeName;
	private Map<String, LinkedList<E>> attributeMap;
	private TreeNode root;
	
	public Attribute(String attributeName, List<E> list) {
		
		this.attributeName = attributeName;
		buildPositiveAttributeMap(list);
		buildNegativeAttributeMap(list);
		this.root = buildAttributeTree(list);
	}

	private TreeNode buildAttributeTree(List<E> list) {
		
		List<String> keyList = new ArrayList<>(attributeMap.keySet());
		Collections.sort(keyList);
		return sortedListToTree(keyList, 0, keyList.size() - 1);
	}

	private TreeNode sortedListToTree(List<String> keyList, int start, int end) {
		
		if(start > end) {
			return null;
		} 
		
		int mid = (start + end)/2;
		TreeNode treeNode = new TreeNode(keyList.get(mid));
		treeNode.left = sortedListToTree(keyList, start, mid - 1);
		treeNode.right = sortedListToTree(keyList, mid + 1, end);
		return treeNode;
	}

	private void buildNegativeAttributeMap(List<E> list) {
		
		for(String value : attributeMap.keySet()) {
			
			String negative_value = "~".concat(value);
			list.removeAll(attributeMap.get(value));
			attributeMap.put(negative_value, (LinkedList<E>) list);
		}
	}

	private void buildPositiveAttributeMap(List<E> list) {
		
		// Create map<unique values, linkedList> based on attributeName
		for(E element : list) {
			
			String positive_value = "value";//element.getValue(attributeName);
 			
			if(attributeMap.containsKey(positive_value)) {
				attributeMap.get(positive_value).add(element);
			}
			else {
				LinkedList<E> linkedList = new LinkedList<>();
				linkedList.add(element);
				attributeMap.put(positive_value, linkedList);
			}
		}
	}

	public String getAttributeName() {
		
		return attributeName;
	}
	
	public Map<String, LinkedList<E>> getAttributeMap() {
		
		return attributeMap;
	}
}
