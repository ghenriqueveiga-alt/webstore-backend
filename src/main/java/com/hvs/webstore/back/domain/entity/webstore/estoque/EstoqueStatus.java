package com.hvs.webstore.back.domain.entity.webstore.estoque;

public enum EstoqueStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    EstoqueStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static EstoqueStatus findByCode(String aCode) {

        if (aCode != null) {
            for (EstoqueStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static EstoqueStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (EstoqueStatus status : values()) {
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
