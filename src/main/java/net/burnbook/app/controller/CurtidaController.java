package net.burnbook.app.controller;

import net.burnbook.app.dto.curtida.CurtidaDTORequest;
import net.burnbook.app.dto.curtida.ResultadoCurtidaDTOResponse;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.service.ComentarioService;
import net.burnbook.app.service.CurtidaService;
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
    public ResultadoCurtidaDTOResponse adicionarCurtidaPubli (@RequestBody CurtidaDTORequest curtida) {
        Usuario logado = new Usuario("teste_1", "teste", LocalDate.of(2000,12,12), "teste@gmail.com", "123", "123.123.123-21", "url.com");

        return service.darOuTirarCurtida(curtida, logado);
    }
}
