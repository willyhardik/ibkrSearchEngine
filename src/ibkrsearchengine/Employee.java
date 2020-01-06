package ibkrsearchengine;

public class Employee {
	public String id;
	public String fname;
	public String lname;
	public String dept;
	public String salary;
	
	public Employee() {
		super();
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", fname=" + fname + ", lname=" + lname + ", dept=" + dept + ", salary=" + salary
				+ "]";
	}

	public Employee(String id, String fname, String lname, String dept, String salary) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.dept = dept;
		this.salary = salary;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	public String getValue(String property) {
		if("id".equals(property)) {
			return getId();
		}
		else if("fname".equals(property)) {
			return getFname();
		}
		else if("lname".equals(property)) {
			return getLname();
		}
		else if("salary".equals(property)) {
			return getSalary();
		}
		else if("dept".equals(property)) {
			return getDept();
		}
		else {
			return " ";
		}
				
	}
	

}
