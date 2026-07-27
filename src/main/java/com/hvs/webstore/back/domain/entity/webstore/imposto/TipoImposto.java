package com.hvs.webstore.back.domain.entity.webstore.imposto;

public enum TipoImposto {

    FEDERAL("FE", "Federal"),
    ESTADUAL("ES", "Estadual"),
    MUNICIPAL("MU", "Municipal");

    private final String code;
    private final String desc;

    TipoImposto(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static TipoImposto findByCode(String aCode) {

        if (aCode != null) {
            for (TipoImposto status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static TipoImposto findByDesc(String aDesc) {

        if (aDesc != null) {
            for (TipoImposto status : values()) {
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
