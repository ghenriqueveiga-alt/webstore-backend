package com.hvs.webstore.back.app.command.television.introdetectado;

public record ProcessDetectAllIntroCommand(Long aProgramaId) {

    public static ProcessDetectAllIntroCommand from(final Long aProgramaId) {

        return new ProcessDetectAllIntroCommand(aProgramaId);
    }
}