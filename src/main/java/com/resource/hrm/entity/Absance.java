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
public class Absance {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;
	private Date startDate;
	private Date endDate;
	private String note;
	
	@ManyToOne
	private Employee employer;

	@ManyToOne
	private AbsanceType absanceType;
}
