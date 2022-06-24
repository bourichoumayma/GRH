package com.resource.hrm.service.AbsanceService.impl;

import com.resource.hrm.entity.Absance;
import com.resource.hrm.repository.AbsanceRepository;
import com.resource.hrm.service.AbsanceService.AbsanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AbsanceServiceImpl implements AbsanceService {


    @Autowired
    private AbsanceRepository absanceRepository;

    @Override
    public List<Absance> getAbsances() {
        return absanceRepository.findAll();
    }

    @Override
    public void addAbsance(Absance absance) {
        absanceRepository.save(absance);
    }

    @Override
    public Absance getAbsanceById(Long id) {
        return absanceRepository.getById(id);
    }

    @Override
    public void removeAbsance(long id) {
        absanceRepository.delete(absanceRepository.getById(id));
    }


}
