package com.resource.hrm.controller;


import com.resource.hrm.controller.formControl.DisciplineFrom;
import com.resource.hrm.entity.Discipline;
import com.resource.hrm.entity.Employee;
import com.resource.hrm.service.DeciplineService.DisciplineService;
import com.resource.hrm.service.EmployerService.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService ;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/discipline")
    public String discipline(Model model){
        model.addAttribute("employeeList",employeeService.getActiveEmployees());
        model.addAttribute("discipline",new DisciplineFrom());
        model.addAttribute("disciplines",disciplineService.getDesciplineForEmployee(employeeService.getActiveEmployees().get(0).getUid()));
        return "discipline";
    }

    @PostMapping(value = "/discipline/add")
    public String addDiscipline(Model model, DisciplineFrom disciplineFrom){
        disciplineService.addDesciplineForEmployee(disciplineFrom.getEmployee(),convertToDiscipline(disciplineFrom));
        model.addAttribute("employeeList",employeeService.getActiveEmployees());
        model.addAttribute("discipline",new DisciplineFrom());
        model.addAttribute("disciplines",disciplineService.getDesciplineForEmployee(disciplineFrom.getEmployee()));
        return "discipline";
    }

    private Discipline convertToDiscipline(DisciplineFrom disciplineFrom) {
        Discipline disciplin = new Discipline();
        disciplin.setEmployer(employeeService.getEmployeeById(disciplineFrom.getEmployee()));
        disciplin.setAvertissment(disciplineFrom.getAvertissment());
        disciplin.setDateAvvert(disciplineFrom.getDateAvvert());
        return disciplin;
    }


    @GetMapping(value = "/view")
    public String view(Model model, @RequestParam("uid") Long uid) {
        model.addAttribute("employeeList",employeeService.getActiveEmployees());
        model.addAttribute("discipline",new DisciplineFrom());
        model.addAttribute("disciplines",disciplineService.getDesciplineForEmployee(uid));
        return "discipline";
    }

    @GetMapping(value = "/removeDiscipline")
    public String remove(Long uid) {
        disciplineService.removeDeciplineById(uid);
        return "redirect:/discipline";
    }

    @GetMapping(value = "/editDiscipline")
    public String edit(Model model, @RequestParam("uid") Long uid) {
        model.addAttribute("employeeList", employeeService.getActiveEmployees());
        model.addAttribute("discipline",convertToDisciplineForm(disciplineService.getDisciplineById(uid)));
        model.addAttribute("disciplines",disciplineService.getDisciplineById(uid).getEmployer().getDisciplines());
        return "discipline";
    }

    private DisciplineFrom convertToDisciplineForm(Discipline discipline) {
        DisciplineFrom disciplineFrom = new DisciplineFrom();
        disciplineFrom.setEmployee(discipline.getEmployer().getUid());
        disciplineFrom.setAvertissment(discipline.getAvertissment());
        disciplineFrom.setDateAvvert(discipline.getDateAvvert());
        return disciplineFrom;
    }

}
