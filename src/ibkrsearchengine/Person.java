package ibkrsearchengine;

public class Person {
	public String pid;
	public String name;
	public String address;
	public String emailId;
	
	
	public Person(String pid, String name, String address, String emailId) {
		super();
		this.pid = pid;
		this.name = name;
		this.address = address;
		this.emailId = emailId;
	}


	public String getPid() {
		return pid;
	}


	public void setPid(String pid) {
		this.pid = pid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	@Override
	public String toString() {
		return "Person [pid=" + pid + ", name=" + name + ", address=" + address + ", emailId=" + emailId + "]";
	}
	
	


}
