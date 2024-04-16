package com.lms.Admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private String title;
    private String author;
    private String genre;
    private int publicationYear;
}
