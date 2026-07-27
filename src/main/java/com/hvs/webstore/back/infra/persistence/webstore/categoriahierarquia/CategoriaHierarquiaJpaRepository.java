package com.hvs.webstore.back.infra.persistence.webstore.categoriahierarquia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CategoriaHierarquiaJpaRepository extends JpaRepository<CategoriaHierarquiaEntity, Long>, JpaSpecificationExecutor<CategoriaHierarquiaEntity> {

    Optional<CategoriaHierarquiaEntity> findByUuid(String uuid);
    List<CategoriaHierarquiaEntity> findByCategoria_Id(Long categoriaId);
    List<CategoriaHierarquiaEntity> findByCategoriaPai_Id(Long categoriaPaiId);
    List<CategoriaHierarquiaEntity> findByCategoriaPaiIsNull();
}
