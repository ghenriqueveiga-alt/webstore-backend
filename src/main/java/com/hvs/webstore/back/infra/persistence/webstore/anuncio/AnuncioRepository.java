package com.hvs.webstore.back.infra.persistence.webstore.anuncio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnuncioRepository extends JpaRepository<AnuncioEntity, Long> {

    @Query("SELECT a FROM AnuncioEntity a WHERE a.statusDesc = 'AT'")
    List<AnuncioEntity> findAllActive();

    @Query("SELECT a FROM AnuncioEntity a WHERE a.statusDesc = 'AT' AND a.posicao = :posicao")
    List<AnuncioEntity> findActiveByPosition(Integer posicao);
}
