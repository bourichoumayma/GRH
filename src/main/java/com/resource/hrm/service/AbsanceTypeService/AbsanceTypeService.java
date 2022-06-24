package com.resource.hrm.service.AbsanceTypeService;

import com.resource.hrm.entity.AbsanceType;

import java.util.List;

public interface AbsanceTypeService {
    AbsanceType getAbsanceTypeById(Long valueOf);

    List<AbsanceType> getAbsanceType();
}
