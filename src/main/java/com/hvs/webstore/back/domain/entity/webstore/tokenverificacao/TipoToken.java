package com.hvs.webstore.back.domain.entity.webstore.tokenverificacao;

public enum TipoToken {

    EMAIL("EM", "Email"),
    SMS("SM", "SMS"),
    PASSWORD_RESET("PR", "Password Reset");

    private final String code;
    private final String desc;

    TipoToken(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static TipoToken findByCode(String aCode) {

        if (aCode != null) {
            for (TipoToken status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static TipoToken findByDesc(String aDesc) {

        if (aDesc != null) {
            for (TipoToken status : values()) {
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
