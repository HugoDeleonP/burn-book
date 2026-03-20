package net.burnbook.app.dto.publicacao;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PublicacaoDTORequest(


        @NotBlank(message = "O conteúdo não deve ser vazio")
        @Max(value = 300)
        String conteudo,

        @Positive(message = "O ID da categoria deve ser positiva")
        @NotNull(message = "O ID da categoria não pode ser nulo")
        Long categoriaId,

        @NotNull(message = "O estado de anônimo não deve ser nulo")
        Boolean isAnonimo
) {
}
