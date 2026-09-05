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
            if (aDesc.contains("10") || aDesc.contains("Y7")) return DEZ;
            if (aDesc.contains("12")) return DOZE;
            if (aDesc.contains("14")) return QUATORZE;
            if (aDesc.contains("16")) return DEZESSEIS;
            if (aDesc.contains("18") || aDesc.contains("MA")) return DEZOITO;
        }
        return null;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}
