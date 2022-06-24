package com.resource.hrm.controller;

import com.resource.hrm.controller.formControl.DepartForm;
import com.resource.hrm.entity.Depart;
import com.resource.hrm.entity.Employee;
import com.resource.hrm.service.AbsanceService.departservice.DepartService;
import com.resource.hrm.service.EmployerService.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Controller
public class DepartContreller {

    @Autowired
    private EmployeeService employeeService;


    @Autowired
    private DepartService departService;

    @GetMapping(value = "/depart")
    public String depart(Model model){
        model.addAttribute("depart",new Depart());
        List<Employee> employeeList = employeeService.getActiveEmployees();
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("departList",departService.getDepartForEmployee(employeeList.get(0)));
        return "depart";
    }

    @PostMapping(value = "/depart/save")
    public String depart(Model model, DepartForm depart){
        Employee e = employeeService.getEmployeeById(depart.getEmployee());
        employeeService.addEmployee(e);
        departService.saveDepart(convertDepart(depart));
        model.addAttribute("depart",new Depart());
        model.addAttribute("departList",departService.getDepartForEmployee(e));
        model.addAttribute("employeeList", employeeService.getActiveEmployees());
        return "depart";
    }

    private Depart convertDepart(DepartForm depart) {
        return Depart.builder().uid(depart.getUid()).dateDepart(depart.getDateDepart()).dateDebut(depart.getDateDebut()).motif(depart.getMotif()).employer(employeeService.getEmployeeById(depart.getEmployee())).build();
    }



    @GetMapping(value = "/showdaprt")
    public String showdaprt(Model model ,@RequestParam Long uid) {
        Employee e = employeeService.getEmployeeById(uid);
        model.addAttribute("depart",new Depart());
        model.addAttribute("departList",departService.getDepartForEmployee(e));
        model.addAttribute("employeeList", employeeService.getActiveEmployees());
        return "depart";
    }


    @GetMapping(value = "/removedepart")
    public String removedepart(@RequestParam Long uid) {
        departService.removeDepart(uid);
        return "redirect:/depart";
    }

    @GetMapping(value = "/editdepart")
    public String editdepart(Model model,@RequestParam Long uid) {
        Depart d = departService.getDepartById(uid);
        model.addAttribute("depart",d);
        model.addAttribute("departList",departService.getDepartForEmployee(d.getEmployer()));
        model.addAttribute("employeeList", employeeService.getActiveEmployees());
        return "depart";
    }
}
