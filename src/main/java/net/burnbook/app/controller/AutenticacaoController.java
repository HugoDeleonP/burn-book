package net.burnbook.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.burnbook.app.dto.auth.LoginDTORequest;
import net.burnbook.app.dto.auth.TokenDTOResponse;
import net.burnbook.app.infra.security.auth.JwtService;
import net.burnbook.app.infra.security.auth.model.UsuarioPrincipal;
import net.burnbook.app.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTOResponse> login(@Valid @RequestBody LoginDTORequest loginDto){

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.senha())
        );

        UsuarioPrincipal principal = (UsuarioPrincipal) auth.getPrincipal();
        String token = jwtService.gerarToken(principal);

        return ResponseEntity.ok(new TokenDTOResponse(token, "Bearer"));

    }

}
