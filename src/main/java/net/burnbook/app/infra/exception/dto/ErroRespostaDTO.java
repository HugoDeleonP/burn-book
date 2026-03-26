package net.burnbook.app.infra.exception.dto;

import java.time.LocalDateTime;

public record ErroRespostaDTO(
        LocalDateTime timestamp,
        Integer status,
        String erro,
        String mensagem,
        String path
) {
}
