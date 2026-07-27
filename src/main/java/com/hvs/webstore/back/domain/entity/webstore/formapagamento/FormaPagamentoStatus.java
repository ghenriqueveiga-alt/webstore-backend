package com.hvs.webstore.back.domain.entity.webstore.formapagamento;

public enum FormaPagamentoStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;

    private final String desc;

    FormaPagamentoStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static FormaPagamentoStatus findByCode(String aCode) {

        if (aCode != null) {
            for (FormaPagamentoStatus status : values()) {
                if (status.code(status.code)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static FormaPagamentoStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (FormaPagamentoStatus status : values()) {
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
