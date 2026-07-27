package com.hvs.webstore.back.app.command.webstore.video;

public record DeleteVideoCommand(Long aId,
                                 String aUuid) {

    public static DeleteVideoCommand from(final Long aId) {

        return new DeleteVideoCommand(
                aId,
                null);
    }

    public static DeleteVideoCommand from(final String aUuid) {

        return new DeleteVideoCommand(
                null,
                aUuid);
    }
}
