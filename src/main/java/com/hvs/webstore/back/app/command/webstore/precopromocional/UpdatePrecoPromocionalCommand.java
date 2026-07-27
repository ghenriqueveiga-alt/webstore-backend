package com.hvs.webstore.back.app.command.webstore.precopromocional;

import java.time.Instant;

public record UpdatePrecoPromocionalCommand(Long aId,
                                            String aUuid,
                                            String aStatusCode,
                                            Long aProdutoId,
                                            Long aPrecoPromocional,
                                            Instant aDataInicio,
                                            Instant aDataFim) {

    public static UpdatePrecoPromocionalCommand from(final Long aId,
                                                     final UpdatePrecoPromocionalCommand aInput) {

        return new UpdatePrecoPromocionalCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aProdutoId,
                aInput.aPrecoPromocional,
                aInput.aDataInicio,
                aInput.aDataFim
        );
    }

    public static UpdatePrecoPromocionalCommand from(final String aUuid,
                                                     final UpdatePrecoPromocionalCommand aInput) {

        return new UpdatePrecoPromocionalCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aProdutoId,
                aInput.aPrecoPromocional,
                aInput.aDataInicio,
                aInput.aDataFim
        );
    }
}
