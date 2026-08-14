package com.hvs.webstore.back.app.output.television.genero;

import com.hvs.webstore.back.domain.entity.television.genero.Genero;

public record ReadGeneroOutput(Long aId,
                               String aUuid,
                               String aStatusDesc,
                               String aNome,
                               String aDescricao) {

    public static ReadGeneroOutput from(final Genero aGenero) {

        return new ReadGeneroOutput(
                aGenero.getId().getValue(),
                aGenero.getUuid().getValue(),
                aGenero.getStatusCode().getDesc(),
                aGenero.getNome(),
                aGenero.getDescricao());
    }

    public static ReadGeneroOutput fromSimple(final Genero aGenero) {

        return new ReadGeneroOutput(
                aGenero.getId().getValue(),
                aGenero.getUuid().getValue(),
                null,
                null,
                null);
    }
}
