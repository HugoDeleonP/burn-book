package net.burnbook.app.controller;

import net.burnbook.app.dto.publicacao.PublicacaoDTOResponse;
import net.burnbook.app.model.Usuario;
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
    public FeedController (FeedService service) {
        this.service = service;
    }

    @GetMapping("") //
    public Page<PublicacaoDTOResponse> listarPublicacoesFeed (Pageable pageable) {
        Usuario logado = new Usuario("teste_1", "teste", LocalDate.of(2000,12,12), "teste@gmail.com", "123", "123.123.123-21", "url.com");

        return service.listaFeedPrincipal(pageable, logado);
    }

}
