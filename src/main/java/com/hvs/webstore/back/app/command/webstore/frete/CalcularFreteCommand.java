package com.hvs.webstore.back.app.command.webstore.frete;

public record CalcularFreteCommand(String cepOrigem,
                                   String cepDestino,
                                   String tipoFrete,
                                   Double peso,
                                   Double comprimento,
                                   Double largura,
                                   Double altura) {

    public static CalcularFreteCommand from(final String cepOrigem,
                                            final String cepDestino,
                                            final String tipoFrete,
                                            final Double peso,
                                            final Double comprimento,
                                            final Double largura,
                                            final Double altura) {

        return new CalcularFreteCommand(
                cepOrigem,
                cepDestino,
                tipoFrete,
                peso,
                comprimento,
                largura,
                altura);
    }
}
