package com.hvs.webstore.back.app.command.webstore.permissao;

public record PatchPermissaoCommand(Long aId,
                                    String aUuid,
                                    String aStatusCode,
                                    String aNome,
                                    String aChave,
                                    String aDescricao) {

    public static PatchPermissaoCommand from(final Long aId,
                                             final PatchPermissaoCommand aInput) {

        return new PatchPermissaoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aChave,
                aInput.aDescricao);
    }

    public static PatchPermissaoCommand from(final String aUuid,
                                             final PatchPermissaoCommand aInput) {

        return new PatchPermissaoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNome,
                aInput.aChave,
                aInput.aDescricao);
    }
}
