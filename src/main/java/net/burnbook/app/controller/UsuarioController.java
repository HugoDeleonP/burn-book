package net.burnbook.app.controller;

import net.burnbook.app.dto.usuario.UsuarioDTORequest;
import net.burnbook.app.dto.usuario.UsuarioDTOResponse;
import net.burnbook.app.service.CategoriaService;
import net.burnbook.app.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/usuarios")
public class UsuarioController {


    private final UsuarioService service;
    public UsuarioController (UsuarioService service) {
        this.service = service;
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
}
