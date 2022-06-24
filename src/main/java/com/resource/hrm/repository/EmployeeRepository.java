package com.resource.hrm.repository;

import com.resource.hrm.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin("*")
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
	Employee getEmployeeByUid(Long Uid);
	
	List<Employee> getEmployeesByAcitve(Boolean isActive);

	List<Employee> getEmployeeByBlackListe(Boolean isBlackListed);

	Employee getEmployeeByCin(String cin);

}
