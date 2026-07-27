package com.hvs.webstore.back.app.command.webstore.precopromocional;

public record ReadPrecoPromocionalCommand(Long aId,
                                          String aUuid) {

    public static ReadPrecoPromocionalCommand from(final Long aId) {

        return new ReadPrecoPromocionalCommand(
                aId,
                null
        );
    }

    public static ReadPrecoPromocionalCommand from(final String aUuid) {

        return new ReadPrecoPromocionalCommand(
                null,
                aUuid
        );
    }
}
