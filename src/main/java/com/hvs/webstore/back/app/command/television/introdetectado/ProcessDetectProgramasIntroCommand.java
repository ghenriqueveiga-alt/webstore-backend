package com.hvs.webstore.back.app.command.television.introdetectado;

public record ProcessDetectProgramasIntroCommand() {

    public static ProcessDetectProgramasIntroCommand create() {

        return new ProcessDetectProgramasIntroCommand();
    }
}