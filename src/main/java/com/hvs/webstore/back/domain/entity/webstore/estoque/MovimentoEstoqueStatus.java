package com.hvs.webstore.back.domain.entity.webstore.estoque;

public enum MovimentoEstoqueStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    MovimentoEstoqueStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static MovimentoEstoqueStatus findByCode(String aCode) {

        if (aCode != null) {
            for (MovimentoEstoqueStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static MovimentoEstoqueStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (MovimentoEstoqueStatus status : values()) {
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
