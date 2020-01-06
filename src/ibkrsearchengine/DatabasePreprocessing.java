package ibkrsearchengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DatabasePreprocessing<E> {
	
	public HashMap<String, Attribute<Employee>> objectMap = new HashMap<String, Attribute<Employee>>();
	public List<String> attributeNames;
	
	public DatabasePreprocessing(HashMap<String, Employee> map, List<String> attributeNames) {
		this.attributeNames = attributeNames;
		buildObjectMap(map);
	}

	public void buildObjectMap(HashMap<String, Employee> map) {
		
		for(String name : attributeNames) {
			List<Employee> list = new ArrayList<>();

			for(Employee employee : map.values()) {
				list.add(employee);
			}
			objectMap.put(name, new Attribute<Employee>(name, list));
		}
	}

	public Map<String, Attribute<Employee>> getObjectMap() {
		return objectMap;
	}

	public List<String> getAttributeNames() {
		return attributeNames;
	}
}
