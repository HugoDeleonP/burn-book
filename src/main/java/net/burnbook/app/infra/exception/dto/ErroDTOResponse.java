package net.burnbook.app.infra.exception.dto;

import java.time.LocalDateTime;

public record ErroDTOResponse(
        int status,
        String erro,
        String mensagem,
        LocalDateTime timestamp
) {
}
