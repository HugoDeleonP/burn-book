package net.burnbook.app.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UsuarioDTORequest(

        @NotBlank
        String username,

        @NotBlank
        String nome,

        @NotNull
        @Past
        LocalDate dataNascimento,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String senha,

        @NotBlank
        @CPF
        String CPF,

        String fotoPerfilUrl
) {
}
