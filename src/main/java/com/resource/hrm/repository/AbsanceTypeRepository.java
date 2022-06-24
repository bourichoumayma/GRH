package com.resource.hrm.repository;

import com.resource.hrm.entity.AbsanceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface AbsanceTypeRepository extends JpaRepository<AbsanceType,Long> {
}
