package net.burnbook.app.controller;

import jakarta.validation.Valid;
import net.burnbook.app.dto.curtida.CurtidaDTORequest;
import net.burnbook.app.dto.curtida.ResultadoCurtidaDTOResponse;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.repository.UsuarioRepository;
import net.burnbook.app.service.ComentarioService;
import net.burnbook.app.service.CurtidaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/curtidas")
public class CurtidaController {

    private final CurtidaService service;

    public CurtidaController (CurtidaService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<ResultadoCurtidaDTOResponse> adicionarCurtidaPubli (
            @Valid @RequestBody CurtidaDTORequest curtida,
            @AuthenticationPrincipal(expression = "usuario") Usuario usuarioLogado) {

        return ResponseEntity.ok(service.darOuTirarCurtida(curtida, usuarioLogado));
    }
}
