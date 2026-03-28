package net.burnbook.app.controller;

import net.burnbook.app.dto.publicacao.PublicacaoDTOResponse;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.repository.UsuarioRepository;
import net.burnbook.app.service.FeedService;
import net.burnbook.app.service.PublicacaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/feed")
public class FeedController {

    private final FeedService service;
    public FeedController (FeedService service) {
        this.service = service;
    }

    @GetMapping("") //
    public ResponseEntity<Page<PublicacaoDTOResponse>> listarPublicacoesFeed (
            @RequestParam Integer page, @RequestParam Integer size,
            @AuthenticationPrincipal(expression = "usuario") Usuario usuarioLogado
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(service.listaFeedPrincipal(pageable, usuarioLogado));
    }

}
