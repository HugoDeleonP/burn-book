package net.burnbook.app.service;

import jakarta.transaction.Transactional;
import net.burnbook.app.dto.curtida.CurtidaDTORequest;
import net.burnbook.app.dto.curtida.ResultadoCurtidaDTOResponse;
import net.burnbook.app.model.Curtida;
import net.burnbook.app.model.Publicacao;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.repository.CurtidaRepository;
import net.burnbook.app.repository.PublicacaoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurtidaService {

    private final CurtidaRepository curtidaRepository;
    private final PublicacaoRepository publicacaoRepository;

    public CurtidaService(CurtidaRepository curtidaRepository, PublicacaoRepository publicacaoRepository){
        this.curtidaRepository = curtidaRepository;
        this.publicacaoRepository = publicacaoRepository;
    }

    @Transactional
    public ResultadoCurtidaDTOResponse darOuTirarCurtida(CurtidaDTORequest curtidaDTORequest, Usuario logado){
        Publicacao publicacaoBuscada = publicacaoRepository.findById(curtidaDTORequest.publicacaoId())
                .orElseThrow( () -> new RuntimeException("Publicação não encontrada"));

        Optional<Curtida> curtidaExistente = curtidaRepository.findByPublicacaoAndUsuario(publicacaoBuscada, logado);

        boolean estadoCurtidaAtual;

        if(curtidaExistente.isPresent()){
            curtidaRepository.deleteById(curtidaExistente.get().getId());
            estadoCurtidaAtual = false;
        } else {
            Curtida novaCurtida = new Curtida();
            novaCurtida.setPublicacao(publicacaoBuscada);
            novaCurtida.setUsuario(logado);

            curtidaRepository.save(novaCurtida);
            estadoCurtidaAtual = true;
        }

        Integer totalCurtidas = curtidaRepository.countByPublicacao(publicacaoBuscada);

        return new ResultadoCurtidaDTOResponse(estadoCurtidaAtual, totalCurtidas);

    }
}
