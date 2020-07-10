package com.qa.ims.persistence.domain;

public class Customer {

	private Long id;
	private String first_name;
	private String last_name;
	private String username;
	private String password;

	public Customer(String firstName, String surname, String username, String password) {
		this.first_name = firstName;
		this.last_name = surname;
		this.username = username;
		this.password = password;
		
	}

	public Customer(Long id, String firstName, String surname) {
		this.id = id;
		this.first_name = firstName;
		this.last_name = surname;
	}
	
	public Customer(Long id, String first_name, String last_name, String username, String password) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String firstName) {
		this.first_name = firstName;
	}

	public String getSurname() {
		return last_name;
	}

	public void setSurname(String surname) {
		this.last_name = surname;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return "id:" + id + ", first name:" + first_name + ", surname:" + last_name + ", username:" + username + ", password:" + password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		return true;
	}

}
