package net.burnbook.app.controller;

import net.burnbook.app.dto.curtida.CurtidaDTORequest;
import net.burnbook.app.dto.curtida.ResultadoCurtidaDTOResponse;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.repository.UsuarioRepository;
import net.burnbook.app.service.ComentarioService;
import net.burnbook.app.service.CurtidaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/curtidas")
public class CurtidaController {

    private final CurtidaService service;
    private final UsuarioRepository usuarioRepository;

    public CurtidaController (CurtidaService service, UsuarioRepository usuarioRepository) {
        this.service = service;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("")
    public ResponseEntity<ResultadoCurtidaDTOResponse> adicionarCurtidaPubli (@RequestBody CurtidaDTORequest curtida) {
        Usuario usuarioMockado = usuarioRepository.getById(1L);

        return ResponseEntity.ok(service.darOuTirarCurtida(curtida, usuarioMockado));
    }
}
