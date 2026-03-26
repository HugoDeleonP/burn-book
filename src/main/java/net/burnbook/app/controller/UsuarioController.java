package net.burnbook.app.controller;

import net.burnbook.app.dto.publicacao.PublicacaoDTOResponse;
import net.burnbook.app.dto.usuario.UsuarioDTORequest;
import net.burnbook.app.dto.usuario.UsuarioDTOResponse;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.repository.UsuarioRepository;
import net.burnbook.app.service.CategoriaService;
import net.burnbook.app.service.PublicacaoService;
import net.burnbook.app.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/usuarios")
public class UsuarioController {


    private final UsuarioService service;
    private final PublicacaoService publicacaoService;
    private final UsuarioRepository usuarioRepository;

    public UsuarioController (UsuarioService service, PublicacaoService publicacaoService, UsuarioRepository usuarioRepository) {
        this.service = service;
        this.publicacaoService = publicacaoService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("")
    public UsuarioDTOResponse cadastrarUsuario (@RequestBody UsuarioDTORequest user) {
        return service.cadastrar(user);
    }

    @GetMapping("/{id}")
    public UsuarioDTOResponse listarUsuario (@PathVariable Long id) {
        return service.buscarPefilPorId(id);
    }

    @PatchMapping("/foto")
    public UsuarioDTOResponse atualizarFotoPerfil (@PathVariable Long id, String fotoUrl) {
        return service.adicionarFotoPerfil(id, fotoUrl);
    }

    @GetMapping("/{usuarioId}/publicacoes")
    public Page<PublicacaoDTOResponse> listarPublicacoesUser (
            @PathVariable Long usuarioId,
            @RequestParam Integer page,
            @RequestParam Integer size
            ) {
        Usuario usuarioMockado = usuarioRepository.getById(1L);
        return publicacaoService.listarPorUsuario(usuarioId, PageRequest.of(page, size), usuarioMockado);
    }
}
