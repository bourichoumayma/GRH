package com.resource.hrm.repository;


import com.resource.hrm.entity.Depart;

import com.resource.hrm.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin("*")
public interface DepartRepository extends JpaRepository<Depart,Long> {


    List<Depart> findDepartByEmployer(Employee e);
}
