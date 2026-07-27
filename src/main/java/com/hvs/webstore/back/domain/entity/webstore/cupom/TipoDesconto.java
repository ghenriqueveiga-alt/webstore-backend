package com.hvs.webstore.back.domain.entity.webstore.cupom;

public enum TipoDesconto {

    PERCENTUAL("PE", "Percentual"),
    FIXO("FI", "Fixo");

    private final String code;
    private final String desc;

    TipoDesconto(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static TipoDesconto findByCode(String aCode) {

        if (aCode != null) {
            for (TipoDesconto tipo : values()) {
                if (tipo.code.equals(aCode)) {
                    return tipo;
                }
            }
        }

        return null;
    }

    public static TipoDesconto findByDesc(String aDesc) {

        if (aDesc != null) {
            for (TipoDesconto tipo : values()) {
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
