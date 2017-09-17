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
@Table(name = "EVENTS")
public class Events implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer status = 0;

	@Column(name = "NAME", nullable = false)
	private String nameEvents;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "events", cascade = CascadeType.ALL)
	private Set<Timetable> timetable;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PLACES_ID", nullable = false)
	private Places places;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CATEGORIES_ID", nullable = false)
	private Categories categories;
	
	// @ManyToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "BROADCASTERS_ID")
	// private Broadcasters broadcasters;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNameEvents() {
		return nameEvents;
	}

	public void setNameEvents(String nameEvents) {
		this.nameEvents = nameEvents;
	}

	public Set<Timetable> getTimetable() {
		return timetable;
	}

	public void setTimetable(Set<Timetable> timetable) {
		this.timetable = timetable;
	}

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public Places getPlaces() {
		return places;
	}

	public void setPlaces(Places places) {
		this.places = places;
	}
	
}
