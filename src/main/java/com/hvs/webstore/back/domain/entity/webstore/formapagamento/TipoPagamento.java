package com.hvs.webstore.back.domain.entity.webstore.formapagamento;

public enum TipoPagamento {

    CARTAO_CREDITO("CC", "Cartão de Crédito"),
    PIX("PX", "Pix"),
    BOLETO("BL", "Boleto Bancário");

    private final String code;
    private final String desc;

    TipoPagamento(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static TipoPagamento findByCode(String aCode) {

        if (aCode != null) {
            for (TipoPagamento tipo : values()) {
                if (tipo.code.equals(aCode)) {
                    return tipo;
                }
            }
        }

        return null;
    }

    public static TipoPagamento findByDesc(String aDesc) {

        if (aDesc != null) {
            for (TipoPagamento tipo : values()) {
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
