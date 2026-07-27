package com.hvs.webstore.back.app.output.webstore.metaloja;

import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLoja;

public record ReadMetaLojaOutput(Long aId,
                                 String aUuid,
                                 String aStatusDesc,
                                 String aChave,
                                 String aValor,
                                 String aDescricao,
                                 String aTipoDesc) {

    public static ReadMetaLojaOutput from(MetaLoja aMetaLoja) {

        return new ReadMetaLojaOutput(
                aMetaLoja.getId().getValue(),
                aMetaLoja.getUuid().getValue(),
                aMetaLoja.getStatusCode().getDesc(),
                aMetaLoja.getChave(),
                aMetaLoja.getValor(),
                aMetaLoja.getDescricao(),
                aMetaLoja.getTipoMetaLoja() != null ? aMetaLoja.getTipoMetaLoja().getDesc() : null);
    }

    public static ReadMetaLojaOutput fromSimple(MetaLoja aMetaLoja) {

        return new ReadMetaLojaOutput(
                aMetaLoja.getId().getValue(),
                aMetaLoja.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null);
    }
}
