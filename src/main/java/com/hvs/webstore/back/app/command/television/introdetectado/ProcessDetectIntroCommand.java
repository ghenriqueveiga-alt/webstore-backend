package com.hvs.webstore.back.app.command.television.introdetectado;

public record ProcessDetectIntroCommand(Long aEpisodioId) {

    public static ProcessDetectIntroCommand from(final Long aEpisodioId) {

        return new ProcessDetectIntroCommand(aEpisodioId);
    }
}