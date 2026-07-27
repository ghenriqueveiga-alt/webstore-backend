package com.hvs.webstore.back.app.command.webstore.metaloja;

public record ReadMetaLojaByChaveCommand(String aChave) {

    public static ReadMetaLojaByChaveCommand from(final String aChave) {

        return new ReadMetaLojaByChaveCommand(aChave);
    }
}
