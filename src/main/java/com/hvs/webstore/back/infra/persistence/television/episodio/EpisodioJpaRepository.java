package com.hvs.webstore.back.infra.persistence.television.episodio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface EpisodioJpaRepository extends JpaRepository<EpisodioEntity, Long>, JpaSpecificationExecutor<EpisodioEntity> {

    Optional<EpisodioEntity> findByUuid(String uuid);
}
