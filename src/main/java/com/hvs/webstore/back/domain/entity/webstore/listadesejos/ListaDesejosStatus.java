package com.hvs.webstore.back.domain.entity.webstore.listadesejos;

public enum ListaDesejosStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    ListaDesejosStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static ListaDesejosStatus findByCode(String aCode) {

        if (aCode != null) {
            for (ListaDesejosStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static ListaDesejosStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (ListaDesejosStatus status : values()) {
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
