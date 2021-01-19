package com.example.amigoscode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Long id;
    @NotBlank(message = "name cannot be blank")
    @Size(min = 2, max = 128, message = "character limit between 2 and 80")
    private String name;
    @Email(message = "email incorrect type")
    private String email;
    @NotNull(message = "date cannot be blank")
    private LocalDate date;
    private Integer age;
}
