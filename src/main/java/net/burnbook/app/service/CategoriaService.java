package net.burnbook.app.service;

import net.burnbook.app.dto.categoria.CategoriaDTOResponse;
import net.burnbook.app.mapper.CategoriaMapper;
import net.burnbook.app.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper){
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    public List<CategoriaDTOResponse> listarTodas(){
        return categoriaRepository.findAll().stream()
                .map(categoriaMapper::paraDto)
                .toList();
    }


}
