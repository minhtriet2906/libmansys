package person;

import java.util.*;

public class User {
	private String username;

	private String password;

	private String name;

	private String email;

	private String phoneNumber;

	private String address;
	
	public User() {
		
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public User(String username, String password, String email) {
		this(username, password);
		this.email = email;
	}
}