package com.hvs.webstore.back.domain.entity.television.arquivo;

public enum ArquivoStatus {

    ACTIVE("AC", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    ArquivoStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static ArquivoStatus findByCode(String aCode) {

        if (aCode != null) {
            for (ArquivoStatus status : values()) {
                if (aCode.equals(status.getCode())) {
                    return status;
                }
            }
        }

        return null;
    }

    public static ArquivoStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (ArquivoStatus status : values()) {
                if (aDesc.equals(status.getDesc())) {
                    return status;
                }
            }
        }

        return null;
    }

    private boolean code(String aCode) {
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