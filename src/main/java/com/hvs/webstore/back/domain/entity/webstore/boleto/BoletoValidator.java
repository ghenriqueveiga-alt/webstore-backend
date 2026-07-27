package com.hvs.webstore.back.domain.entity.webstore.boleto;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class BoletoValidator extends Validator {

    private final Boleto boleto;

    public BoletoValidator(ValidationHandler aHandler,
                           final Boleto boleto) {

        super(aHandler);
        this.boleto = boleto;
    }

    @Override
    public void validate() {

        if (boleto.getCodigoBarras() == null)
            this.validationHandler().append(new Erro("codigoBarras should not be null"));
    }

    public Boleto getBoleto() { return boleto; }
}
