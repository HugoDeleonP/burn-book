package net.burnbook.app.controller;

import net.burnbook.app.dto.publicacao.PublicacaoDTOResponse;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.repository.UsuarioRepository;
import net.burnbook.app.service.FeedService;
import net.burnbook.app.service.PublicacaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/feed")
public class FeedController {

    private final FeedService service;
    private final UsuarioRepository usuarioRepository;
    public FeedController (FeedService service, UsuarioRepository usuarioRepository) {
        this.service = service;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("") //
    public Page<PublicacaoDTOResponse> listarPublicacoesFeed (Pageable pageable) {
        Usuario usuarioMockado = usuarioRepository.getById(100L);

        return service.listaFeedPrincipal(pageable, usuarioMockado);
    }

}
