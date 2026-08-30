package com.hvs.webstore.back.app.command.television.endingdetectado;

public record ProcessDetectEndingCommand(Long aEpisodioId) {

    public static ProcessDetectEndingCommand from(final Long aEpisodioId) {

        return new ProcessDetectEndingCommand(aEpisodioId);
    }
}
