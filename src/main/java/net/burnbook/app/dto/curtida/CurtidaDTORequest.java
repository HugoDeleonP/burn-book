package net.burnbook.app.dto.curtida;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CurtidaDTORequest(

        @Positive(message = "O ID da publicação deve ser positiva")
        @NotNull(message = "O ID da publicação não pode ser nulo")
        Long publicacaoId
) {
}
