package net.burnbook.app.dto.publicacao;

import jakarta.validation.constraints.*;

public record PublicacaoDTORequest(


        @NotBlank(message = "O conteúdo não deve ser vazio")
        @Size(max = 250, message = "O conteúdo deve ter no máximo 250 caracteres")
        String conteudo,

        @Positive(message = "O ID da categoria deve ser positiva")
        @NotNull(message = "O ID da categoria não pode ser nulo")
        Long categoriaId,

        @NotNull(message = "O estado de anônimo não deve ser nulo")
        Boolean isAnonimo
) {
}
