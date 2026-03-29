package net.burnbook.app.controller;

import net.burnbook.app.dto.publicacao.PublicacaoDTOResponse;
import jakarta.validation.Valid;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<UsuarioDTOResponse> cadastrarUsuario (@Valid @RequestBody UsuarioDTORequest user) {
        return ResponseEntity.ok(service.cadastrar(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTOResponse> listarUsuario (@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPefilPorId(id));
    }

    @PatchMapping("/foto")
    public ResponseEntity<UsuarioDTOResponse> atualizarFotoPerfil (@PathVariable Long id, String fotoUrl) {
        return ResponseEntity.ok(service.adicionarFotoPerfil(id, fotoUrl));
    }

    @GetMapping("/{usuarioId}/publicacoes")
    public ResponseEntity<Page<PublicacaoDTOResponse>> listarPublicacoesUser (
            @PathVariable Long usuarioId,
            @RequestParam Integer page,
            @RequestParam Integer size,
            @AuthenticationPrincipal(expression = "usuario") Usuario usuarioLogado
            ) {

        return ResponseEntity.ok(publicacaoService.listarPorUsuario(usuarioId,
                PageRequest.of(page, size),
                usuarioLogado));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UsuarioDTOResponse> buscarPorUsername(
            @PathVariable String username) {
        return ResponseEntity.ok(service.buscarPorUsername(username));
    }

}
