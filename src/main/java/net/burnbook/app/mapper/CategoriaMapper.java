package net.burnbook.app.mapper;

import net.burnbook.app.dto.categoria.CategoriaDTORequest;
import net.burnbook.app.dto.categoria.CategoriaDTOResponse;
import net.burnbook.app.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria paraEntidade(CategoriaDTORequest categoriaDTORequest){
        return new Categoria(categoriaDTORequest.nome());
    }

    public CategoriaDTOResponse paraDto( Categoria categoria){
        return new CategoriaDTOResponse(categoria.getId(), categoria.getNome());
    }
}
