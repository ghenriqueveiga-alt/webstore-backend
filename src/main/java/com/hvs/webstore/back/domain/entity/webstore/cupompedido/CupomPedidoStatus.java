package com.hvs.webstore.back.domain.entity.webstore.cupompedido;

public enum CupomPedidoStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    CupomPedidoStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static CupomPedidoStatus findByCode(String aCode) {

        if (aCode != null) {
            for (CupomPedidoStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static CupomPedidoStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (CupomPedidoStatus status : values()) {
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
