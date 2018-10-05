package com.qa.raz.petClinic.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "Injury")
public class Injury {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String title;
	
	@NotNull
	@Lob
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "pets_id", nullable = false)
	@OnDelete (action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private MySpringBootDataModel pets;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MySpringBootDataModel getPets() {
		return pets;
	}

	public void setPets(MySpringBootDataModel pets) {
		this.pets = pets;
	}
	
	
	
	
	
}
