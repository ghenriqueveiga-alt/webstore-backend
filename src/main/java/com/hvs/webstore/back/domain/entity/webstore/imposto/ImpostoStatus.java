package com.hvs.webstore.back.domain.entity.webstore.imposto;

public enum ImpostoStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    ImpostoStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static ImpostoStatus findByCode(String aCode) {

        if (aCode != null) {
            for (ImpostoStatus status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static ImpostoStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (ImpostoStatus status : values()) {
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
