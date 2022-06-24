package com.resource.hrm.repository;

import com.resource.hrm.entity.Discipline;
import com.resource.hrm.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin("*")
public interface DisciplineRepository extends JpaRepository<Discipline,Long> {

    List<Discipline> getDisciplineByEmployer(Employee employer);
}
