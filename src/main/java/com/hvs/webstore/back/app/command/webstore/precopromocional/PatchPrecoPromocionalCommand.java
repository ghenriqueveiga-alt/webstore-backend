package com.hvs.webstore.back.app.command.webstore.precopromocional;

import java.time.Instant;

public record PatchPrecoPromocionalCommand(Long aId,
                                           String aUuid,
                                           String aStatusCode,
                                           Long aProdutoId,
                                           Long aPrecoPromocional,
                                           Instant aDataInicio,
                                           Instant aDataFim) {

    public static PatchPrecoPromocionalCommand from(final Long aId,
                                                    final PatchPrecoPromocionalCommand aInput) {

        return new PatchPrecoPromocionalCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aProdutoId,
                aInput.aPrecoPromocional,
                aInput.aDataInicio,
                aInput.aDataFim);
    }

    public static PatchPrecoPromocionalCommand from(final String aUuid,
                                                    final PatchPrecoPromocionalCommand aInput) {

        return new PatchPrecoPromocionalCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aProdutoId,
                aInput.aPrecoPromocional,
                aInput.aDataInicio,
                aInput.aDataFim);
    }
}
