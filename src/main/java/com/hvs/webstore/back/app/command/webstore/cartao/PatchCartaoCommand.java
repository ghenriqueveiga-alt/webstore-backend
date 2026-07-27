package com.hvs.webstore.back.app.command.webstore.cartao;

public record PatchCartaoCommand(Long aId,
                                 String aUuid,
                                 String aStatusCode,
                                 String aNomeTitular,
                                 String aNumero,
                                 String aBandeira,
                                 String aTipo,
                                 Integer aMesVencimento,
                                 Integer aAnoVencimento,
                                 String aCvv) {

    public static PatchCartaoCommand from(final Long aId,
                                          final PatchCartaoCommand aInput) {

        return new PatchCartaoCommand(
                aId,
                null,
                aInput.aStatusCode,
                aInput.aNomeTitular,
                aInput.aNumero,
                aInput.aBandeira,
                aInput.aTipo,
                aInput.aMesVencimento,
                aInput.aAnoVencimento,
                aInput.aCvv);
    }

    public static PatchCartaoCommand from(final String aUuid,
                                          final PatchCartaoCommand aInput) {

        return new PatchCartaoCommand(
                null,
                aUuid,
                aInput.aStatusCode,
                aInput.aNomeTitular,
                aInput.aNumero,
                aInput.aBandeira,
                aInput.aTipo,
                aInput.aMesVencimento,
                aInput.aAnoVencimento,
                aInput.aCvv);
    }
}
