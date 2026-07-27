package com.hvs.webstore.back.app.command.webstore.endereco;

public record ReadEnderecoCommand(Long aId,
                                  String aUuid) {

    public static ReadEnderecoCommand from(final Long aId) {

        return new ReadEnderecoCommand(
                aId,
                null
        );
    }

    public static ReadEnderecoCommand from(final String aUuid) {

        return new ReadEnderecoCommand(
                null,
                aUuid
        );
    }
}
