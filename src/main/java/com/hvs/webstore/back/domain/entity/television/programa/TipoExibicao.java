package com.hvs.webstore.back.domain.entity.television.programa;

public enum TipoExibicao {

    INEDITO("IN", "Inédito"),
    REPRISE("RE", "Reprise"),
    AMBOS("AM", "Ambos");

    private final String code;
    private final String desc;

    TipoExibicao(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static TipoExibicao findByCode(String aCode) {
        if (aCode != null) {
            for (TipoExibicao te : values()) {
                if (te.code.equals(aCode)) {
                    return te;
                }
            }
        }
        return null;
    }

    public static TipoExibicao findByDesc(String aDesc) {
        if (aDesc != null) {
            for (TipoExibicao te : values()) {
                if (te.desc.equals(aDesc)) {
                    return te;
                }
            }
        }
        return null;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}
