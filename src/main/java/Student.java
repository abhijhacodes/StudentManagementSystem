
public class Student {
	private int id;
	private String fname;
	private String lname;
	private String password;
	
	public Student(int id, String fname, String lname, String password) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
