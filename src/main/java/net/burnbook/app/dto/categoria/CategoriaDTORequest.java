package net.burnbook.app.dto.categoria;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriaDTORequest(
        @NotBlank
        String nome
) {
}