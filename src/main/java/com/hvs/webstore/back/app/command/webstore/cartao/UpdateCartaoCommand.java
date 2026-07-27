package com.hvs.webstore.back.app.command.webstore.cartao;

public record UpdateCartaoCommand(Long aId,
                                  String aUuid,
                                  String aStatusCode,
                                  String aNomeTitular,
                                  String aNumero,
                                  String aBandeira,
                                  String aTipo,
                                  Integer aMesVencimento,
                                  Integer aAnoVencimento,
                                  String aCvv) {

    public static UpdateCartaoCommand from(final Long aId,
                                           final UpdateCartaoCommand aInput) {

        return new UpdateCartaoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNomeTitular,
                aInput.aNumero,
                aInput.aBandeira,
                aInput.aTipo,
                aInput.aMesVencimento,
                aInput.aAnoVencimento,
                aInput.aCvv
        );
    }

    public static UpdateCartaoCommand from(final String aUuid,
                                           final UpdateCartaoCommand aInput) {

        return new UpdateCartaoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNomeTitular,
                aInput.aNumero,
                aInput.aBandeira,
                aInput.aTipo,
                aInput.aMesVencimento,
                aInput.aAnoVencimento,
                aInput.aCvv
        );
    }
}
