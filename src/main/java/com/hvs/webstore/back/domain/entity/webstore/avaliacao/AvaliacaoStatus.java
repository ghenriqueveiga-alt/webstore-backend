package com.hvs.webstore.back.domain.entity.webstore.avaliacao;

public enum AvaliacaoStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    FLAGGED("FL", "Flagged"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    AvaliacaoStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static AvaliacaoStatus findByCode(String aCode) {

        if (aCode != null) {
            for (AvaliacaoStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static AvaliacaoStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (AvaliacaoStatus status : values()) {
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
