package net.burnbook.app.dto.usuario;

import java.time.LocalDate;

public record UsuarioDTOResponse(
        Long id,
        String username,
        String nome,
        LocalDate dataNascimento,
        String email,
        String fotoPerfilUrl
) {
}
