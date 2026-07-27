package com.hvs.webstore.back.domain.entity.webstore.variacaoproduto;

public enum VariacaoProdutoStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    VariacaoProdutoStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static VariacaoProdutoStatus findByCode(String aCode) {

        if (aCode != null) {
            for (VariacaoProdutoStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static VariacaoProdutoStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (VariacaoProdutoStatus status : values()) {
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
