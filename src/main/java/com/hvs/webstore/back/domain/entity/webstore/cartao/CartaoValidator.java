package com.hvs.webstore.back.domain.entity.webstore.cartao;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class CartaoValidator extends Validator {

    private final Cartao cartao;

    public CartaoValidator(ValidationHandler aHandler,
                           final Cartao cartao) {

        super(aHandler);
        this.cartao = cartao;
    }

    @Override
    public void validate() {

        if (cartao.getNomeTitular() == null)
            this.validationHandler().append(new Erro("Nome do titular should not be null"));
        if (cartao.getNumero() == null)
            this.validationHandler().append(new Erro("Numero should not be null"));
        if (cartao.getBandeira() == null)
            this.validationHandler().append(new Erro("Bandeira should not be null"));
        if (cartao.getTipo() == null)
            this.validationHandler().append(new Erro("Tipo should not be null"));
        if (cartao.getMesVencimento() == null)
            this.validationHandler().append(new Erro("Mes de vencimento should not be null"));
        if (cartao.getAnoVencimento() == null)
            this.validationHandler().append(new Erro("Ano de vencimento should not be null"));
        if (cartao.getCvv() == null)
            this.validationHandler().append(new Erro("CVV should not be null"));
    }

    public Cartao getCartao() { return cartao; }
}
