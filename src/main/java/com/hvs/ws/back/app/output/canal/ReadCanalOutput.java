package com.hvs.ws.back.app.output.canal;

import com.hvs.ws.back.domain.entity.canal.Canal;

public record ReadCanalOutput(Long aId,
                              String aUuid,
                              String aStatusCode,
                              String aNome,
                              String aDescricao,
                              String aLogotipoUrl,
                              String aSite) {

    public static ReadCanalOutput from(final Canal aCanal) {

        return new ReadCanalOutput(
                aCanal.getId().getValue(),
                aCanal.getUuid().getValue(),
                aCanal.getStatus().getCode(),
                aCanal.getNome(),
                aCanal.getDescricao(),
                aCanal.getLogotipoUrl(),
                aCanal.getSite());
    }

    public static ReadCanalOutput fromSimple(final Canal aCanal) {

        return new ReadCanalOutput(
                aCanal.getId().getValue(),
                aCanal.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null);
    }
}
