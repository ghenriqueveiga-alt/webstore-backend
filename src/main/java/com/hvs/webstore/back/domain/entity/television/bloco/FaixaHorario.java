package com.hvs.webstore.back.domain.entity.television.bloco;

public enum FaixaHorario {

    MADRUGADA("MA", "Madrugada"),
    MANHA("MN", "Manhã"),
    TARDE("TA", "Tarde"),
    NOITE("NO", "Noite"),
    PRIME_TIME("PT", "Prime Time");

    private final String code;
    private final String desc;

    FaixaHorario(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static FaixaHorario findByCode(String aCode) {
        if (aCode != null) {
            for (FaixaHorario fh : values()) {
                if (fh.code.equals(aCode)) {
                    return fh;
                }
            }
        }
        return null;
    }

    public static FaixaHorario findByDesc(String aDesc) {
        if (aDesc != null) {
            for (FaixaHorario fh : values()) {
                if (fh.desc.equals(aDesc)) {
                    return fh;
                }
            }
        }
        return null;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}
