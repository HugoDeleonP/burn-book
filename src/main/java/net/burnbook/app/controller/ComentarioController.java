package net.burnbook.app.controller;

import jakarta.validation.Valid;
import net.burnbook.app.dto.comentario.ComentarioDTORequest;
import net.burnbook.app.dto.comentario.ComentarioDTOResponse;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.repository.UsuarioRepository;
import net.burnbook.app.service.CategoriaService;
import net.burnbook.app.service.ComentarioService;
import org.hibernate.query.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    private final ComentarioService service;

    public ComentarioController (ComentarioService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<ComentarioDTOResponse> adicionarComentario (
            @Valid @RequestBody ComentarioDTORequest comentario,
            @AuthenticationPrincipal(expression = "usuario") Usuario usuarioLogado) {

        return ResponseEntity.ok(service.comentar(comentario, usuarioLogado));
    }

    @DeleteMapping("/{id}")
    public void deletarComentario (
            @PathVariable Long id,
            @AuthenticationPrincipal(expression = "usuario") Usuario usuarioLogado) {

        service.deletar(id, usuarioLogado);
        ResponseEntity.ok("Removido com sucesso");
    }

}
