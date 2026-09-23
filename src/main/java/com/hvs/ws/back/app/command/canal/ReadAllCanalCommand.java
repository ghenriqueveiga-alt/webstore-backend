package com.hvs.ws.back.app.command.canal;

public record ReadAllCanalCommand(CanalSearchQuery aCanalSearchQuery) {

    public static ReadAllCanalCommand from(final CanalSearchQuery aCanalSearchQuery) {

        return new ReadAllCanalCommand(aCanalSearchQuery);
    }
}
