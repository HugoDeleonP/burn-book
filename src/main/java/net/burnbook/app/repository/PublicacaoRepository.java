package net.burnbook.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Integer> {

    Page<Publicacao> findAllByOrderByDataHoraDesc(Pageable pageable);

    Page<Publicacao> findByAutorOrderByDataHoraDesc(Usuario autor, Pageable pageable);

    Page<Publicacao> findByCategoriaNomeOrderByDataHoraDesc(String categoriaNome, Pageable pageable)

    @Query("SELECT p FROM Publicacao p JOIN FETCH p.autor JOIN FETCH p.categoria ORDER BY p.dataHora DESC")
    Page<Publicacao> findFeedOtimizado(Pageable pageable);


}
