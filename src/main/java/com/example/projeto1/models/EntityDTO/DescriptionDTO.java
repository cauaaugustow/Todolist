package com.example.projeto1.models.EntityDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DescriptionDTO(
        @NotBlank(message = "The text field must not be empty.")
        @Size(min = 3, max = 255, message = "The title field must contain between 3 and 255 characters.")
        String text,
        @NotNull(message = "A valid id must be required.")
        Long taskId) {
}
