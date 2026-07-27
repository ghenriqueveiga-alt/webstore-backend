package com.hvs.webstore.back.domain.entity.webstore.marca;

public enum MarcaStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    MarcaStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static MarcaStatus findByCode(String aCode) {

        if (aCode != null) {
            for (MarcaStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static MarcaStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (MarcaStatus status : values()) {
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
