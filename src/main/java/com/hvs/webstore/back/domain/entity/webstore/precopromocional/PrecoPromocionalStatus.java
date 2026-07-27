package com.hvs.webstore.back.domain.entity.webstore.precopromocional;

public enum PrecoPromocionalStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    PrecoPromocionalStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static PrecoPromocionalStatus findByCode(String aCode) {

        if (aCode != null) {
            for (PrecoPromocionalStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static PrecoPromocionalStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (PrecoPromocionalStatus status : values()) {
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
