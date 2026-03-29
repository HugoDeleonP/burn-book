package net.burnbook.app.dto.comentario;

import java.time.LocalDateTime;

public record ComentarioDTOResponse(
        Long id,
        String autorNome,
        ComentarioPaiResumoResponse comentarioPai,
        String conteudo,
        LocalDateTime dataHora

) {
}
