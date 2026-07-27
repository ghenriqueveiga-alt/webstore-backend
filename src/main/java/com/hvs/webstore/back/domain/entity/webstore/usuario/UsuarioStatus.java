package com.hvs.webstore.back.domain.entity.webstore.usuario;

public enum UsuarioStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    UsuarioStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static UsuarioStatus findByCode(String aCode) {

        if (aCode != null) {
            for (UsuarioStatus status : values()) {
                if (status.code(status.code)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static UsuarioStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (UsuarioStatus status : values()) {
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
