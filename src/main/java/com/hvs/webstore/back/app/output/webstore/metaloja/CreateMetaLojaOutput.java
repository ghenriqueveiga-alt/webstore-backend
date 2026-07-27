package com.hvs.webstore.back.app.output.webstore.metaloja;

import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLoja;

public record CreateMetaLojaOutput(Long aId,
                                   String aUuid,
                                   String aMessage) {

    public static CreateMetaLojaOutput from(MetaLoja aMetaLoja) {

        return new CreateMetaLojaOutput(
                aMetaLoja.getId().getValue(),
                aMetaLoja.getUuid().getValue(),
                "MetaLoja created: " + aMetaLoja.getUuid().getValue());
    }
}
