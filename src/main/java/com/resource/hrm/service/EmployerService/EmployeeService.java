package com.resource.hrm.service.EmployerService;

import com.resource.hrm.entity.Employee;

import java.util.List;

public interface EmployeeService {
	
	void addEmployee(Employee employer);
	
	void editEmployee(Employee employer);
	
	void removeEmployee(Employee employer);
	
	List<Employee> getAllEmployee();
	
	Employee getEmployeeById(Long uid);
	
	Employee removeEmployeeById(Long uid);
	
	List<Employee> getActiveEmployees();

	List<Employee> getAllInAciveEmployee();

	void activateEmployee(Long id);

    List<Employee> getEmployeeDepart(int numberOfdate);

	void addToBlackList(Long uid);

	List<Employee> getBlackListedEmployee();

	void removeFromBlackList(Long uid);

	Employee getEmployeeByCin(String cin);
}
