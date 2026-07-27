package com.hvs.webstore.back.domain.entity.television.corte;

public enum CorteTipo {

    INTRODUCAO("IN", "Introdução do Episódio"),
    ABERTURA("AB", "Abertura do Episódio"),
    TITULO("TI", "Título do Episódio"),
    PARTE1("P1", "Primeira parte do Episódio"),
    VINHETA_IDA_INTERVALO("VI", "Vinheta de ida para o intervalo do Episódio"),
    VINHETA_VOLTA_INTERVALO("VV", "Vinheta de volta do intervalo do Episódio"),
    PARTE2("P2", "Segunda parte do Episódio"),
    ENCERRAMENTO("EN", "Encerramento do Episódio"),
    PREVIA_PROXIMO_EPISODIO("PP", "Prévia do próximo Episódio");

    private final String code;
    private final String desc;

    CorteTipo(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static CorteTipo findByCode(String aCode) {

        if (aCode != null) {
            for (CorteTipo tipo : values()) {
                if (tipo.code(tipo.code)) {
                    return tipo;
                }
            }
        }

        return null;
    }

    public static CorteTipo findByDesc(String aDesc) {

        if (aDesc != null) {
            for (CorteTipo tipo : values()) {
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