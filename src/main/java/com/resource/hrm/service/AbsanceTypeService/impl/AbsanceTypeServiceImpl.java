package com.resource.hrm.service.AbsanceTypeService.impl;

import com.resource.hrm.entity.AbsanceType;
import com.resource.hrm.repository.AbsanceTypeRepository;
import com.resource.hrm.service.AbsanceService.AbsanceService;
import com.resource.hrm.service.AbsanceTypeService.AbsanceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AbsanceTypeServiceImpl implements AbsanceTypeService {

    @Autowired
    private AbsanceTypeRepository absanceTypeRepository;


    @Override
    public AbsanceType getAbsanceTypeById(Long uid) {
        return absanceTypeRepository.getById(uid);
    }

    @Override
    public List<AbsanceType> getAbsanceType() {
        return absanceTypeRepository.findAll();
    }
}
