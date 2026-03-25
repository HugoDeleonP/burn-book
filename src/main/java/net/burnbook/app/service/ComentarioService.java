package net.burnbook.app.service;

import net.burnbook.app.dto.comentario.ComentarioDTORequest;
import net.burnbook.app.dto.comentario.ComentarioDTOResponse;
import net.burnbook.app.mapper.ComentarioMapper;
import net.burnbook.app.model.Comentario;
import net.burnbook.app.model.Publicacao;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.repository.ComentarioRepository;
import net.burnbook.app.repository.PublicacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;
    private final PublicacaoRepository publicacaoRepository;

    public ComentarioService(
            ComentarioRepository comentarioRepository,
            ComentarioMapper comentarioMapper,
            PublicacaoRepository publicacaoRepository){
        this.comentarioRepository = comentarioRepository;
        this.comentarioMapper = comentarioMapper;
        this.publicacaoRepository = publicacaoRepository;
    }

    public ComentarioDTOResponse comentar(ComentarioDTORequest comentarioDTORequest, Usuario logado){

        Publicacao publicacaoBuscada = publicacaoRepository.findById(comentarioDTORequest.publicacaoId())
            .orElseThrow( () -> new RuntimeException("Publicação não encontrada"));

        Comentario comentarioPai = null;

        if(comentarioDTORequest.comentarioPaiId() != null){
            comentarioPai = comentarioRepository.findById(comentarioDTORequest.comentarioPaiId())
                    .orElseThrow(() -> new RuntimeException("Comentário pai não encontrado"));
        }

        Comentario novoComentario = new Comentario();
        novoComentario.setConteudo(comentarioDTORequest.conteudo());
        novoComentario.setDataHora(LocalDateTime.now());
        novoComentario.setAutor(logado);
        novoComentario.setPublicacao(publicacaoBuscada);
        novoComentario.setComentarioPai(comentarioPai);


        return comentarioMapper.paraDto(comentarioRepository.save(novoComentario));
    }

    public void deletar(Long comentarioId, Usuario logado){
        Comentario comentarioBuscado = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new RuntimeException("Comentário não encontrado"));

        if(!comentarioBuscado.getAutor().getId().equals(logado.getId())){
            throw new RuntimeException("O usuário não tem permissão de deletar o comentário de outro");
        }

        comentarioRepository.delete(comentarioBuscado);
    }
}
