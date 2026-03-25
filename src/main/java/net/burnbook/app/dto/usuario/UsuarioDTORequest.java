package net.burnbook.app.dto.usuario;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UsuarioDTORequest(

        @NotBlank(message = "O username não pode ser vazio")
        String username,

        @NotBlank(message = "O nome não pode ser nulo")
        String nome,

        @NotNull(message = "A data de nascimento não pode ser nula")
        @Past(message = "A data de nascimento deve ser válida")
        LocalDate dataNascimento,

        @NotBlank(message = "O Email não pode ser vazio")
        @Email(message = "O formato de Email deve ser válido")
        String email,

        @NotBlank(message = "A senha não pode ser vazia")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        String senha,

        @NotBlank(message = "O CPF não pode ser vazio")
        @CPF(message = "O CPF deve ser válido")
        String cpf,

        String fotoPerfilUrl
) {
}
