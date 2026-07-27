package com.hvs.webstore.back.domain.entity.webstore.pedido;

public enum PedidoStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted"),
    PAID("PA", "Paid"),
    PENDING("PE", "Pending"),
    CANCELLED("CA", "Cancelled");

    private final String code;
    private final String desc;

    PedidoStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static PedidoStatus findByCode(String aCode) {

        if (aCode != null) {
            for (PedidoStatus status : values()) {
                if (status.code(status.code)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static PedidoStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (PedidoStatus status : values()) {
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
