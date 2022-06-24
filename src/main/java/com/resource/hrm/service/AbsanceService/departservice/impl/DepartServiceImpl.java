package com.resource.hrm.service.AbsanceService.departservice.impl;

import com.resource.hrm.entity.Depart;
import com.resource.hrm.entity.Employee;
import com.resource.hrm.repository.DepartRepository;
import com.resource.hrm.service.AbsanceService.departservice.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepartServiceImpl implements DepartService {

    @Autowired
    DepartRepository departRepository;

    @Override
    public void saveDepart(Depart depart) {
        departRepository.save(depart);
    }

    @Override
    public List<Depart> getDepartForEmployee(Employee e) {
        return departRepository.findDepartByEmployer(e);
    }

    @Override
    public void removeDepart(Long uid) {
        departRepository.deleteById(uid);
    }

    @Override
    public Depart getDepartById(Long uid) {
        return departRepository.getById(uid);
    }
}
