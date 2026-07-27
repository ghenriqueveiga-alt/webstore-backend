package com.hvs.webstore.back.domain.entity.webstore.preco;

public enum PrecoStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    PrecoStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static PrecoStatus findByCode(String aCode) {

        if (aCode != null) {
            for (PrecoStatus status : values()) {
                if (status.code(status.code)) {
                    return status;
                }
            }
        }

        return null;
    }
    
    public static PrecoStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (PrecoStatus status : values()) {
                if (status.desc(status.desc)) {
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

    public String getDesc() {
        return desc;
    }

    public String getCode() {
        return code;
    }
}