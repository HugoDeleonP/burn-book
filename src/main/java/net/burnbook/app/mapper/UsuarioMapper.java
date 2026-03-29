package net.burnbook.app.mapper;

import net.burnbook.app.dto.usuario.UsuarioDTORequest;
import net.burnbook.app.dto.usuario.UsuarioDTOResponse;
import net.burnbook.app.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario paraEntidade(UsuarioDTORequest usuarioDTORequest) {
        return new Usuario(
                usuarioDTORequest.username(),
                usuarioDTORequest.nome(),
                usuarioDTORequest.dataNascimento(),
                usuarioDTORequest.email(),
                usuarioDTORequest.senha(),
                usuarioDTORequest.cpf(),
                usuarioDTORequest.fotoPerfilUrl()
        );
    }

    public UsuarioDTOResponse paraDto(Usuario usuario){

        return new UsuarioDTOResponse(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getNome(),
                usuario.getDataNascimento(),
                usuario.getEmail(),
                usuario.getFotoPerfilUrl()
        );
    }

}
