package com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao;

public enum PreferenciaNotificacaoStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    PreferenciaNotificacaoStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static PreferenciaNotificacaoStatus findByCode(String aCode) {

        if (aCode != null) {
            for (PreferenciaNotificacaoStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static PreferenciaNotificacaoStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (PreferenciaNotificacaoStatus status : values()) {
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
