package com.hvs.webstore.back.domain.entity.webstore.tokenverificacao;

public enum TokenVerificacaoStatus {

    ACTIVE("AT", "Active"),
    USED("US", "Used"),
    EXPIRED("EX", "Expired"),
    CANCELLED("CA", "Cancelled");

    private final String code;
    private final String desc;

    TokenVerificacaoStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static TokenVerificacaoStatus findByCode(String aCode) {

        if (aCode != null) {
            for (TokenVerificacaoStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static TokenVerificacaoStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (TokenVerificacaoStatus status : values()) {
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
