package net.burnbook.app.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record FotoPerfilDTORequest(

        @NotBlank(message = "A URL da foto não pode ser vazia")
        @URL(message = "A URL da foto deve ser válida")
        String fotoPerfilUrl

) {
}
