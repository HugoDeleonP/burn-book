package net.burnbook.app.dto.comentario;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ComentarioDTORequest(

        @Positive
        @NotNull
        Long publicacaoId,

        @Positive
        @NotNull
        Long comentarioPaiId,

        @NotBlank
        @Max(value = 300)
        String conteudo

) {
}
