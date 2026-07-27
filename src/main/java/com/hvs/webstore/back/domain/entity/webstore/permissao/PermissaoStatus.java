package com.hvs.webstore.back.domain.entity.webstore.permissao;

public enum PermissaoStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    PermissaoStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static PermissaoStatus findByCode(String aCode) {

        if (aCode != null) {
            for (PermissaoStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static PermissaoStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (PermissaoStatus status : values()) {
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
