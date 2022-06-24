package com.resource.hrm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;


@Builder
@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Depart {
	@Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long uid;
	private Date dateDebut;
	private Date dateDepart;
	private String motif;
	
	@ManyToOne
	private Employee employer;
}
