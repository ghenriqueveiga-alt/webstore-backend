package com.hvs.webstore.back.app.output.television.canal;

import com.hvs.webstore.back.domain.entity.television.canal.Canal;

public record ReadCanalOutput(Long aId,
                              String aUuid,
                              String aStatusDesc,
                              String aNome,
                              String aDescricao,
                              String aLogotipoUrl,
                              String aSite) {

    public static ReadCanalOutput from(final Canal aCanal) {

        return new ReadCanalOutput(
                aCanal.getId().getValue(),
                aCanal.getUuid().getValue(),
                aCanal.getStatusCode().getDesc(),
                aCanal.getNome(),
                aCanal.getDescricao(),
                aCanal.getLogotipoUrl(),
                aCanal.getSite());
    }
}
