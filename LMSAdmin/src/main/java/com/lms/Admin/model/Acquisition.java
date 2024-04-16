package com.lms.Admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "acquisitions")
public class Acquisition {
    @Id
    private String id;
    private String bookId;
    private String acquisitionType;
    private Date acquisitionDate;
   
}
