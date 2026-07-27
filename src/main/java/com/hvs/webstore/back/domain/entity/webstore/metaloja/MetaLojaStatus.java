package com.hvs.webstore.back.domain.entity.webstore.metaloja;

public enum MetaLojaStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    MetaLojaStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static MetaLojaStatus findByCode(String aCode) {

        if (aCode != null) {
            for (MetaLojaStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static MetaLojaStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (MetaLojaStatus status : values()) {
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
