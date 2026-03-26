package net.burnbook.app.controller;

import net.burnbook.app.dto.categoria.CategoriaDTOResponse;
import net.burnbook.app.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService service;
    public CategoriaController (CategoriaService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<CategoriaDTOResponse>> listarCategorias () {
        return ResponseEntity.ok(service.listarTodas());
    }
}
