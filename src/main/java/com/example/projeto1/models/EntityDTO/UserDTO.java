
package  com.example.projeto1.models.EntityDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.example.projeto1.models.Entity.Task;

import java.util.List;

public record UserDTO (Long id,

                       @NotBlank(message = "The name field must not be empty.")
                       @Size(min = 3,max = 100, message = "The name field must contain between 3 and 100 characters.")
                       String name,

                       String lastname,

                       String cpf,

                       @Email(message = "Invalid E-mail.")
                       @NotBlank(message = "The e-mail field must not be empty.")
                       String email,

                       @NotBlank(message = "The password field must not be empty.")
                       @Size(min = 6,max = 60, message = "The password field must contain between 6 and 60 characters.")
                       String password,

                       List<Task> tasks) {}

