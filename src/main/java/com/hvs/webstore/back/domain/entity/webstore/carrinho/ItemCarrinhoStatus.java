package com.hvs.webstore.back.domain.entity.webstore.carrinho;

public enum ItemCarrinhoStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    REMOVED("RM", "Removed");

    private final String code;
    private final String desc;

    ItemCarrinhoStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static ItemCarrinhoStatus findByCode(String aCode) {

        if (aCode != null) {
            for (ItemCarrinhoStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static ItemCarrinhoStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (ItemCarrinhoStatus status : values()) {
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
