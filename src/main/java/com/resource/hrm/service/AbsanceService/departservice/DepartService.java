package com.resource.hrm.service.AbsanceService.departservice;

import com.resource.hrm.entity.Depart;
import com.resource.hrm.entity.Employee;

import java.util.List;

public interface DepartService {


    void saveDepart(Depart depart);

    List<Depart> getDepartForEmployee(Employee e);

    void removeDepart(Long uid);

    Depart getDepartById(Long uid);
}
