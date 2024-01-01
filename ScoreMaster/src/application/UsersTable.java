package application;

public class UsersTable {
	
	int user_id;
	
	String username, mail_address;
	
	String password;
	
	boolean authority;
	
	public UsersTable(int user_id, String username, String mail_adress) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.mail_address = mail_adress;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMail_address() {
		return mail_address;
	}

	public void setMail_address(String mail_adress) {
		this.mail_address = mail_adress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getAuthority() {
		return authority;
	}

	public void setAuthority(boolean authority) {
		this.authority = authority;
	}
	

}