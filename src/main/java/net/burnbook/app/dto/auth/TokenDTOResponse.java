package net.burnbook.app.dto.auth;

public record TokenDTOResponse(
        String token,
        String tipo
) {
}
