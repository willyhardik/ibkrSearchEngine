package ibkrsearchengine;

import java.util.HashMap;

public class EmployeeAdapter implements SearchEngineInterface{
		
	public String id;
	public String name;
	public String country;
	public String salary;
	

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", country =" + country + ", salary=" + salary
				+ "]";
	}

	public EmployeeAdapter(Employee record) {
		super();
		this.id = record.getId();
		this.name = record.getName();
		this.country = record.getCountry();
		this.salary = record.getSalary();
	}
	
	
	public EmployeeAdapter() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	@Override
	public String getValue(String property) {
		// TODO Auto-generated method stub
		switch(property) {
		case "id":
			return this.getId();
		case "name":
			return this.getName();
		case "country":
			return this.getCountry();
		case "salary":
			return this.getSalary();

		}
		return null;
	}

	
	
	

}
