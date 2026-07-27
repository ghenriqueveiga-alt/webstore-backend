package com.hvs.webstore.back.app.output.webstore.metaloja;

import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLoja;

public record PatchMetaLojaOutput(Long aId,
                                  String aUuid,
                                  String aMessage) {

    public static PatchMetaLojaOutput from(MetaLoja aMetaLoja) {

        return new PatchMetaLojaOutput(
                aMetaLoja.getId().getValue(),
                aMetaLoja.getUuid().getValue(),
                "MetaLoja patched: " + aMetaLoja.getUuid().getValue());
    }
}
