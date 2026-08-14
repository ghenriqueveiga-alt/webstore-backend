package com.hvs.webstore.back.app.command.television.canal;

public record ReadAllCanalCommand(CanalSearchQuery aCanalSearchQuery) {

    public static ReadAllCanalCommand from(final CanalSearchQuery aCanalSearchQuery) {

        return new ReadAllCanalCommand(aCanalSearchQuery);
    }
}
