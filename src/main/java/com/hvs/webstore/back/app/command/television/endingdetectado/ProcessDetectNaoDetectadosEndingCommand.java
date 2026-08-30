package com.hvs.webstore.back.app.command.television.endingdetectado;

public record ProcessDetectNaoDetectadosEndingCommand(String aTipo) {

    public static ProcessDetectNaoDetectadosEndingCommand create() {

        return new ProcessDetectNaoDetectadosEndingCommand(null);
    }

    public static ProcessDetectNaoDetectadosEndingCommand from(final String aTipo) {

        return new ProcessDetectNaoDetectadosEndingCommand(aTipo);
    }
}
