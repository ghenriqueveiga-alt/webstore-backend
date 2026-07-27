package com.hvs.webstore.back.app.command.webstore.cartao;

public record CreateCartaoCommand(String aNomeTitular,
                                  String aNumero,
                                  String aBandeira,
                                  String aTipo,
                                  Integer aMesVencimento,
                                  Integer aAnoVencimento,
                                  String aCvv) {

    public static CreateCartaoCommand from(final String aNomeTitular,
                                           final String aNumero,
                                           final String aBandeira,
                                           final String aTipo,
                                           final Integer aMesVencimento,
                                           final Integer aAnoVencimento,
                                           final String aCvv) {

        return new CreateCartaoCommand(
                aNomeTitular,
                aNumero,
                aBandeira,
                aTipo,
                aMesVencimento,
                aAnoVencimento,
                aCvv);
    }
}
