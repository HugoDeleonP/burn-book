package net.burnbook.app.dto.comentario;

import jakarta.validation.constraints.*;

public record ComentarioDTORequest(

        @Positive(message = "O ID da publicação deve ser positiva")
        @NotNull(message = "O ID da publicação não pode ser nulo")
        Long publicacaoId,

        @Positive(message = "O ID do comentario pai deve ser positivo")
        Long comentarioPaiId,

        @NotBlank(message = "O conteúdo não deve ser vazio")
        @Size(max = 250, message = "O conteúdo deve ter no máximo 250 caracteres")
        String conteudo

) {
}
