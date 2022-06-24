package com.resource.hrm.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity @AllArgsConstructor @NoArgsConstructor @Data
public class AbsanceType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    private String type;


    @OneToMany
    private List<Absance> absances;
}
