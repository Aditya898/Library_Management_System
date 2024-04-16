package com.lms.Admin.controller;

import com.lms.Admin.dto.AcquisitionDTO;
import com.lms.Admin.model.Acquisition;
import com.lms.Admin.service.AcquisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/acquisitions")
public class AcquisitionController {

    @Autowired
    private AcquisitionService acquisitionService;

    @PostMapping
    public ResponseEntity<Acquisition> addAcquisition(@RequestBody AcquisitionDTO acquisitionDTO) {
        return acquisitionService.addAcquisition(acquisitionDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Acquisition> updateAcquisition(@PathVariable String id, @RequestBody AcquisitionDTO acquisitionDTO) {
        return acquisitionService.updateAcquisition(id, acquisitionDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAcquisition(@PathVariable String id) {
        return acquisitionService.deleteAcquisition(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Acquisition> getAcquisitionById(@PathVariable String id) {
        return acquisitionService.getAcquisitionById(id);
    }

    @GetMapping
    public ResponseEntity<List<Acquisition>> getAllAcquisitions() {
        return acquisitionService.getAllAcquisitions();
    }
}
