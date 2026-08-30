package com.hvs.webstore.back.domain.entity.television.endingdetectado;

public enum EndingDetectadoStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    EndingDetectadoStatus(final String aCode, final String aDesc) {

        this.code = aCode;
        this.desc = aDesc;
    }

    public static EndingDetectadoStatus findByCode(final String aCode) {

        if (aCode != null) {
            for (EndingDetectadoStatus status : values()) {
                if (aCode.equals(status.getCode())) {
                    return status;
                }
            }
        }

        return null;
    }

    public static EndingDetectadoStatus findByDesc(final String aDesc) {

        if (aDesc != null) {
            for (EndingDetectadoStatus status : values()) {
                if (aDesc.equals(status.getDesc())) {
                    return status;
                }
            }
        }

        return null;
    }

    public String getCode() {

        return this.code;
    }

    public String getDesc() {

        return this.desc;
    }
}
