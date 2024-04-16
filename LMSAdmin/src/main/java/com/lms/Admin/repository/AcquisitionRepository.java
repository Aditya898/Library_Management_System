package com.lms.Admin.repository;

import com.lms.Admin.model.Acquisition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquisitionRepository extends MongoRepository<Acquisition, String> {
    
}
