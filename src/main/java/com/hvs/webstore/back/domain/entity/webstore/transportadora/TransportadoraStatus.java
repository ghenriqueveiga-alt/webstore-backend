package com.hvs.webstore.back.domain.entity.webstore.transportadora;

public enum TransportadoraStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    TransportadoraStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static TransportadoraStatus findByCode(String aCode) {

        if (aCode != null) {
            for (TransportadoraStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static TransportadoraStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (TransportadoraStatus status : values()) {
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
