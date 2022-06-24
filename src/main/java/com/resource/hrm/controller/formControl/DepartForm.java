package com.resource.hrm.controller.formControl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data @AllArgsConstructor @NoArgsConstructor
public class DepartForm {
    private Long uid;
    private String motif;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateDebut;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateDepart;
    private Long Employee;
}
