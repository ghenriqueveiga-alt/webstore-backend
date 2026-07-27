package com.hvs.webstore.back.domain.entity.webstore.pagamento;

public enum PagamentoStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    PagamentoStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static PagamentoStatus findByCode(String aCode) {

        if (aCode != null) {
            for (PagamentoStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static PagamentoStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (PagamentoStatus status : values()) {
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
