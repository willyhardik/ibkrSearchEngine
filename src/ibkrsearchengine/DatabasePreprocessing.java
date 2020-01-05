package ibkrsearchengine;

import java.util.List;
import java.util.Map;

public class DatabasePreprocessing<E> {
	
	private Map<String, Attribute<E>> objectMap;
	private List<String> attributeNames;
	
	public DatabasePreprocessing(Map<String, E> map, List<String> attributeNames) {
		
		this.attributeNames = attributeNames;
		buildObjectMap(objectMap);
	}

	private void buildObjectMap(Map<String, Attribute<E>> map) {
		
		for(String name : attributeNames) {
			List<E> list = (List) map.values();
			System.out.println(list.get(0).toString());
			objectMap.put(name, new Attribute<E>(name, list));
		}
	}

	public Map<String, Attribute<E>> getObjectMap() {
		return objectMap;
	}

	public List<String> getAttributeNames() {
		return attributeNames;
	}
}
