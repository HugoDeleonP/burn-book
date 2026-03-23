package net.burnbook.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurtidaRepository extends JpaRepository <Curtida, Integer> {

    Optional<Curtida> findByPublicacaoAndUsuario(Publicacao publicacao, Usuario usuario);

    Integer countByPublicacao(Publicacao publicacao);

    boolean existsByPublicacaoAndUsuario(Publicacao publicacao, Usuario usuario);
}
