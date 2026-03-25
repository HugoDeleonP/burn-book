package net.burnbook.app.controller;

import net.burnbook.app.dto.comentario.ComentarioDTORequest;
import net.burnbook.app.dto.comentario.ComentarioDTOResponse;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.service.CategoriaService;
import net.burnbook.app.service.ComentarioService;
import org.hibernate.query.Page;
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
    public ComentarioDTOResponse adicionarComentario (@RequestBody ComentarioDTORequest comentario) {
        Usuario logado = new Usuario("teste_1", "teste", LocalDate.of(2000,12,12), "teste@gmail.com", "123", "123.123.123-21", "url.com");

        return service.comentar(comentario, logado);
    }

    @DeleteMapping("/{id}")
    public void deletarComentario (@PathVariable Long id) {
        Usuario logado = new Usuario("teste_1", "teste", LocalDate.of(2000,12,12), "teste@gmail.com", "123", "123.123.123-21", "url.com");

        service.deletar(id, logado);
    }

//    @GetMapping("/publicacoes/{publicacaoId}/comentarios") // rota correta?
//   public Page<ComentarioDTOResponse> listarComentarios () {
//        return service
//    }
}
