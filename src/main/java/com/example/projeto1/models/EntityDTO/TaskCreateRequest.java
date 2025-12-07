
package  com.example.projeto1.models.EntityDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record TaskCreateRequest(
        @NotBlank(message = "The title field must not be empty.")
        @Size(min = 3, max = 40, message = "The title field must contain between 3 and 100 characters.")
        String title,

        @Size(min = 3, max = 255, message = "The title field must contain between 3 and 100 characters.")
        String description,

        @NotNull(message = "The done field must not be empty.")
        Boolean done,

        @NotEmpty(message = "The task must contain some label.")
        List<Long> labelIds){

}

