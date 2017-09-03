package com.hubsport.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TOWNS")
public class Towns implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NAME")
	private String nameTowns;

//	@OneToMany(mappedBy = "towns", cascade = CascadeType.ALL)
//	private Set<Places> places;
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity=Districts.class )
	@JoinColumn(name = "districts_id")
	private Districts districts;

	public Integer getid() {
		return id;
	}

	public void setid(Integer id) {
		this.id = id;
	}

	public String getNameTowns() {
		return nameTowns;
	}

	public void setNameTowns(String nameTowns) {
		this.nameTowns = nameTowns;
	}

//	public Set<Places> getPlaces() {
//		return places;
//	}
//
//	public void setPlaces(Set<Places> places) {
//		this.places = places;
//	}

	public boolean isNew() {
		return (this.id == null);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Districts getDistricts() {
		return districts;
	}

	public void setDistricts(Districts districts) {
		this.districts = districts;
	}
}
