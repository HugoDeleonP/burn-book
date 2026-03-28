package net.burnbook.app.controller;

import net.burnbook.app.dto.comentario.ComentarioDTOResponse;
import jakarta.validation.Valid;
import net.burnbook.app.dto.publicacao.PublicacaoDTORequest;
import net.burnbook.app.dto.publicacao.PublicacaoDTOResponse;
import net.burnbook.app.model.Comentario;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.repository.UsuarioRepository;
import net.burnbook.app.service.ComentarioService;
import net.burnbook.app.service.PublicacaoService;
import net.burnbook.app.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/publicacoes")
public class PublicacaoController {

    private final PublicacaoService service;
    private final ComentarioService comentarioService;
    public PublicacaoController (PublicacaoService service,
                                 ComentarioService comentarioService) {
        this.service = service;
        this.comentarioService = comentarioService;
    }

    @PostMapping("")
    public ResponseEntity<PublicacaoDTOResponse> criarPublicacao (
            @Valid @RequestBody PublicacaoDTORequest publicacao,
            @AuthenticationPrincipal(expression = "usuario") Usuario usuarioLogado
    ) {

        return ResponseEntity.ok(service.criar(publicacao, usuarioLogado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacaoDTOResponse>  atualizarPublicacao (
            @PathVariable Long id,
            @Valid @RequestBody PublicacaoDTORequest publicacaoDTORequest,
            @AuthenticationPrincipal(expression = "usuario") Usuario usuarioLogado
    ) {
        return ResponseEntity.ok(service.atualizar(id, publicacaoDTORequest, usuarioLogado));
    }

    @DeleteMapping("/{id}")
    public void deletarPublicacao ( @PathVariable Long id, @AuthenticationPrincipal(expression = "usuario") Usuario usuarioLogado
    ) {

        service.deletar(id, usuarioLogado);

        ResponseEntity.ok("Removido com sucesso");
    }

   @GetMapping("/{publicacaoId}/comentarios")
   public ResponseEntity<Page<ComentarioDTOResponse>> listarComentariosPai (
           @PathVariable Long publicacaoId,
           @RequestParam Integer page,
           @RequestParam Integer size
   ) {
        return ResponseEntity.ok(comentarioService.listarComentariosRaizDaPublicacao(publicacaoId, PageRequest.of(page, size)));
    }

}
