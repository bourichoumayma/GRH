package com.resource.hrm.service.DeciplineService;

import com.resource.hrm.entity.Discipline;

import java.util.List;

public interface DisciplineService {

    List<Discipline> getDesciplineForEmployee(Long uid);

    void addDesciplineForEmployee(Long uid, Discipline discipline);

    void removeDecipline(Discipline discipline);

    void removeDeciplineById(Long uid);

    void editDeciplien(Discipline discipline);

    List<Discipline> getDisciplines();

    Discipline getDisciplineById(Long uid);
}
