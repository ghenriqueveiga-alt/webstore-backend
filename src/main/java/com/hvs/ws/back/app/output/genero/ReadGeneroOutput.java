package com.hvs.ws.back.app.output.genero;

import com.hvs.ws.back.domain.entity.genero.Genero;

public record ReadGeneroOutput(Long aId,
                               String aUuid,
                               String aStatusCode,
                               String aNome,
                               String aDescricao) {

    public static ReadGeneroOutput from(final Genero aGenero) {

        return new ReadGeneroOutput(
                aGenero.getId().getValue(),
                aGenero.getUuid().getValue(),
                aGenero.getStatus().getCode(),
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
