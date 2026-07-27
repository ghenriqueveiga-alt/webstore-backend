package com.hvs.webstore.back.domain.entity.webstore.logauditoria;

public enum AcaoLog {

    CREATE("CR", "Create"),
    UPDATE("UP", "Update"),
    DELETE("DE", "Delete"),
    SOFT_DELETE("SD", "Soft Delete"),
    RESTORE("RE", "Restore");

    private final String code;
    private final String desc;

    AcaoLog(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static AcaoLog findByCode(String aCode) {

        if (aCode != null) {
            for (AcaoLog status : values()) {
                if (status.code.equals(aCode)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static AcaoLog findByDesc(String aDesc) {

        if (aDesc != null) {
            for (AcaoLog status : values()) {
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
