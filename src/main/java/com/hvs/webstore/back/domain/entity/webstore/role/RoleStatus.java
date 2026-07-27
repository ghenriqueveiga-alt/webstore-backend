package com.hvs.webstore.back.domain.entity.webstore.role;

public enum RoleStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    RoleStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static RoleStatus findByCode(String aCode) {

        if (aCode != null) {
            for (RoleStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static RoleStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (RoleStatus status : values()) {
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
