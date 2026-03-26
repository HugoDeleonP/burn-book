package net.burnbook.app.infra.exception.dto;

public record ErroValidacaoDTO(
        String campo,
        String mensagem
) {
}
