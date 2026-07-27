package com.hvs.webstore.back.domain.entity.webstore.notafiscal;

public enum NotaFiscalStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    NotaFiscalStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static NotaFiscalStatus findByCode(String aCode) {

        if (aCode != null) {
            for (NotaFiscalStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static NotaFiscalStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (NotaFiscalStatus status : values()) {
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
