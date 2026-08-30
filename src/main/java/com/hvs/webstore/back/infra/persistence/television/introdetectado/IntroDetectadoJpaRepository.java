package com.hvs.webstore.back.infra.persistence.television.introdetectado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IntroDetectadoJpaRepository extends JpaRepository<IntroDetectadoEntity, Long> {

    Optional<IntroDetectadoEntity> findByUuid(String aUuid);

    List<IntroDetectadoEntity> findByEpisodioId(Long aEpisodioId);

    void deleteByEpisodioId(Long aEpisodioId);

    @Query("select count(i.id) from IntroDetectadoEntity i where i.episodio.id in (select e.id from EpisodioEntity e where e.programa.id = :programaId)")
    long countByPrograma(@Param("programaId") Long aProgramaId);

    @Query("select distinct i.episodio.id from IntroDetectadoEntity i "
            + "where i.detectado = false and i.statusDesc = 'Active'")
    List<Long> findEpisodiosNaoDetectados();

    @Query("select distinct i.episodio.id from IntroDetectadoEntity i "
            + "where i.detectado = false and i.statusDesc = 'Active' "
            + "and i.episodio.programa.tipoDesc = :tipo")
    List<Long> findEpisodiosNaoDetectadosPorTipo(@Param("tipo") String tipo);

    @Query("select distinct i.episodio.programa.id from IntroDetectadoEntity i "
            + "where i.detectado = false and i.statusDesc = 'Active'")
    List<Long> findProgramasComEpisodiosNaoDetectados();

    @Query("select distinct i.episodio.programa.id from IntroDetectadoEntity i "
            + "where i.detectado = false and i.statusDesc = 'Active' "
            + "and i.episodio.programa.tipoDesc = :tipo")
    List<Long> findProgramasComEpisodiosNaoDetectadosPorTipo(@Param("tipo") String tipo);
}