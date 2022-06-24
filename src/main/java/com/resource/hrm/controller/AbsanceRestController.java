package com.resource.hrm.controller;


import com.resource.hrm.controller.json.AbsanceJsonRespance;
import com.resource.hrm.entity.Absance;
import com.resource.hrm.entity.AbsanceType;
import com.resource.hrm.entity.Employee;
import com.resource.hrm.service.AbsanceService.AbsanceService;
import com.resource.hrm.service.AbsanceTypeService.AbsanceTypeService;
import com.resource.hrm.service.EmployerService.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.resource.hrm.controller.json.AbsanceJson;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
public class AbsanceRestController {

    @Autowired
    private AbsanceService absanceService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AbsanceTypeService absanceTypeService;

    @GetMapping(value = "/get/absance")
    public List<AbsanceJson> getAbsance(){
        return convertAbsance(absanceService.getAbsances());
    }

    @RequestMapping(value = "/add/absance",method = RequestMethod.POST)
    public Long addList(@RequestBody AbsanceJsonRespance json) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Employee employee = employeeService.getEmployeeById(Long.valueOf(json.getEmploeeID()));
        AbsanceType absanceType = absanceTypeService.getAbsanceTypeById(Long.valueOf(json.getAbsanceID()));
        String discreption=json.getDiscreption();
        Date start = simpleDateFormat.parse(json.getStart());
        Date end = simpleDateFormat.parse(json.getEnd());
        Absance result =Absance.builder().absanceType(absanceType).employer(employee).endDate(end).startDate(start).note(discreption).build();
        if(!"".equals(json.getUid())){
            result.setUid(Long.valueOf(json.getUid()));
        }
        absanceService.addAbsance(result);
        return  result.getUid();
    }


    @PostMapping("/save/doc")
    public void document(@RequestBody MultipartFile file,@RequestParam("id") String id) throws IOException {
        if(!Files.exists(Paths.get(System.getProperty("user.home") + "/Docs"))){
            Files.createDirectories(Paths.get(System.getProperty("user.home") + "/Docs"));
        }
        Files.deleteIfExists(Paths.get(System.getProperty("user.home") + "/Docs").resolve(id+".pdf"));
        Files.copy(file.getInputStream(), Paths.get(System.getProperty("user.home") + "/Docs").resolve(id+".pdf"));

    }


    @RequestMapping(value = "/get/absance",method = RequestMethod.POST)
    public AbsanceJsonRespance getAbsance(@RequestBody long id) {
        return convertAbsanceJson(absanceService.getAbsanceById(id));
    }

    @RequestMapping(value = "/remove/absance",method = RequestMethod.POST)
    public void removeAbsance(@RequestBody long id) {
        absanceService.removeAbsance(id);
    }

    public AbsanceJsonRespance convertAbsanceJson(Absance absance){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return AbsanceJsonRespance.builder()
                .absanceID(String.valueOf(absance.getUid()))
                .emploeeID(String.valueOf(absance.getEmployer().getUid()))
                .discreption(absance.getNote())
                .end(simpleDateFormat.format(absance.getEndDate()))
                .start(simpleDateFormat.format(absance.getStartDate()))
                .uid(String.valueOf(absance.getUid()))
                .build();
    }

    public List<AbsanceJson> convertAbsance(List<Absance> absances){
        List<AbsanceJson> absanceJsons = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for(Absance absance : absances){
            AbsanceJson absanceJson = AbsanceJson.builder().id(absance.getUid())
                    .start(simpleDateFormat.format(absance.getStartDate()))
                    .end(simpleDateFormat.format(absance.getEndDate()))
                    .description(absance.getNote())
                    .title(absance.getEmployer().getName() + " " + absance.getEmployer().getAfterName() + " : " + absance.getAbsanceType().getType()  + " , " + absance.getNote()).build();
            absanceJsons.add(absanceJson);
        }
        return absanceJsons;
    }

    @GetMapping("/document/{id}")
    public byte[] getDocument(@PathVariable("id") Long id) throws IOException {
        File file = new File(System.getProperty("user.home") + "/Docs/" + id + ".pdf");
        System.out.println(System.getProperty("user.home") + "\\vid\\" + id + ".pdf");
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }
}
