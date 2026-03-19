package net.burnbook.app.dto.curtida;

import java.util.List;

public record ResultadoCurtidaDTOResponse(
        Boolean estadoCurtida,
        Integer novaQuantidadeCurtidas
) {
}
