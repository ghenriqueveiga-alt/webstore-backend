package com.hvs.webstore.back.app.command.television.programa;

public record ReadAllProgramaCommand(ProgramaSearchQuery aProgramaSearchQuery) {

    public static ReadAllProgramaCommand from(final ProgramaSearchQuery aProgramaSearchQuery) {

        return new ReadAllProgramaCommand(aProgramaSearchQuery);
    }
}