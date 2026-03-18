package net.burnbook.app.dto.comentario;

public record ComentarioDTOResponse(
        Long id,
        String autorNome,
        ComentarioPaiResumoResponse comentarioPai,
        String conteudo
) {
}
