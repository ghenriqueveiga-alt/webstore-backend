package com.hvs.webstore.back.app.output.webstore.metaloja;

import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLoja;

public record DeleteMetaLojaOutput(Long aId,
                                   String aUuid,
                                   String aMessage) {

    public static DeleteMetaLojaOutput from(MetaLoja aMetaLoja) {

        return new DeleteMetaLojaOutput(
                aMetaLoja.getId().getValue(),
                aMetaLoja.getUuid().getValue(),
                "MetaLoja deleted: " + aMetaLoja.getUuid().getValue());
    }
}
