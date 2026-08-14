package com.hvs.webstore.back.domain.entity.television.bloco;

public enum BlocoTipo {

    INEDITO("IN", "Inédito"),
    REPRISE("RE", "Reprise"),
    MARATONA("MA", "Maratona"),
    ESPECIAL("ES", "Especial");

    private final String code;
    private final String desc;

    BlocoTipo(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static BlocoTipo findByCode(String aCode) {
        if (aCode != null) {
            for (BlocoTipo bt : values()) {
                if (bt.code.equals(aCode)) {
                    return bt;
                }
            }
        }
        return null;
    }

    public static BlocoTipo findByDesc(String aDesc) {
        if (aDesc != null) {
            for (BlocoTipo bt : values()) {
                if (bt.desc.equals(aDesc)) {
                    return bt;
                }
            }
        }
        return null;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}
