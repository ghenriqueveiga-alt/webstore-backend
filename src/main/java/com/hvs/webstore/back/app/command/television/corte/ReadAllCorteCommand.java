package com.hvs.webstore.back.app.command.television.corte;

public record ReadAllCorteCommand(CorteSearchQuery aCorteSearchQuery) {

    public static ReadAllCorteCommand from(final CorteSearchQuery aCorteSearchQuery) {

        return new ReadAllCorteCommand(aCorteSearchQuery);
    }
}