package com.hvs.webstore.back.domain.entity.webstore.pix;

public enum TipoChavePix {

    CPF("CP", "CPF"),
    CNPJ("CJ", "CNPJ"),
    EMAIL("EM", "E-mail"),
    TELEFONE("TL", "Telefone"),
    CHAVE_ALEATORIA("CA", "Chave Aleatoria");

    private final String code;
    private final String desc;

    TipoChavePix(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static TipoChavePix findByCode(String aCode) {

        if (aCode != null) {
            for (TipoChavePix tipo : values()) {
                if (tipo.code.equals(aCode)) {
                    return tipo;
                }
            }
        }

        return null;
    }

    public static TipoChavePix findByDesc(String aDesc) {

        if (aDesc != null) {
            for (TipoChavePix tipo : values()) {
                if (tipo.desc.equals(aDesc)) {
                    return tipo;
                }
            }
        }

        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
