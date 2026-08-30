package com.hvs.webstore.back.app.command.television.endingdetectado;

public record ProcessDetectAllEndingCommand(Long aProgramaId) {

    public static ProcessDetectAllEndingCommand from(final Long aProgramaId) {

        return new ProcessDetectAllEndingCommand(aProgramaId);
    }
}
