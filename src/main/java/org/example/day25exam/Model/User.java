package org.example.day25exam.Model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message = "ID should not be empty")
    private String ID;
    @NotEmpty(message = "ID should not be empty")
    private String name;
    @NotNull(message = "ID should not be empty")
    private int age;
    @NotNull(message = "ID should not be empty")
    @Positive
    private double balance;
    @NotEmpty(message = "ID should not be empty")
    @Pattern(regexp = "^(customer|librarian)$")
    private String role;
}
