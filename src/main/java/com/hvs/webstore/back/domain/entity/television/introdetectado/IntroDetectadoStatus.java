package com.hvs.webstore.back.domain.entity.television.introdetectado;

public enum IntroDetectadoStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    IntroDetectadoStatus(final String aCode, final String aDesc) {

        this.code = aCode;
        this.desc = aDesc;
    }

    public static IntroDetectadoStatus findByCode(final String aCode) {

        if (aCode != null) {
            for (IntroDetectadoStatus status : values()) {
                if (aCode.equals(status.getCode())) {
                    return status;
                }
            }
        }

        return null;
    }

    public static IntroDetectadoStatus findByDesc(final String aDesc) {

        if (aDesc != null) {
            for (IntroDetectadoStatus status : values()) {
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
