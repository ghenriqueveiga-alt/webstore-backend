package com.hvs.webstore.back.app.output.webstore.categoriahierarquia;

import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquia;

public record ReadCategoriaHierarquiaOutput(Long id,
                                            String uuid,
                                            String statusDesc,
                                            Long categoriaId,
                                            Long categoriaPaiId,
                                            Integer nivel) {

    public static ReadCategoriaHierarquiaOutput from(CategoriaHierarquia aCategoriaHierarquia) {

        return new ReadCategoriaHierarquiaOutput(
                aCategoriaHierarquia.getId().getValue(),
                aCategoriaHierarquia.getUuid().getValue(),
                aCategoriaHierarquia.getStatusCode().getDesc(),
                aCategoriaHierarquia.getCategoria() != null ? aCategoriaHierarquia.getCategoria().getId().getValue() : null,
                aCategoriaHierarquia.getCategoriaPai() != null ? aCategoriaHierarquia.getCategoriaPai().getId().getValue() : null,
                aCategoriaHierarquia.getNivel());
    }

    public static ReadCategoriaHierarquiaOutput fromSimple(CategoriaHierarquia aCategoriaHierarquia) {

        return new ReadCategoriaHierarquiaOutput(
                aCategoriaHierarquia.getId().getValue(),
                aCategoriaHierarquia.getUuid().getValue(),
                null,
                null,
                null,
                null);
    }
}
