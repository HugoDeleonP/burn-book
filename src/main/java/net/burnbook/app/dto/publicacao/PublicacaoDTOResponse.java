package net.burnbook.app.dto.publicacao;

import net.burnbook.app.model.Comentario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record PublicacaoDTOResponse(
        Long id,
        String usernameAutor,
        String conteudo,
        String categoriaNome,
        Boolean isAnonimo,
        Integer quantidadeCurtidas,
        Boolean curtidoPorMim,
        LocalDateTime dataHora
) {
}
