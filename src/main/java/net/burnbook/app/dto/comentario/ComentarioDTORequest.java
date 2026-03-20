package net.burnbook.app.dto.comentario;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ComentarioDTORequest(

        @Positive(message = "O ID da publicação deve ser positiva")
        @NotNull(message = "O ID da publicação não pode ser nulo")
        Long publicacaoId,

        @Positive(message = "O ID do comentario pai deve ser positivo")
        Long comentarioPaiId,

        @NotBlank(message = "O conteúdo não deve ser vazio")
        @Max(value = 300)
        String conteudo

) {
}
