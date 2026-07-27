package com.hvs.webstore.back.domain.entity.webstore.cartao;

public enum CartaoStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    CartaoStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static CartaoStatus findByCode(String aCode) {
        if (aCode != null) {
            for (CartaoStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }
        return null;
    }

    public static CartaoStatus findByDesc(String aDesc) {
        if (aDesc != null) {
            for (CartaoStatus status : values()) {
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
