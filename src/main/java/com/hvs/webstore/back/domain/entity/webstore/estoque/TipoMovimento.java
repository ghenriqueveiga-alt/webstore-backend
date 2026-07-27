package com.hvs.webstore.back.domain.entity.webstore.estoque;

public enum TipoMovimento {

    ENTRADA("EN", "Entrada"),
    SAIDA("SA", "Saida"),
    AJUSTE("AJ", "Ajuste"),
    RESERVA("RE", "Reserva"),
    BAIXA("BX", "Baixa");

    private final String code;
    private final String desc;

    TipoMovimento(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static TipoMovimento findByCode(String aCode) {

        if (aCode != null) {
            for (TipoMovimento tipo : values()) {
                if (tipo.code.equals(aCode)) {
                    return tipo;
                }
            }
        }

        return null;
    }

    public static TipoMovimento findByDesc(String aDesc) {

        if (aDesc != null) {
            for (TipoMovimento tipo : values()) {
                if (tipo.desc.equals(aDesc)) {
                    return tipo;
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
