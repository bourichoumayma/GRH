package com.resource.hrm.controller;

import com.resource.hrm.entity.Depart;
import com.resource.hrm.entity.Employee;
import com.resource.hrm.service.EmployerService.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class EmployerController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public String employee(Model model) {
		model.addAttribute("employeeList", employeeService.getActiveEmployees());
		Employee employee = new Employee();
		List<Depart> departList = new ArrayList<>();
		departList.add(new Depart());
		employee.setDepart(departList);
		model.addAttribute("employee", employee);
		return "employee";
	}
	
	@RequestMapping(value = "/employee/save", method = RequestMethod.POST)
	public String saveEmployee(Model model, Employee employer) {
		employer.setAcitve(true);
		Employee e = employeeService.getEmployeeByCin(employer.getCin());

		model.addAttribute("employeeList", employeeService.getActiveEmployees());
		if(!Objects.isNull(e)){
			if(e.getBlackListe()){
				model.addAttribute("blackListedCin","this employee is blacklisted");
				model.addAttribute("employee", employer);
				return "employee";
			}else if (Objects.isNull(employer.getUid())){
				model.addAttribute("employeeAlreadyExist","this employee already exist in the database");
				model.addAttribute("employee", employer);
				return "employee";
			}
		}
		model.addAttribute("employee", new Employee());
		employeeService.editEmployee(employer);
		return "employee";
	}
	
	@GetMapping(value = "/remove")
	public String remove(Long uid) {
		employeeService.removeEmployeeById(uid);
		return "redirect:/employee";
	}
	
	@GetMapping(value = "/edit")
	public String edit(Model model, @RequestParam("uid") Long uid) {
		model.addAttribute("employeeList", employeeService.getActiveEmployees());
		model.addAttribute("employee", employeeService.getEmployeeById(uid));
		return "employee";
	}
}
