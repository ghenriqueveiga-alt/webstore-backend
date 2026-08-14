package com.hvs.webstore.back.domain.entity.television.genero;

public enum GeneroStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    GeneroStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static GeneroStatus findByCode(String aCode) {
        if (aCode != null) {
            for (GeneroStatus status : values()) {
                if (aCode.equals(status.getCode())) {
                    return status;
                }
            }
        }
        return null;
    }

    public static GeneroStatus findByDesc(String aDesc) {
        if (aDesc != null) {
            for (GeneroStatus status : values()) {
                if (aDesc.equals(status.getDesc())) {
                    return status;
                }
            }
        }
        return null;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}
