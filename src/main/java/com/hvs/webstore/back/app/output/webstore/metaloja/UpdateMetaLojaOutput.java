package com.hvs.webstore.back.app.output.webstore.metaloja;

import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLoja;

public record UpdateMetaLojaOutput(Long aId,
                                   String aUuid,
                                   String aMessage) {

    public static UpdateMetaLojaOutput from(MetaLoja aMetaLoja) {

        return new UpdateMetaLojaOutput(
                aMetaLoja.getId().getValue(),
                aMetaLoja.getUuid().getValue(),
                "MetaLoja updated: " + aMetaLoja.getUuid().getValue());
    }
}
