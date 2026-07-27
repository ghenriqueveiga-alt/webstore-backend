package com.hvs.webstore.back.domain.entity.webstore.carrinho;

public enum CarrinhoStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted"),
    CHECKED_OUT("CO", "Checked Out");

    private final String code;
    private final String desc;

    CarrinhoStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static CarrinhoStatus findByCode(String aCode) {

        if (aCode != null) {
            for (CarrinhoStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static CarrinhoStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (CarrinhoStatus status : values()) {
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
