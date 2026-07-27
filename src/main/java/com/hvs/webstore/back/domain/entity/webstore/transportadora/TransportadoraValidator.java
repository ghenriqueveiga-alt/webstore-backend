package com.hvs.webstore.back.domain.entity.webstore.transportadora;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class TransportadoraValidator extends Validator {

    private final Transportadora transportadora;

    public TransportadoraValidator(ValidationHandler aHandler,
                                   final Transportadora transportadora) {

        super(aHandler);
        this.transportadora = transportadora;
    }

    @Override
    public void validate() {

        if (transportadora.getNome() == null)
            this.validationHandler().append(new Erro("nome should not be null"));
    }

    public Transportadora getTransportadora() { return transportadora; }
}
