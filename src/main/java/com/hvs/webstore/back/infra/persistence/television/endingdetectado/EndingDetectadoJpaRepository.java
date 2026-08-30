package com.hvs.webstore.back.infra.persistence.television.endingdetectado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EndingDetectadoJpaRepository extends JpaRepository<EndingDetectadoEntity, Long> {

    Optional<EndingDetectadoEntity> findByUuid(String aUuid);

    List<EndingDetectadoEntity> findByEpisodioId(Long aEpisodioId);

    void deleteByEpisodioId(Long aEpisodioId);

    @Query("select count(i.id) from EndingDetectadoEntity i where i.episodio.id in (select e.id from EpisodioEntity e where e.programa.id = :programaId)")
    long countByPrograma(@Param("programaId") Long aProgramaId);

    @Query("select distinct i.episodio.id from EndingDetectadoEntity i "
            + "where i.detectado = false and i.statusDesc = 'Active'")
    List<Long> findEpisodiosNaoDetectados();

    @Query("select distinct i.episodio.id from EndingDetectadoEntity i "
            + "where i.detectado = false and i.statusDesc = 'Active' "
            + "and i.episodio.programa.tipoDesc = :tipo")
    List<Long> findEpisodiosNaoDetectadosPorTipo(@Param("tipo") String tipo);

    @Query("select distinct i.episodio.programa.id from EndingDetectadoEntity i "
            + "where i.detectado = false and i.statusDesc = 'Active'")
    List<Long> findProgramasComEpisodiosNaoDetectados();

    @Query("select distinct i.episodio.programa.id from EndingDetectadoEntity i "
            + "where i.detectado = false and i.statusDesc = 'Active' "
            + "and i.episodio.programa.tipoDesc = :tipo")
    List<Long> findProgramasComEpisodiosNaoDetectadosPorTipo(@Param("tipo") String tipo);
}
