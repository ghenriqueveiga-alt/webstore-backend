package com.hvs.webstore.back.domain.entity.webstore.anexo;

public enum AnexoStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    AnexoStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static AnexoStatus findByCode(String aCode) {

        if (aCode != null) {
            for (AnexoStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static AnexoStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (AnexoStatus status : values()) {
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
