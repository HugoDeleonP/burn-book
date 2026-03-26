package net.burnbook.app.controller;

import net.burnbook.app.dto.comentario.ComentarioDTOResponse;
import net.burnbook.app.dto.publicacao.PublicacaoDTORequest;
import net.burnbook.app.dto.publicacao.PublicacaoDTOResponse;
import net.burnbook.app.model.Comentario;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.repository.UsuarioRepository;
import net.burnbook.app.service.ComentarioService;
import net.burnbook.app.service.PublicacaoService;
import net.burnbook.app.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/publicacoes")
public class PublicacaoController {

    private final PublicacaoService service;
    private final UsuarioRepository usuarioRepository;
    private final ComentarioService comentarioService;
    public PublicacaoController (PublicacaoService service,
                                 UsuarioRepository usuarioRepository,
                                 ComentarioService comentarioService) {
        this.service = service;
        this.usuarioRepository = usuarioRepository;
        this.comentarioService = comentarioService;
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

   @GetMapping("/{publicacaoId}/comentarios")
   public Page<ComentarioDTOResponse> listarComentariosPai (
           @PathVariable Long publicacaoId,
           @PageableDefault(size = 10, sort = "dataHora", direction = Sort.Direction.DESC) Pageable pageable
           ) {
        return comentarioService.listarComentariosRaizDaPublicacao(publicacaoId, pageable);
    }

}
