package com.hubsport.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "TOWNS")
public class Towns {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private int id;

	@NotEmpty
	@Column(name = "NAME")
	private String name;

	@OneToOne
	@JoinColumn(name = "DISTRICTS_ID")
	private Districts districts;
	
	public Towns() {
	}

	public Towns(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Districts getDistricts() {
		return districts;
	}

	public void setDistricts(Districts districts) {
		this.districts = districts;
	}

}
