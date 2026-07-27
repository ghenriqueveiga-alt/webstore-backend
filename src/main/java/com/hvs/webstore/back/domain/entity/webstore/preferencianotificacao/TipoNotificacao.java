package com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao;

public enum TipoNotificacao {

    EMAIL("EM", "Email"),
    SMS("SM", "SMS"),
    PUSH("PU", "Push");

    private final String code;
    private final String desc;

    TipoNotificacao(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static TipoNotificacao findByCode(String aCode) {

        if (aCode != null) {
            for (TipoNotificacao status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static TipoNotificacao findByDesc(String aDesc) {

        if (aDesc != null) {
            for (TipoNotificacao status : values()) {
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
