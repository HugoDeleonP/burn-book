package net.burnbook.app.service;

import net.burnbook.app.dto.publicacao.PublicacaoDTOResponse;
import net.burnbook.app.mapper.PublicacaoMapper;
import net.burnbook.app.model.Publicacao;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.repository.CurtidaRepository;
import net.burnbook.app.repository.PublicacaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedService {

    private PublicacaoRepository publicacaoRepository;
    private PublicacaoMapper publicacaoMapper;
    private CurtidaRepository curtidaRepository;

    public FeedService(
            PublicacaoRepository publicacaoRepository,
            PublicacaoMapper publicacaoMapper,
            CurtidaRepository curtidaRepository
    ){
        this.curtidaRepository = curtidaRepository;
        this.publicacaoMapper = publicacaoMapper;
        this.publicacaoRepository = publicacaoRepository;
    }

    @Transactional(readOnly = true)
    public Page<PublicacaoDTOResponse> listaFeedPrincipal(Pageable pageable, Usuario logado){
        Page<Publicacao> paginaPublicacoes = publicacaoRepository.findFeedOtimizado(pageable);

        return paginaPublicacoes.map(
                publicacao -> {

                    Integer totalCurtidas = curtidaRepository.countByPublicacao(publicacao);
                    boolean curtidoPorMim = false;

                    if(logado != null){
                        curtidoPorMim = curtidaRepository.existsByPublicacaoAndUsuario(publicacao, logado);
                    }

                    return publicacaoMapper.paraDto(publicacao, totalCurtidas, curtidoPorMim);

                }
        );
    }
}
