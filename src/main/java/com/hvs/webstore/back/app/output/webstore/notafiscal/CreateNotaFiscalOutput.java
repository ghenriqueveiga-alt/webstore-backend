package com.hvs.webstore.back.app.output.webstore.notafiscal;

import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscal;

public record CreateNotaFiscalOutput(Long aId,
                                     String aUuid,
                                     String aMessage) {

    public static CreateNotaFiscalOutput from(NotaFiscal aNotaFiscal) {

        return new CreateNotaFiscalOutput(
                aNotaFiscal.getId().getValue(),
                aNotaFiscal.getUuid().getValue(),
                "NotaFiscal created: " + aNotaFiscal.getUuid().getValue());
    }
}
