package bean;

public class UserBean {
	private String firstName = new String();
	private String lastName = new String();
	private String number = new String();
	private String TCKN = new String();

	public UserBean(){

	}
	public UserBean(String firstName, String lastName, String number, String TCKN) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
		this.TCKN = TCKN;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getNumber() {
		return number;
	}
	public String getTCKN() {
		return TCKN;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public void setTCKN(String TCKN) {
		this.TCKN = TCKN;
	}
}
