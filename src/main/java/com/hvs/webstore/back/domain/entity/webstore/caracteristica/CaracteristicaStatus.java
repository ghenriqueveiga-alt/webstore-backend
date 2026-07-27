package com.hvs.webstore.back.domain.entity.webstore.caracteristica;

public enum CaracteristicaStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;

    private final String desc;

    CaracteristicaStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static CaracteristicaStatus findByCode(String aCode) {

        if (aCode != null) {
            for (CaracteristicaStatus status : values()) {
                if (status.code(status.code)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static CaracteristicaStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (CaracteristicaStatus status : values()) {
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

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}