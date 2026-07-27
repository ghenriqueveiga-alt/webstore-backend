package com.hvs.webstore.back.app.output.webstore.notafiscal;

import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscal;

public record PatchNotaFiscalOutput(Long aId,
                                    String aUuid,
                                    String aMessage) {

    public static PatchNotaFiscalOutput from(NotaFiscal aNotaFiscal) {

        return new PatchNotaFiscalOutput(
                aNotaFiscal.getId().getValue(),
                aNotaFiscal.getUuid().getValue(),
                "NotaFiscal patched: " + aNotaFiscal.getUuid().getValue());
    }
}
