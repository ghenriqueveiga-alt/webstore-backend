package com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia;

public enum CategoriaHierarquiaStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    CategoriaHierarquiaStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static CategoriaHierarquiaStatus findByCode(String aCode) {

        if (aCode != null) {
            for (CategoriaHierarquiaStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static CategoriaHierarquiaStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (CategoriaHierarquiaStatus status : values()) {
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
