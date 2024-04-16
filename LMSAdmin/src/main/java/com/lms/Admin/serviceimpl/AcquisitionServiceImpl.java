package com.lms.Admin.serviceimpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.Admin.dto.AcquisitionDTO;
import com.lms.Admin.exception.AcquisitionNotFoundException;
import com.lms.Admin.model.Acquisition;
import com.lms.Admin.repository.AcquisitionRepository;
import com.lms.Admin.service.AcquisitionService;

@Service
public class AcquisitionServiceImpl implements AcquisitionService {

    @Autowired
    private AcquisitionRepository acquisitionRepository;

    @Override
    public ResponseEntity<Acquisition> addAcquisition(AcquisitionDTO acquisitionDTO) {
        Acquisition acquisition = new Acquisition();
        BeanUtils.copyProperties(acquisitionDTO, acquisition);
        Acquisition savedAcquisition = acquisitionRepository.save(acquisition);
        return new ResponseEntity<>(savedAcquisition, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Acquisition> updateAcquisition(String id, AcquisitionDTO acquisitionDTO) {
        Acquisition existingAcquisition = acquisitionRepository.findById(id)
                .orElseThrow(() -> new AcquisitionNotFoundException("Acquisition not found with ID: " + id));
        BeanUtils.copyProperties(acquisitionDTO, existingAcquisition);
        Acquisition updatedAcquisition = acquisitionRepository.save(existingAcquisition);
        return new ResponseEntity<>(updatedAcquisition, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteAcquisition(String id) {
        if (!acquisitionRepository.existsById(id)) {
            throw new AcquisitionNotFoundException("Acquisition not found with ID: " + id);
        }
        acquisitionRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<Acquisition>> getAllAcquisitions() {
        List<Acquisition> acquisitions = acquisitionRepository.findAll();
        return new ResponseEntity<>(acquisitions, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Acquisition> getAcquisitionById(String id) {
        Acquisition acquisition = acquisitionRepository.findById(id)
                .orElseThrow(() -> new AcquisitionNotFoundException("Acquisition not found with ID: " + id));
        return new ResponseEntity<>(acquisition, HttpStatus.OK);
    }
}
