package net.burnbook.app.service;

import net.burnbook.app.dto.comentario.ComentarioDTORequest;
import net.burnbook.app.dto.comentario.ComentarioDTOResponse;
import net.burnbook.app.infra.exception.model.AcessoNegadoException;
import net.burnbook.app.infra.exception.model.EntidadeNaoEncontradaException;
import net.burnbook.app.mapper.ComentarioMapper;
import net.burnbook.app.model.Comentario;
import net.burnbook.app.model.Publicacao;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.repository.ComentarioRepository;
import net.burnbook.app.repository.PublicacaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
            .orElseThrow( () -> new EntidadeNaoEncontradaException("Publicação não encontrada"));

        Comentario comentarioPai = null;

        if(comentarioDTORequest.comentarioPaiId() != null){
            comentarioPai = comentarioRepository.findById(comentarioDTORequest.comentarioPaiId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Comentário pai não encontrado"));
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
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Comentário não encontrado"));

        if(!comentarioBuscado.getAutor().getId().equals(logado.getId())){
            throw new AcessoNegadoException("O usuário não tem permissão de deletar o comentário de outro");
        }

        comentarioRepository.delete(comentarioBuscado);
    }

    public Page<ComentarioDTOResponse> listarComentariosRaizDaPublicacao(Long publicacaoId, Pageable pageable){

        Publicacao publicacaoBuscada = publicacaoRepository.findById(publicacaoId)
                .orElseThrow( () -> new EntidadeNaoEncontradaException("Publicação não encontrada"));

        Page<Comentario> paginaComentarios = comentarioRepository.buscarComentariosDaPublicacao(publicacaoBuscada.getId(), pageable);

        return paginaComentarios.map(comentarioMapper::paraDto);

    }

    public List<ComentarioDTOResponse> listarRespostas(Long comentarioPaiId
    ){
        Comentario comentarioPaiBuscado = comentarioRepository.findById(comentarioPaiId)
                .orElseThrow( () -> new EntidadeNaoEncontradaException("Comentário pai não encontrado."));

        List<Comentario> comentariosFilho = comentarioRepository.findByComentarioPaiOrderByDataHoraAsc(comentarioPaiBuscado);

        return comentariosFilho.stream().map(comentarioMapper::paraDto)
                .toList();
    }


}
