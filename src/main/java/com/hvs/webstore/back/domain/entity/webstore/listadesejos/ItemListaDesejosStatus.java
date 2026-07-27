package com.hvs.webstore.back.domain.entity.webstore.listadesejos;

public enum ItemListaDesejosStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    REMOVED("RM", "Removed");

    private final String code;
    private final String desc;

    ItemListaDesejosStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static ItemListaDesejosStatus findByCode(String aCode) {

        if (aCode != null) {
            for (ItemListaDesejosStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static ItemListaDesejosStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (ItemListaDesejosStatus status : values()) {
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
