package com.hvs.webstore.back.app.command.webstore.precopromocional;

public record DeletePrecoPromocionalCommand(Long aId,
                                            String aUuid) {

    public static DeletePrecoPromocionalCommand from(final Long aId) {

        return new DeletePrecoPromocionalCommand(
                aId,
                null);
    }

    public static DeletePrecoPromocionalCommand from(final String aUuid) {

        return new DeletePrecoPromocionalCommand(
                null,
                aUuid);
    }
}
