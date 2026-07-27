package com.hvs.webstore.back.domain.entity.webstore.imagem;

public enum ImagemStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    ImagemStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static ImagemStatus findByCode(String aCode) {

        if (aCode != null) {
            for (ImagemStatus status : values()) {
                if (status.code(status.code)) {
                    return status;
                }
            }
        }

        return null;
    }

    public static ImagemStatus findByDesc(String aDesc) {

        if (aDesc != null) {
            for (ImagemStatus status : values()) {
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

    public String getDesc() {
        return desc;
    }

    public String getCode() {
        return code;
    }
}
