package net.burnbook.app.dto.publicacao;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PublicacaoDTORequest(


        @NotBlank
        @Max(value = 300)
        String conteudo,

        @Positive
        @NotNull
        Long categoriaId,

        @NotNull
        Boolean isAnonimo
) {
}
