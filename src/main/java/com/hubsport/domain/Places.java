package com.hubsport.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PLACES")
public class Places implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NAME", nullable = false)
	private String namePlaces;

	@Column(name = "ADDRESS", nullable = false)
	private String address;

	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity=Towns.class )
	@JoinColumn(name = "TOWNS_ID")
	private Towns towns;
	
	@OneToMany(mappedBy = "places", cascade = CascadeType.ALL)
	private Set<Events> events;

	public Integer getid() {
		return id;
	}

	public void setid(Integer id) {
		this.id = id;
	}

	public String getNamePlaces() {
		return namePlaces;
	}

	public void setNamePlaces(String namePlaces) {
		this.namePlaces = namePlaces;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Towns getTowns() {
		return towns;
	}

	public void setTowns(Towns towns) {
		this.towns = towns;
	}

	public Set<Events> getEvents() {
		return events;
	}

	public void setEvents(Set<Events> events) {
		this.events = events;
	}

	public boolean isNew() {
		return (this.id == null);
	}

}
