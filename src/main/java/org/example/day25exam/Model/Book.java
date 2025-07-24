package org.example.day25exam.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    @NotEmpty(message = "ID Should not be empty")
    private String ID;
    @NotEmpty(message = "Name Should not be empty")
    private String name;
    @NotNull(message = "Number Of Pages Should not be empty")
    private int number_of_pages;
    @NotNull(message = "Price Should not be empty")
    private double price;
    @NotEmpty(message = "category Should not be empty")
    @Pattern(regexp = "^(novel|academic)$")
    private String category;
    @NotNull(message = "is Available Should not be empty ")
    private boolean isAvailable;

}
