package com.resource.hrm.service.EmployerService.imp;

import com.resource.hrm.entity.Depart;
import com.resource.hrm.entity.Employee;
import com.resource.hrm.repository.DepartRepository;
import com.resource.hrm.repository.EmployeeRepository;
import com.resource.hrm.service.EmployerService.EmployeeService;

import net.bytebuddy.matcher.FilterableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;


	@Autowired
	private DepartRepository departRepository;

	@Override
	public void addEmployee(final Employee employer) {
		employer.setColor((int)(Math.random() * 0x1000000));
		employeeRepository.save(employer);
	}
	
	@Override
	public void editEmployee(final Employee employer) {
		employeeRepository.save(employer);
	}
	
	@Override
	public void removeEmployee(final Employee employer) {
		employer.setAcitve(false);
		employeeRepository.save(employer);
	}
	
	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}
	
	@Override
	public Employee getEmployeeById(final Long uid) {
		return employeeRepository.getEmployeeByUid(uid);
	}
	
	public Employee removeEmployeeById(final Long uid){
		Employee employer= getEmployeeById(uid);
		removeEmployee(employer);
		return employer;
	}
	
	@Override
	public List<Employee> getActiveEmployees() {
		return employeeRepository.getEmployeesByAcitve(true);
	}

	@Override
	public List<Employee> getAllInAciveEmployee() {
		return employeeRepository.getEmployeesByAcitve(false);
	}

	@Override
	public void activateEmployee(Long id) {
		Employee employee = getEmployeeById(id);
		employee.setAcitve(true);
		employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getEmployeeDepart(int numberOfdate) {
		List<Employee> activeEmployee = employeeRepository.getEmployeesByAcitve(true);
		List<Employee> employeeDate = new ArrayList<>();
		for(Employee e : activeEmployee){
			Optional<Depart> optionalDepart = departRepository.findDepartByEmployer(e).stream().sorted((e1, e2)-> DateUtils.millisecond(e1.getDateDepart()) - DateUtils.millisecond(e2.getDateDepart())).findFirst();
			if(optionalDepart.isPresent()){
				Depart currDepart = optionalDepart.get();
				if(currDepart.getDateDepart().getTime() - (new Date().getTime())<(numberOfdate * 86400000) ){
					employeeDate.add(e);
				}
			}

		}
		return employeeDate;
	}

	@Override
	public void addToBlackList(Long uid) {
		Employee e = employeeRepository.getEmployeeByUid(uid);
		e.setBlackListe(true);
		e.setAcitve(false);
		employeeRepository.save(e);
	}

	@Override
	public List<Employee> getBlackListedEmployee() {
		return employeeRepository.getEmployeeByBlackListe(true);
	}

	@Override
	public void removeFromBlackList(Long uid) {
		Employee e = employeeRepository.getEmployeeByUid(uid);
		e.setBlackListe(false);
		e.setAcitve(true);
		employeeRepository.save(e);
	}

	@Override
	public Employee getEmployeeByCin(String cin){
		return employeeRepository.getEmployeeByCin(cin);
	}

}
