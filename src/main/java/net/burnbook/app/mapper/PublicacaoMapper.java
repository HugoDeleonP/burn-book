package net.burnbook.app.mapper;

import net.burnbook.app.dto.publicacao.PublicacaoDTORequest;
import net.burnbook.app.dto.publicacao.PublicacaoDTOResponse;
import net.burnbook.app.model.Categoria;
import net.burnbook.app.model.Publicacao;
import net.burnbook.app.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class PublicacaoMapper {

    public Publicacao paraEntidade(
            PublicacaoDTORequest publicacaoDTORequest,
            Categoria categoria
    ){
        return new Publicacao(
                publicacaoDTORequest.conteudo(),
                categoria,
                publicacaoDTORequest.isAnonimo()
        );
    }

    public PublicacaoDTOResponse paraDto(
            Publicacao publicacao, Integer quantidadeCurtidas, Boolean curtidoPorMim
    ){
        return new PublicacaoDTOResponse(
                publicacao.getId(),
                publicacao.getAutor().getUsername(),
                publicacao.getConteudo(),
                publicacao.getCategoria().getNome(),
                publicacao.getIsAnonimo(),
                quantidadeCurtidas,
                curtidoPorMim
        );
    }
}
