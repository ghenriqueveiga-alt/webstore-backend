package com.hvs.webstore.back.domain.entity.webstore.cupom;

public enum CupomStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    EXPIRED("EX", "Expired"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    CupomStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static CupomStatus findByCode(String aCode) {

        if (aCode != null) {
            for (CupomStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static CupomStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (CupomStatus status : values()) {
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
