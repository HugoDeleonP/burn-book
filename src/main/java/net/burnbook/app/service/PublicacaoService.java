package net.burnbook.app.service;

import net.burnbook.app.dto.publicacao.PublicacaoDTORequest;
import net.burnbook.app.dto.publicacao.PublicacaoDTOResponse;
import net.burnbook.app.infra.exception.model.AcessoNegadoException;
import net.burnbook.app.infra.exception.model.EntidadeNaoEncontradaException;
import net.burnbook.app.mapper.PublicacaoMapper;
import net.burnbook.app.model.Categoria;
import net.burnbook.app.model.Publicacao;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.repository.CategoriaRepository;
import net.burnbook.app.repository.CurtidaRepository;
import net.burnbook.app.repository.PublicacaoRepository;
import net.burnbook.app.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class PublicacaoService {

    private final PublicacaoRepository publicacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PublicacaoMapper publicacaoMapper;
    private final CategoriaRepository categoriaRepository;
    private final CurtidaRepository curtidaRepository;

    public PublicacaoService(PublicacaoRepository publicacaoRepository,
                             PublicacaoMapper publicacaoMapper,
                             CategoriaRepository categoriaRepository,
                             CurtidaRepository curtidaRepository,
                             UsuarioRepository usuarioRepository){
        this.publicacaoRepository = publicacaoRepository;
        this.publicacaoMapper = publicacaoMapper;
        this.categoriaRepository = categoriaRepository;
        this.curtidaRepository = curtidaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public PublicacaoDTOResponse criar(PublicacaoDTORequest publicacaoDTORequest, Usuario logado){

        Categoria categoriaBuscada = categoriaRepository.findById(publicacaoDTORequest.categoriaId())
                .orElseThrow( () -> new EntidadeNaoEncontradaException("Categoria não encontrada"));

        Publicacao publicacao = new Publicacao(
                logado,
                publicacaoDTORequest.conteudo(),
                categoriaBuscada,
                publicacaoDTORequest.isAnonimo(),
                LocalDateTime.now()
        );

        return publicacaoMapper.paraDto(publicacaoRepository.save(publicacao), 0, false);


    }

    public PublicacaoDTOResponse atualizar(Long publicacaoId, PublicacaoDTORequest publicacaoDTORequest, Usuario logado){

        Publicacao publicacaoBuscada = publicacaoRepository.findById(publicacaoId)
                .orElseThrow( () -> new EntidadeNaoEncontradaException("Publicação não encontrada"));

        if(!publicacaoBuscada.getAutor().getId().equals(logado.getId())){
            throw new AcessoNegadoException("O usuário não tem permissão de editar publicação de outro");
        }

        publicacaoBuscada.setConteudo(publicacaoDTORequest.conteudo());

        Publicacao publicacaoAtualizada = publicacaoRepository.save(publicacaoBuscada);
        Integer quantidadeCurtidas = curtidaRepository.countByPublicacao(publicacaoAtualizada);
        Boolean curtidoPorMim = curtidaRepository.existsByPublicacaoAndUsuario(publicacaoAtualizada, logado);

        return publicacaoMapper.paraDto(publicacaoAtualizada, quantidadeCurtidas, curtidoPorMim);
    }

    public void deletar(Long publicacaoId, Usuario logado){
        Publicacao publicacaoBuscada = publicacaoRepository.findById(publicacaoId)
                .orElseThrow( () -> new EntidadeNaoEncontradaException ("Publicação não encontrada"));

        if(!publicacaoBuscada.getAutor().getId().equals(logado.getId())){
            throw new AcessoNegadoException ("O usuário não tem permissão de deletar a publicação de outro");
        }

        publicacaoRepository.delete(publicacaoBuscada);
    }

    public Page<PublicacaoDTOResponse> listarPorUsuario(Long usuarioId, Pageable pageable, Usuario logado){

        Usuario usuarioBuscado = usuarioRepository.findById(usuarioId)
                .orElseThrow( () -> new EntidadeNaoEncontradaException("Usuário não encontrada"));

        Page<Publicacao> paginaPublicacoesPorAutor = publicacaoRepository.findByAutorOrderByDataHoraDesc(usuarioBuscado, pageable);

        return paginaPublicacoesPorAutor.map( publicacao -> new PublicacaoDTOResponse(
                publicacao.getId(),
                publicacao.getIsAnonimo() ? null : publicacao.getAutor().getUsername(),
                publicacao.getConteudo(),
                publicacao.getCategoria().getNome(),
                publicacao.getIsAnonimo(),
                curtidaRepository.countByPublicacao(publicacao),
                curtidaRepository.existsByPublicacaoAndUsuario(publicacao, logado),
                publicacao.getDataHora()
        ));

    }

    public Page<PublicacaoDTOResponse> listarPorCategoria(String categoriaNome, Pageable pageable){

        Page<Publicacao> paginaPublicacoesPorCategoria = publicacaoRepository.findByCategoriaNomeOrderByDataHoraDesc(categoriaNome, pageable);


        return paginaPublicacoesPorCategoria.map( publicacao -> new PublicacaoDTOResponse(
                publicacao.getId(),
                publicacao.getIsAnonimo() ? null : publicacao.getAutor().getUsername(),
                publicacao.getConteudo(),
                publicacao.getCategoria().getNome(),
                publicacao.getIsAnonimo(),
                curtidaRepository.countByPublicacao(publicacao),
                false,
                publicacao.getDataHora()
        ));

    }

    public PublicacaoDTOResponse listarPorId(Long id, Usuario logado){
        Publicacao publicacaoBuscada = publicacaoRepository.findById(id)
                .orElseThrow( () -> new EntidadeNaoEncontradaException("Publicação não encontrada!"));

        return publicacaoMapper.paraDto(
                publicacaoBuscada,
                curtidaRepository.countByPublicacao(publicacaoBuscada),
                curtidaRepository.existsByPublicacaoAndUsuario(publicacaoBuscada, logado)
                );

    }
}
