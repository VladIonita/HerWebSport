package com.hubsport.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.hubsport.dao.FormValidationGroup;
import com.hubsport.dao.PersistenceValidationGroup;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Column(name = "USERNAME", unique = true, nullable = false)
	@Size(min=5, max = 15, groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	private String username;

	@NotEmpty(groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Column(name = "EMAIL", unique = true, nullable = false)
	// treb sa fie de un anumit stil gen bla@yahoo.com....
	private String email;

	@NotEmpty(groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Column(name = "PASSWORD", nullable = false)
	@Size(min=5, max = 15,groups={FormValidationGroup.class})
	private String password;

	@NotEmpty(groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@NotEmpty(groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	
	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

	// made for comparing two objects

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	 @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((id == null) ? 0 : id.hashCode());
	        result = prime * result + ((username == null) ? 0 : username.hashCode());
	        return result;
	    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}


}
