package net.burnbook.app.controller;

import net.burnbook.app.dto.publicacao.PublicacaoDTORequest;
import net.burnbook.app.dto.publicacao.PublicacaoDTOResponse;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.service.PublicacaoService;
import net.burnbook.app.service.UsuarioService;
import org.hibernate.query.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/publicacoes")
public class PublicacaoController {

    private final PublicacaoService service;
    public PublicacaoController (PublicacaoService service) {
        this.service = service;
    }

    @PostMapping("")
    public PublicacaoDTOResponse criarPublicacao (@RequestBody PublicacaoDTORequest publicacao) {
        Usuario usuarioMockado = new Usuario("teste_1", "teste", LocalDate.of(2000,12,12), "teste@gmail.com", "123", "123.123.123-21", "url.com");

        return service.criar(publicacao, usuarioMockado);
    }

    @PutMapping("{id}")
    public PublicacaoDTOResponse atualizarPublicacao (@PathVariable Long publicacaoId, @RequestBody PublicacaoDTORequest publicacaoDTORequest) {
        Usuario usuarioMockado = new Usuario("teste_1", "teste", LocalDate.of(2000,12,12), "teste@gmail.com", "123", "123.123.123-21", "url.com");

        return service.atualizar(publicacaoId, publicacaoDTORequest, usuarioMockado);
    }

    @DeleteMapping("/{id}")
    public void deletarPublicacao (@PathVariable Long id) {
        Usuario usuarioMockado = new Usuario("teste_1", "teste", LocalDate.of(2000,12,12), "teste@gmail.com", "123", "123.123.123-21", "url.com");

        service.deletar(id, usuarioMockado);
    }

//   @GetMapping("/{usuarioId}/publicacoes")
//   public Page<PublicacaoDTOResponse> listarPublicacoesUser (@PathVariable Long id) {
//
//   }

}
