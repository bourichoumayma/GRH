package com.resource.hrm.service.AbsanceService;

import com.resource.hrm.entity.Absance;

import java.util.List;

public interface AbsanceService {
    List<Absance> getAbsances();

    void addAbsance(Absance build);

    Absance getAbsanceById(Long id);

    void removeAbsance(long id);
}
