package ibkrsearchengine;

public class Employee {
	public String id;
	public String name;
	public String country;
	public String salary;
	
	public Employee() {
		super();
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", country =" + country + ", salary=" + salary
				+ "]";
	}

	public Employee(String id, String name, String country, String salary) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.salary = salary;
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
	
	

}
