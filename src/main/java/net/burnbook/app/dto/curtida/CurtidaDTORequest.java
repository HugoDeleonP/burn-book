package net.burnbook.app.dto.curtida;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CurtidaDTORequest(

        @Positive
        @NotNull
        Long publicacaoId
) {
}
