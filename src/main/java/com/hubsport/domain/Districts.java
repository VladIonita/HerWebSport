package com.hubsport.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DISTRICTS")
public class Districts implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "NAME")
	private String nameDistrict;
	
	@OneToMany(mappedBy = "districts", cascade = CascadeType.ALL)
	private Set<Towns> towns;

	public Integer getid() {
		return id;
	}

	public void setid(Integer id) {
		this.id = id;
	}

	public String getNameDistrict() {
		return nameDistrict;
	}

	public void setNameDistrict(String nameDistrict) {
		this.nameDistrict = nameDistrict;
	}

	public Set<Towns> getTowns() {
		return towns;
	}

	public void setTowns(Set<Towns> towns) {
		this.towns = towns;
	}


}
