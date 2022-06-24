package com.resource.hrm.controller;

import com.resource.hrm.entity.User;
import com.resource.hrm.repository.UserRepository;
import com.resource.hrm.service.EmployerService.EmployeeService;
import com.resource.hrm.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomePageController {

	@Autowired
	private EmployeeService employeeService;


	private int departDateNumber = 20 ;


	@Autowired
	private UserRepository userRepository;


	@GetMapping(path = "/index")
	public String index(Model model){
		model.addAttribute("employeeList",employeeService.getAllEmployee());
		model.addAttribute("inActiveEmployeeList",employeeService.getAllInAciveEmployee());
		model.addAttribute("activeEmployeeList",employeeService.getActiveEmployees());
		model.addAttribute("employeeDepart",employeeService.getEmployeeDepart(departDateNumber));
		model.addAttribute("dateNumber",departDateNumber);
		model.addAttribute("usersList",userRepository.findAll());
		model.addAttribute("blackListedEmployee",employeeService.getBlackListedEmployee());
		model.addAttribute("user",new User());
		UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.getUserByUsername(principal.getUsername());
		model.addAttribute("passwordNotChanged",user.getEnabled() == null ? true : !user.getEnabled());
		model.addAttribute("userRole",user.getRole());
		return "indexpage";
	}

	@GetMapping(value = "/activate")
	public String activate(Long uid) {
		employeeService.activateEmployee(uid);
		return "redirect:/index";
	}
	@GetMapping(value = "/inactivate")
	public String inactivate(Long uid) {
		employeeService.removeEmployeeById(uid);
		return "redirect:/index";
	}

	@PostMapping(value = "/save/date")
	public String saveDate(int dateNumber) {
		this.departDateNumber=dateNumber;
		return "redirect:/index";
	}


	@PostMapping(value = "/add/user")
	public String addUser(User user) {
		userRepository.save(user);
		return "redirect:/index";
	}


	@GetMapping(value = "/removeuser")
	public String removeuser(Long uid) {
		userRepository.deleteById(uid);
		return "redirect:/index";
	}



	@GetMapping(value = "/addtobacklist")
	public String addtobacklist(Long uid) {
		employeeService.addToBlackList(uid);
		return "redirect:/index";
	}



	@GetMapping(value = "/removefromBlacklist")
	public String removefromBlacklist(Long uid) {
		employeeService.removeFromBlackList(uid);
		return "redirect:/index";
	}

}
