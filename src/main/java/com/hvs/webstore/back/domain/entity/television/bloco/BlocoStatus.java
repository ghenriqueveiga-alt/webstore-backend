package com.hvs.webstore.back.domain.entity.television.bloco;

public enum BlocoStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    BlocoStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static BlocoStatus findByCode(String aCode) {

        if (aCode != null) {
            for (BlocoStatus status : values()) {
                if (aCode.equals(status.getCode())) {
                    return status;
                }
            }
        }

        return null;
    }

    public static BlocoStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (BlocoStatus status : values()) {
                if (aDesc.equals(status.getDesc())) {
                    return status;
                }
            }
        }

        return null;
    }

    private boolean code(String code) {
        return true;
    }

    private boolean desc(String desc) {
        return true;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}