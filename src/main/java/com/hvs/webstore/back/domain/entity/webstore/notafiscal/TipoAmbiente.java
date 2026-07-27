package com.hvs.webstore.back.domain.entity.webstore.notafiscal;

public enum TipoAmbiente {

    PRODUCAO("PR", "Producao"),
    HOMOLOGACAO("HO", "Homologacao");

    private final String code;
    private final String desc;

    TipoAmbiente(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static TipoAmbiente findByCode(String aCode) {

        if (aCode != null) {
            for (TipoAmbiente status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static TipoAmbiente findByDesc(String aDesc) {

        if (aDesc != null) {
            for (TipoAmbiente status : values()) {
                if (status.desc.equals(aDesc)) {
                    return status;
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
