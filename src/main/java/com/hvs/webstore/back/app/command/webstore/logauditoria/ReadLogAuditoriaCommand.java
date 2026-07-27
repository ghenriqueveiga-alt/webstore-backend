package com.hvs.webstore.back.app.command.webstore.logauditoria;

public record ReadLogAuditoriaCommand(Long aId,
                                      String aUuid) {

    public static ReadLogAuditoriaCommand from(final Long aId) {

        return new ReadLogAuditoriaCommand(
                aId,
                null
        );
    }

    public static ReadLogAuditoriaCommand from(final String aUuid) {

        return new ReadLogAuditoriaCommand(
                null,
                aUuid
        );
    }
}
