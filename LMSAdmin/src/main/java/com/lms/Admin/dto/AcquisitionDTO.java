package com.lms.Admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcquisitionDTO {
    private String bookId;
    private String acquisitionType;
    private Date acquisitionDate;
    
}
