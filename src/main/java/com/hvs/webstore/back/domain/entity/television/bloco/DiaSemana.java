package com.hvs.webstore.back.domain.entity.television.bloco;

public enum DiaSemana {

    SEGUNDA("SE", "Segunda-feira"),
    TERCA("TE", "Terça-feira"),
    QUARTA("QA", "Quarta-feira"),
    QUINTA("QI", "Quinta-feira"),
    SEXTA("SX", "Sexta-feira"),
    SABADO("SA", "Sábado"),
    DOMINGO("DO", "Domingo");

    private final String code;
    private final String desc;

    DiaSemana(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static DiaSemana findByCode(String aCode) {
        if (aCode != null) {
            for (DiaSemana dia : values()) {
                if (dia.code.equals(aCode)) {
                    return dia;
                }
            }
        }
        return null;
    }

    public static DiaSemana findByDesc(String aDesc) {
        if (aDesc != null) {
            for (DiaSemana dia : values()) {
                if (dia.desc.equals(aDesc)) {
                    return dia;
                }
            }
        }
        return null;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}
