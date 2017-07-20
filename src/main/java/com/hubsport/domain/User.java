package com.hubsport.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "USER")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	@Column(name = "STATUS", nullable = false)
	private int status;

	@NotEmpty
	@Column(name = "EMAIL", unique = true, nullable = false)
	private String email;

	@NotEmpty
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@NotEmpty
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@NotEmpty
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	// @NotEmpty
	// @ManyToMany(fetch = FetchType.LAZY)
	// @JoinTable(name = "APP_USER_USER_PROFILE",
	// joinColumns = { @JoinColumn(name = "USER_ID") },
	// inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
	// private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

	// public User() {
	// }
	//
	// public User(int status, String email, String password, String firstName,
	// String lastName) {
	// this.status = status;
	// this.email = email;
	// this.password = password;
	// this.firstName = firstName;
	// this.lastName = lastName;
	// }

	// public User(String email, String password2, boolean b, boolean c, boolean
	// d, boolean e,
	// Object grantedAuthorities) {
	// // TODO Auto-generated constructor stub
	// }

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	// public void setStatus(int status) {
	// this.status = status;
	// }

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == 0) ? 0 : id.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		return "User [id=" + id + ", status=" + status + ", email=" + email + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}

	// public Set<UserProfile> getUserProfiles() {
	// return userProfiles;
	// }
	//
	// public void setUserProfiles(Set<UserProfile> userProfiles) {
	// this.userProfiles = userProfiles;
	// }

}
