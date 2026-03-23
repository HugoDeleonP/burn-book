package net.burnbook.app.dto.comentario;

import java.time.LocalDateTime;

public record ComentarioPaiResumoResponse(

        String autorNome,
        String conteudo,
        LocalDateTime dataHora
) {
}
