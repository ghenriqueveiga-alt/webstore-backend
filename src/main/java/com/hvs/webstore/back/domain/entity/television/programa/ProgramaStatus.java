package com.hvs.webstore.back.domain.entity.television.programa;

public enum ProgramaStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    ENDED("EN", "Encerrado"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    ProgramaStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static ProgramaStatus findByCode(String aCode) {

        if (aCode != null) {
            for (ProgramaStatus status : values()) {
                if (aCode.equals(status.getCode())) {
                    return status;
                }
            }
        }

        return null;
    }

    public static ProgramaStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (ProgramaStatus status : values()) {
                if (aDesc.equals(status.getDesc())) {
                    return status;
                }
            }
            if (aDesc.equalsIgnoreCase("Ativo")) return ACTIVE;
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