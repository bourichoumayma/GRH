package com.resource.hrm.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor @NoArgsConstructor @Data
@Entity
public class Employee {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String regestrationNumber;
	private String name;
	private String afterName;
	private String cin;
	private String address;
	private String diplom;
	private String phoneNumber;
	private String email;
	private String post;
	private Double salary;
	private Boolean blackListe;
	private Boolean acitve;
	private Date lastModification;
	private int color;
	
	@OneToMany(mappedBy = "employer")
	private Collection<Absance> absances;
	
	@OneToMany
	private List<Depart> depart;

	@OneToMany(mappedBy = "employer")
	private Collection<Discipline> disciplines;
	
}
