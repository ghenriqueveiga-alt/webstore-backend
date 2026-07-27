package com.hvs.webstore.back.domain.entity.television.programa;

public enum ProgramaTipo {

    ANIME("AN", "Anime"),
    Cartoon("CA", "Cartoon");

    private final String code;
    private final String desc;

    ProgramaTipo(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static ProgramaTipo findByCode(String aCode) {

        if (aCode != null) {
            for (ProgramaTipo tipo : values()) {
                if (tipo.code(tipo.code)) {
                    return tipo;
                }
            }
        }

        return null;
    }

    public static ProgramaTipo findByDesc(String aDesc) {

        if (aDesc != null) {
            for (ProgramaTipo tipo : values()) {
                if (tipo.desc(tipo.desc)) {
                    return tipo;
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