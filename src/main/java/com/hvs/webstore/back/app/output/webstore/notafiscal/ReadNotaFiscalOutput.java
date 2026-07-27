package com.hvs.webstore.back.app.output.webstore.notafiscal;

import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscal;

import java.time.LocalDate;

public record ReadNotaFiscalOutput(Long aId,
                                   String aUuid,
                                   String aStatusDesc,
                                   Long aPedidoId,
                                   String aChaveAcesso,
                                   Long aNumero,
                                   Long aSerie,
                                   String aTipoAmbienteDesc,
                                   String aXml,
                                   String aDanfeUrl,
                                   LocalDate aDataEmissao) {

    public static ReadNotaFiscalOutput from(NotaFiscal aNotaFiscal) {

        return new ReadNotaFiscalOutput(
                aNotaFiscal.getId().getValue(),
                aNotaFiscal.getUuid().getValue(),
                aNotaFiscal.getStatusCode().getDesc(),
                aNotaFiscal.getPedido() != null ? aNotaFiscal.getPedido().getId().getValue() : null,
                aNotaFiscal.getChaveAcesso(),
                aNotaFiscal.getNumero(),
                aNotaFiscal.getSerie(),
                aNotaFiscal.getTipoAmbiente() != null ? aNotaFiscal.getTipoAmbiente().getDesc() : null,
                aNotaFiscal.getXml(),
                aNotaFiscal.getDanfeUrl(),
                aNotaFiscal.getDataEmissao());
    }

    public static ReadNotaFiscalOutput fromSimple(NotaFiscal aNotaFiscal) {

        return new ReadNotaFiscalOutput(
                aNotaFiscal.getId().getValue(),
                aNotaFiscal.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
