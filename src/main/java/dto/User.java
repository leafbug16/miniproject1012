package dto;

import java.util.Date;

public class User {
	private String id;
	private String password;
	private String name;
	private Date regDate;
	
	//생성자
	public User(String id, String password, String name) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
	}
	public User() {}
	
	//getter,setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	//toString
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", name=" + name + ", regDate=" + regDate + "]";
	}
}
