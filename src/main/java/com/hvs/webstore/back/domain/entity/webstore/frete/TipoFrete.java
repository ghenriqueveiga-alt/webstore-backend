package com.hvs.webstore.back.domain.entity.webstore.frete;

public enum TipoFrete {

    SEDEX("SE", "Sedex"),
    PAC("PA", "PAC"),
    RETIRADA("RE", "Retirada");

    private final String code;
    private final String desc;

    TipoFrete(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static TipoFrete findByCode(String aCode) {

        if (aCode != null) {
            for (TipoFrete tipo : values()) {
                if (tipo.code.equals(aCode)) {
                    return tipo;
                }
            }
        }

        return null;
    }

    public static TipoFrete findByDesc(String aDesc) {

        if (aDesc != null) {
            for (TipoFrete tipo : values()) {
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
