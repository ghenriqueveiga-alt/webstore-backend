package com.hvs.webstore.back.domain.entity.webstore.endereco;

public enum EnderecoStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;

    private final String desc;

    EnderecoStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static EnderecoStatus findByCode(String aCode) {

        if (aCode != null) {
            for (EnderecoStatus status : values()) {
                if (status.code(status.code)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static EnderecoStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (EnderecoStatus status : values()) {
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
