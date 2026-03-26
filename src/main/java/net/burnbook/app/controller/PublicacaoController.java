package net.burnbook.app.controller;

import net.burnbook.app.dto.publicacao.PublicacaoDTORequest;
import net.burnbook.app.dto.publicacao.PublicacaoDTOResponse;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.repository.UsuarioRepository;
import net.burnbook.app.service.PublicacaoService;
import net.burnbook.app.service.UsuarioService;
import org.hibernate.query.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/publicacoes")
public class PublicacaoController {

    private final PublicacaoService service;
    private final UsuarioRepository usuarioRepository;
    public PublicacaoController (PublicacaoService service, UsuarioRepository usuarioRepository) {
        this.service = service;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("")
    public PublicacaoDTOResponse criarPublicacao (@RequestBody PublicacaoDTORequest publicacao) {
        Usuario usuarioMockado = usuarioRepository.getById(100L);

        return service.criar(publicacao, usuarioMockado);
    }

    @PutMapping("/{id}")
    public PublicacaoDTOResponse atualizarPublicacao (@PathVariable Long id, @RequestBody PublicacaoDTORequest publicacaoDTORequest) {
        Usuario usuarioMockado = usuarioRepository.getById(100L);

        return service.atualizar(id, publicacaoDTORequest, usuarioMockado);
    }

    @DeleteMapping("/{id}")
    public void deletarPublicacao (@PathVariable Long id) {
        Usuario usuarioMockado = usuarioRepository.getById(100L);

        service.deletar(id, usuarioMockado);
    }

//   @GetMapping("/{publicacaoId}/comentarios")
//   public Page<ComentarioDTOResponse> listarComentarios () {
//        return service
//    }

}
