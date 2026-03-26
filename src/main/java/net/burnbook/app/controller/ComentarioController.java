package net.burnbook.app.controller;

import net.burnbook.app.dto.comentario.ComentarioDTORequest;
import net.burnbook.app.dto.comentario.ComentarioDTOResponse;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.repository.UsuarioRepository;
import net.burnbook.app.service.CategoriaService;
import net.burnbook.app.service.ComentarioService;
import org.hibernate.query.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    private final ComentarioService service;
    private final UsuarioRepository usuarioRepository;

    public ComentarioController (ComentarioService service, UsuarioRepository usuarioRepository) {
        this.service = service;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("")
    public ComentarioDTOResponse adicionarComentario (@RequestBody ComentarioDTORequest comentario) {
        Usuario usuarioMockado = usuarioRepository.getById(2L);

        return service.comentar(comentario, usuarioMockado);
    }

    @DeleteMapping("/{id}")
    public void deletarComentario (@PathVariable Long id) {
        Usuario usuarioMockado = usuarioRepository.getById(1L);

        service.deletar(id, usuarioMockado);
    }

}
