package com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate;

public enum NotificacaoTemplateStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    NotificacaoTemplateStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static NotificacaoTemplateStatus findByCode(String aCode) {

        if (aCode != null) {
            for (NotificacaoTemplateStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static NotificacaoTemplateStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (NotificacaoTemplateStatus status : values()) {
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
