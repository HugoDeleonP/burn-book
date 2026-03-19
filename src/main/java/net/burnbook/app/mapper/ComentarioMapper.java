package net.burnbook.app.mapper;

import net.burnbook.app.dto.comentario.ComentarioDTORequest;
import net.burnbook.app.dto.comentario.ComentarioDTOResponse;
import net.burnbook.app.dto.comentario.ComentarioPaiResumoResponse;
import net.burnbook.app.model.Comentario;
import net.burnbook.app.model.Publicacao;
import net.burnbook.app.model.Usuario;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ComentarioMapper {

    public Comentario paraEntidade(
          ComentarioDTORequest comentarioDTORequest,
          Publicacao publicacao,
          Comentario comentarioPai

    ){
        return new Comentario(
                publicacao,
                comentarioPai,
                comentarioDTORequest.conteudo()
        );
    }

    public ComentarioDTOResponse paraDto(
        Comentario comentario
    ){

        ComentarioPaiResumoResponse comentarioPai = null;

        if(comentario.getComentarioPai() != null){
            comentarioPai = new ComentarioPaiResumoResponse(
                    comentario.getComentarioPai().getAutor().getUsername(),
                    comentario.getComentarioPai().getConteudo(),
                    comentario.getComentarioPai().getDataHora()
            );
        }

        return new ComentarioDTOResponse(
                comentario.getId(),
                comentario.getAutor().getNome(),
                comentarioPai,
                comentario.getConteudo(),
                comentario.getDataHora()
        );
    }
}
