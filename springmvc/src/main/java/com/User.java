package com;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

final public class User {
	
	private Long id;
	
	@NotNull
	@Size(min=5, max=16)
	private String firstName;
	
	@NotNull
	@Size(min=2, max=30)
	private String lastName;
	
	@NotNull
	@Size(min=5, max=25)
	private String userName;
	
	@NotNull
	@Size(min=5, max=25)
	private String password; // FIXME: this should be a char-based stuff I believe
	
	@NotNull
	@Size(min=2, max=30)
	private String email;
	
	public User() {
	}
	
	public User(String firstName, String lastName, String userName, String password, String email) {
		this(null, firstName, lastName, userName, password, email);
	}
	
	public User(Long id, String firstName, String lastName, String userName, String password, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	
	// Getters:
	public long getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	// Setters:
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public boolean equals(Object that) {
		// Takes into account only id;
		return EqualsBuilder.reflectionEquals(this, that, "firstName", "lastName", "userName", "password", "email");	
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "firstName", "lastName", "userName", "password", "email");
	}
}
