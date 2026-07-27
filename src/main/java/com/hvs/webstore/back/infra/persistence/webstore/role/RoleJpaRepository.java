package com.hvs.webstore.back.infra.persistence.webstore.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long>, JpaSpecificationExecutor<RoleEntity> {

    Optional<RoleEntity> findByUuid(String uuid);
    Optional<RoleEntity> findByNome(String nome);
}
