package com.hvs.webstore.back.app.command.webstore.precopromocional;

import java.time.Instant;

public record CreatePrecoPromocionalCommand(Long aProdutoId,
                                            Long aPrecoPromocional,
                                            Instant aDataInicio,
                                            Instant aDataFim) {

    public static CreatePrecoPromocionalCommand from(final Long aProdutoId,
                                                     final Long aPrecoPromocional,
                                                     final Instant aDataInicio,
                                                     final Instant aDataFim) {

        return new CreatePrecoPromocionalCommand(
                aProdutoId,
                aPrecoPromocional,
                aDataInicio,
                aDataFim);
    }
}
