package com.hvs.webstore.back.domain.entity.webstore.carrinhofrete;

public enum CarrinhoFreteStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    CarrinhoFreteStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static CarrinhoFreteStatus findByCode(String aCode) {

        if (aCode != null) {
            for (CarrinhoFreteStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static CarrinhoFreteStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (CarrinhoFreteStatus status : values()) {
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
