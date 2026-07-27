package com.hvs.webstore.back.app.command.webstore.endereco;

public record DeleteEnderecoCommand(Long aId,
                                    String aUuid) {

    public static DeleteEnderecoCommand from(final Long aId) {

        return new DeleteEnderecoCommand(
                aId,
                null);
    }

    public static DeleteEnderecoCommand from(final String aUuid) {

        return new DeleteEnderecoCommand(
                null,
                aUuid);
    }
}
