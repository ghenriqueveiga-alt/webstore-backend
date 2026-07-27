package com.hvs.webstore.back.domain.entity.webstore.historicopedido;

public enum HistoricoPedidoStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    HistoricoPedidoStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static HistoricoPedidoStatus findByCode(String aCode) {

        if (aCode != null) {
            for (HistoricoPedidoStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static HistoricoPedidoStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (HistoricoPedidoStatus status : values()) {
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
