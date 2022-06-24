package com.resource.hrm.service.DeciplineService.imp;

import com.resource.hrm.entity.Discipline;
import com.resource.hrm.entity.Employee;
import com.resource.hrm.repository.DisciplineRepository;
import com.resource.hrm.service.DeciplineService.DisciplineService;
import com.resource.hrm.service.EmployerService.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplineServiceImp implements DisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;
    @Autowired
    private EmployeeService employerService;

    @Override
    public List<Discipline> getDesciplineForEmployee(Long uid) {
        Employee employer = employerService.getEmployeeById(uid);
        return disciplineRepository.getDisciplineByEmployer(employer);
    }

    @Override
    public void addDesciplineForEmployee(Long uid, Discipline discipline) {
        Employee employer = employerService.getEmployeeById(uid);
        employer.getDisciplines().add(discipline);
        discipline.setEmployer(employer);
        disciplineRepository.save(discipline);
        employerService.addEmployee(employer);
    }

    @Override
    public void removeDecipline(Discipline discipline) {
        disciplineRepository.delete(discipline);
    }

    @Override
    public void removeDeciplineById(Long uid) {
        disciplineRepository.deleteById(uid);
    }

    @Override
    public void editDeciplien(Discipline discipline) {
        disciplineRepository.save(discipline);
    }

    @Override
    public List<Discipline> getDisciplines() {
        return disciplineRepository.findAll();
    }

    @Override
    public Discipline getDisciplineById(Long uid) {
        return disciplineRepository.getById(uid);
    }
}
