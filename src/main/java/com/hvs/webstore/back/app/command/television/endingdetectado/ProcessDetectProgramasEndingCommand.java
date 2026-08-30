package com.hvs.webstore.back.app.command.television.endingdetectado;

public record ProcessDetectProgramasEndingCommand() {

    public static ProcessDetectProgramasEndingCommand create() {

        return new ProcessDetectProgramasEndingCommand();
    }
}
