package com.hvs.webstore.back.app.output.webstore.caracteristica;

import com.hvs.webstore.back.domain.entity.webstore.caracteristica.Caracteristica;

public record ReadCaracteristicaOutput(Long id,
                                       String uuid,
                                       String statusDesc,
                                       String nome,
                                       String descricao) {

    public static ReadCaracteristicaOutput from(Caracteristica aCaracteristica) {

        return new ReadCaracteristicaOutput(
                aCaracteristica.getId().getValue(),
                aCaracteristica.getUuid().getValue(),
                aCaracteristica.getStatusCode().getDesc(),
                aCaracteristica.getNome(),
                aCaracteristica.getDescricao());
    }

    public static ReadCaracteristicaOutput fromSimple(Caracteristica aCaracteristica) {

        return new ReadCaracteristicaOutput(
                aCaracteristica.getId().getValue(),
                aCaracteristica.getUuid().getValue(),
                null,
                aCaracteristica.getNome(),
                null);
    }
}
