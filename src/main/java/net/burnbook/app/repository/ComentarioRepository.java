package net.burnbook.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface ComentarioRepository extends JpaRepository <Comentario, Integer> {

    Page<Comentario>
    findByPublicacaoAndComentarioPaiIsNullOrderByDataHoraDesc(Publicacao publicacao, Pageable pageable);

    List<Comentario> findByComentarioPaiOrderByDataHoraAsc(Comentario comentarioPai);

    @Query("SELECT c FROM Comentario c " + "JOIN FETCH c.autor " + "LEFT JOIN FETCH c.comentarioPai " + "WHERE c.publicacao.id = :publicacaoId " + "ORDER BY c.dataHora DESC")
    Page<Comentario> buscarComentariosDaPublicacao(@Param("publicacaoId") Long publicacaoId, Pageable pageable);


}
