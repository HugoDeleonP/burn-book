package net.burnbook.app.service;

import net.burnbook.app.dto.comentario.ComentarioDTOResponse;
import net.burnbook.app.dto.publicacao.PublicacaoDTORequest;
import net.burnbook.app.dto.publicacao.PublicacaoDTOResponse;
import net.burnbook.app.mapper.PublicacaoMapper;
import net.burnbook.app.model.Categoria;
import net.burnbook.app.model.Publicacao;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.repository.CategoriaRepository;
import net.burnbook.app.repository.CurtidaRepository;
import net.burnbook.app.repository.PublicacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PublicacaoService {

    private final PublicacaoRepository publicacaoRepository;
    private final PublicacaoMapper publicacaoMapper;
    private final CategoriaRepository categoriaRepository;
    private final CurtidaRepository curtidaRepository;

    public PublicacaoService(PublicacaoRepository publicacaoRepository,
                             PublicacaoMapper publicacaoMapper,
                             CategoriaRepository categoriaRepository,
                             CurtidaRepository curtidaRepository){
        this.publicacaoRepository = publicacaoRepository;
        this.publicacaoMapper = publicacaoMapper;
        this.categoriaRepository = categoriaRepository;
        this.curtidaRepository = curtidaRepository;
    }

    public PublicacaoDTOResponse criar(PublicacaoDTORequest publicacaoDTORequest, Usuario logado){

        Categoria categoriaBuscada = categoriaRepository.findById(publicacaoDTORequest.categoriaId())
                .orElseThrow( () -> new RuntimeException("Categoria não encontrada"));

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
                .orElseThrow( () -> new RuntimeException("Publicação não encontrada"));

        if(!publicacaoBuscada.getAutor().getId().equals(logado.getId())){
            throw new RuntimeException("O usuário não tem permissão de editar publicação de outro");
        }

        publicacaoBuscada.setConteudo(publicacaoDTORequest.conteudo());

        Publicacao publicacaoAtualizada = publicacaoRepository.save(publicacaoBuscada);
        Integer quantidadeCurtidas = curtidaRepository.countByPublicacao(publicacaoAtualizada);
        Boolean curtidoPorMim = curtidaRepository.existsByPublicacaoAndUsuario(publicacaoAtualizada, logado);

        return publicacaoMapper.paraDto(publicacaoAtualizada, quantidadeCurtidas, curtidoPorMim);
    }

    public void deletar(Long publicacaoId, Usuario logado){
        Publicacao publicacaoBuscada = publicacaoRepository.findById(publicacaoId)
                .orElseThrow( () -> new RuntimeException("Publicação não encontrada"));

        if(!publicacaoBuscada.getAutor().getId().equals(logado.getId())){
            throw new RuntimeException("O usuário não tem permissão de deletar a publicação de outro");
        }

        publicacaoRepository.delete(publicacaoBuscada);
    }
}
