package com.hvs.webstore.back.domain.entity.webstore.metaloja;

public enum TipoMetaLoja {

    STRING("ST", "String"),
    INTEGER("IN", "Integer"),
    BOOLEAN("BO", "Boolean"),
    JSON("JS", "JSON"),
    DECIMAL("DE", "Decimal");

    private final String code;
    private final String desc;

    TipoMetaLoja(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static TipoMetaLoja findByCode(String aCode) {

        if (aCode != null) {
            for (TipoMetaLoja status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static TipoMetaLoja findByDesc(String aDesc) {

        if (aDesc != null) {
            for (TipoMetaLoja status : values()) {
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
