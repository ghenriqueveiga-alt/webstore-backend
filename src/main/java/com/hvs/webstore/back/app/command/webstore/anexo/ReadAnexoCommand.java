package com.hvs.webstore.back.app.command.webstore.anexo;

public record ReadAnexoCommand(Long aId,
                               String aUuid,
                               String aEntidadeNome,
                               Long aEntidadeId) {

    public static ReadAnexoCommand from(final Long aId) {

        return new ReadAnexoCommand(
                aId,
                null,
                null,
                null
        );
    }

    public static ReadAnexoCommand from(final String aUuid) {

        return new ReadAnexoCommand(
                null,
                aUuid,
                null,
                null
        );
    }

    public static ReadAnexoCommand from(final String aEntidadeNome,
                                        final Long aEntidadeId) {

        return new ReadAnexoCommand(
                null,
                null,
                aEntidadeNome,
                aEntidadeId
        );
    }
}
