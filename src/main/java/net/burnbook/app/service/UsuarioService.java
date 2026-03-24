package net.burnbook.app.service;

import net.burnbook.app.dto.usuario.UsuarioDTORequest;
import net.burnbook.app.dto.usuario.UsuarioDTOResponse;
import net.burnbook.app.mapper.UsuarioMapper;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.repository.UsuarioRepository;

public class UsuarioService {

    public final UsuarioRepository usuarioRepository;
    public final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper){
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioDTOResponse cadastrar(UsuarioDTORequest usuarioDto){
        Usuario usuarioDatabase = usuarioMapper.paraEntidade(usuarioDto);

        return usuarioMapper.paraDto(usuarioRepository.save(usuarioDatabase));
    }




}
