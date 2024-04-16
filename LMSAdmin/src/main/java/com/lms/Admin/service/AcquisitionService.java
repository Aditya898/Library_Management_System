package com.lms.Admin.service;

import com.lms.Admin.dto.AcquisitionDTO;
import com.lms.Admin.model.Acquisition;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AcquisitionService {
    ResponseEntity<Acquisition> addAcquisition(AcquisitionDTO acquisitionDTO);
    ResponseEntity<Acquisition> updateAcquisition(String id, AcquisitionDTO acquisitionDTO);
    ResponseEntity<Void> deleteAcquisition(String id);
    ResponseEntity<List<Acquisition>> getAllAcquisitions();
    ResponseEntity<Acquisition> getAcquisitionById(String id);
}
