package com.resource.hrm.controller;


import com.resource.hrm.service.AbsanceService.AbsanceService;
import com.resource.hrm.service.AbsanceTypeService.AbsanceTypeService;
import com.resource.hrm.service.EmployerService.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;

@Controller
public class AbsanceController {


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AbsanceTypeService absanceTypeService;

    @Autowired
    private AbsanceService absanceService;

    @GetMapping(value = "/absance")
    public String absance(Model model){
        model.addAttribute("employeeList",employeeService.getActiveEmployees());
        model.addAttribute("absanceTypeList",absanceTypeService.getAbsanceType());
        return "absance";
    }

    @GetMapping(value = "/edit/absance/{id}")
    public String editAbsnace(Model model, @RequestParam Long id){
        model.addAttribute("employeeList",employeeService.getActiveEmployees());
        model.addAttribute("absance", absanceService.getAbsanceById(id));
        model.addAttribute("absanceTypeList",absanceTypeService.getAbsanceType());
        return "absance";
    }

}
