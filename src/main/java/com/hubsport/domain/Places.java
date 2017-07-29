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
@Table(name = "PLACES")
public class Places {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private int id;

	@NotEmpty
	@Column(name = "NAME")
	private String name;

	@NotEmpty
	@Column(name = "ADDRESS")
	private String address;

	@OneToOne
	@JoinColumn(name = "TOWNS_ID")
	private Towns town;
	
	

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Towns getTowns() {
		return town;
	}

	public void setTowns(Towns towns) {
		this.town = towns;
	}

	@Override
	public String toString() {
		return "Places [name=" + name + ", address=" + address + ", towns=" + town.getName() + "]";
	}

	
	
}
