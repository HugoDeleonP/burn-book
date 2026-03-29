package net.burnbook.app.service;

import net.burnbook.app.dto.usuario.UsuarioDTORequest;
import net.burnbook.app.dto.usuario.UsuarioDTOResponse;
import net.burnbook.app.infra.exception.model.ConflitoException;
import net.burnbook.app.infra.exception.model.EntidadeNaoEncontradaException;
import net.burnbook.app.infra.exception.model.RegraDeNegocioException;
import net.burnbook.app.mapper.UsuarioMapper;
import net.burnbook.app.model.Usuario;
import net.burnbook.app.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper){
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioDTOResponse cadastrar(UsuarioDTORequest usuarioDto){

        if(Period.between( usuarioDto.dataNascimento(), LocalDate.now()).getYears() < 18 ){
            throw new RegraDeNegocioException("O Usuário deve ser maior de idade.");
        }

        if(usuarioRepository.existsByEmail(usuarioDto.email())){
            throw new ConflitoException("Email já cadastrado!");
        }

        if(usuarioRepository.existsByUsername(usuarioDto.username())){
            throw new ConflitoException("Username já cadastrado!");
        }

        if(usuarioRepository.existsByCpf(usuarioDto.cpf())){
            throw new ConflitoException("CPF já cadastrado!");
        }

        Usuario usuarioDatabase = usuarioMapper.paraEntidade(usuarioDto);

        return usuarioMapper.paraDto(usuarioRepository.save(usuarioDatabase));
    }


    public UsuarioDTOResponse buscarPefilPorId(Long usuarioId){

        Usuario usuarioBuscado = usuarioRepository.findById(usuarioId)
                .orElseThrow( () -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

        return usuarioMapper.paraDto(usuarioBuscado);
    }

    public UsuarioDTOResponse adicionarFotoPerfil(Long usuarioId, String fotoUrl){

        Usuario usuarioBuscado = usuarioRepository.findById(usuarioId)
                .orElseThrow( () -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

        usuarioBuscado.setFotoPerfilUrl(fotoUrl);

        return usuarioMapper.paraDto(usuarioBuscado);
    }

    public UsuarioDTOResponse buscarPorUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));
        return usuarioMapper.paraDto(usuario);
    }

}
