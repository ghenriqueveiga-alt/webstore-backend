package com.hvs.webstore.back.app.output.webstore.notafiscal;

import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscal;

public record UpdateNotaFiscalOutput(Long aId,
                                     String aUuid,
                                     String aMessage) {

    public static UpdateNotaFiscalOutput from(NotaFiscal aNotaFiscal) {

        return new UpdateNotaFiscalOutput(
                aNotaFiscal.getId().getValue(),
                aNotaFiscal.getUuid().getValue(),
                "NotaFiscal updated: " + aNotaFiscal.getUuid().getValue());
    }
}
