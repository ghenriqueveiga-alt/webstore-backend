package com.hvs.webstore.back.domain.entity.webstore.logauditoria;

public enum LogAuditoriaStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    LogAuditoriaStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static LogAuditoriaStatus findByCode(String aCode) {

        if (aCode != null) {
            for (LogAuditoriaStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static LogAuditoriaStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (LogAuditoriaStatus status : values()) {
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
