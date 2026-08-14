package com.hvs.webstore.back.domain.entity.television.programa;

public enum ClassificacaoEtaria {

    LIVRE("LI", "Livre"),
    DEZ("10", "10 anos"),
    DOZE("12", "12 anos"),
    QUATORZE("14", "14 anos"),
    DEZESSEIS("16", "16 anos"),
    DEZOITO("18", "18 anos");

    private final String code;
    private final String desc;

    ClassificacaoEtaria(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static ClassificacaoEtaria findByCode(String aCode) {
        if (aCode != null) {
            for (ClassificacaoEtaria ce : values()) {
                if (ce.code.equals(aCode)) {
                    return ce;
                }
            }
        }
        return null;
    }

    public static ClassificacaoEtaria findByDesc(String aDesc) {
        if (aDesc != null) {
            for (ClassificacaoEtaria ce : values()) {
                if (ce.desc.equals(aDesc)) {
                    return ce;
                }
            }
        }
        return null;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}
