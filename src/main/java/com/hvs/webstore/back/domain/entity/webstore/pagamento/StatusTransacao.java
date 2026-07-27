package com.hvs.webstore.back.domain.entity.webstore.pagamento;

public enum StatusTransacao {

    PENDING("PE", "Pendente"),
    APPROVED("AP", "Aprovado"),
    REFUSED("RE", "Recusado"),
    REFUNDED("RF", "Estornado"),
    CANCELLED("CA", "Cancelado");

    private final String code;
    private final String desc;

    StatusTransacao(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static StatusTransacao findByCode(String aCode) {

        if (aCode != null) {
            for (StatusTransacao status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static StatusTransacao findByDesc(String aDesc) {

        if (aDesc != null) {
            for (StatusTransacao status : values()) {
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
