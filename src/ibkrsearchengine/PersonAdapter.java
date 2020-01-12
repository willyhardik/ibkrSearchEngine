package ibkrsearchengine;


public class PersonAdapter implements SearchEngineInterface{

	public String pid;
	public String name;
	public String address;
	public String emailId;
	
	
	public PersonAdapter(Person person) {
		super();
		this.pid = person.getPid();
		this.name = person.getName();
		this.address = person.getAddress();
		this.emailId = person.getEmailId();
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


	@Override
	public String getValue(String property) {
		switch(property) {
		case "pid":
			return this.getPid();
		case "name":
			return this.getName();
		case "address":
			return this.getAddress();
		case "emailId":
			return this.getEmailId();
		default :
			return null;
		}
		
	}
	
	
}
