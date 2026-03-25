package net.burnbook.app.service;

import net.burnbook.app.dto.usuario.UsuarioDTORequest;
import net.burnbook.app.dto.usuario.UsuarioDTOResponse;
import net.burnbook.app.mapper.UsuarioMapper;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper){
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioDTOResponse cadastrar(UsuarioDTORequest usuarioDto){
        Usuario usuarioDatabase = usuarioMapper.paraEntidade(usuarioDto);

        return usuarioMapper.paraDto(usuarioRepository.save(usuarioDatabase));
    }


    public UsuarioDTOResponse buscarPefilPorId(Long usuarioId){

        Usuario usuarioBuscado = usuarioRepository.findById(usuarioId)
                .orElseThrow( () -> new RuntimeException("Usuário não encontrado"));

        return usuarioMapper.paraDto(usuarioBuscado);
    }

    public UsuarioDTOResponse adicionarFotoPerfil(Long usuarioId, String fotoUrl){

        Usuario usuarioBuscado = usuarioRepository.findById(usuarioId)
                .orElseThrow( () -> new RuntimeException("Usuário não encontrado"));

        usuarioBuscado.setFotoPerfilUrl(fotoUrl);

        return usuarioMapper.paraDto(usuarioBuscado);
    }

}
