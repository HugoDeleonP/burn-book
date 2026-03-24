package net.burnbook.app.repository;

import net.burnbook.app.model.Curtida;
import net.burnbook.app.model.Publicacao;
import net.burnbook.app.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurtidaRepository extends JpaRepository <Curtida, Long> {

    Optional<Curtida> findByPublicacaoAndUsuario(Publicacao publicacao, Usuario usuario);

    Integer countByPublicacao(Publicacao publicacao);

    boolean existsByPublicacaoAndUsuario(Publicacao publicacao, Usuario usuario);
}
