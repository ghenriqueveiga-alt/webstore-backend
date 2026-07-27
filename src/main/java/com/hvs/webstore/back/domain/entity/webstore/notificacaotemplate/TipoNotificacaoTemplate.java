package com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate;

public enum TipoNotificacaoTemplate {

    EMAIL("EM", "Email"),
    SMS("SM", "SMS"),
    PUSH("PU", "Push");

    private final String code;
    private final String desc;

    TipoNotificacaoTemplate(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static TipoNotificacaoTemplate findByCode(String aCode) {

        if (aCode != null) {
            for (TipoNotificacaoTemplate status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static TipoNotificacaoTemplate findByDesc(String aDesc) {

        if (aDesc != null) {
            for (TipoNotificacaoTemplate status : values()) {
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
