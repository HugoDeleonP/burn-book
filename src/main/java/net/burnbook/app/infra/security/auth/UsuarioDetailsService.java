package net.burnbook.app.infra.security.auth;

import lombok.RequiredArgsConstructor;
import net.burnbook.app.infra.security.auth.model.UsuarioPrincipal;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor

public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuarioBuscado = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado para validação!"));

        return new UsuarioPrincipal(usuarioBuscado);
    }
}
