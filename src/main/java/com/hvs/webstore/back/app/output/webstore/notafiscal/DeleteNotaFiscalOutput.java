package com.hvs.webstore.back.app.output.webstore.notafiscal;

import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscal;

public record DeleteNotaFiscalOutput(Long aId,
                                     String aUuid,
                                     String aMessage) {

    public static DeleteNotaFiscalOutput from(NotaFiscal aNotaFiscal) {

        return new DeleteNotaFiscalOutput(
                aNotaFiscal.getId().getValue(),
                aNotaFiscal.getUuid().getValue(),
                "NotaFiscal deleted: " + aNotaFiscal.getUuid().getValue());
    }
}
