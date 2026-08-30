package com.hvs.webstore.back.app.command.television.introdetectado;

public record ProcessDetectNaoDetectadosIntroCommand(String aTipo) {

    public static ProcessDetectNaoDetectadosIntroCommand create() {

        return new ProcessDetectNaoDetectadosIntroCommand(null);
    }

    public static ProcessDetectNaoDetectadosIntroCommand from(final String aTipo) {

        return new ProcessDetectNaoDetectadosIntroCommand(aTipo);
    }
}