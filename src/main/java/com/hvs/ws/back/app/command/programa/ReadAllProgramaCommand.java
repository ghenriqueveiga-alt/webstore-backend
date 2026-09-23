package com.hvs.ws.back.app.command.programa;

public record ReadAllProgramaCommand(ProgramaSearchQuery aProgramaSearchQuery) {

    public static ReadAllProgramaCommand from(final ProgramaSearchQuery aProgramaSearchQuery) {

        return new ReadAllProgramaCommand(aProgramaSearchQuery);
    }
}