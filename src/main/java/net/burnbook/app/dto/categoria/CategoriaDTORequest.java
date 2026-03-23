package net.burnbook.app.dto.categoria;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriaDTORequest(
        @NotBlank(message = "O nome não pode ser vazio")
        String nome
) {
}