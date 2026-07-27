package com.hvs.webstore.back.app.output.webstore.pix;

import com.hvs.webstore.back.domain.entity.webstore.pix.Pix;

public record ReadPixOutput(Long aId,
                            String aUuid,
                            String aStatusDesc,
                            String aChavePix,
                            String aTipoChavePix) {

    public static ReadPixOutput from(Pix aPix) {

        return new ReadPixOutput(
                aPix.getId().getValue(),
                aPix.getUuid().getValue(),
                aPix.getStatusCode().getDesc(),
                aPix.getChavePix(),
                aPix.getTipoChavePix() != null ? aPix.getTipoChavePix().getDesc() : null);
    }

    public static ReadPixOutput fromSimple(Pix aPix) {

        return new ReadPixOutput(
                aPix.getId().getValue(),
                aPix.getUuid().getValue(),
                null,
                null,
                null);
    }
}
