package com.hvs.webstore.back.domain.entity.webstore.imposto;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class ImpostoValidator extends Validator {

    private final Imposto imposto;

    public ImpostoValidator(ValidationHandler aHandler,
                            final Imposto imposto) {

        super(aHandler);
        this.imposto = imposto;
    }

    @Override
    public void validate() {

        if (imposto.getNome() == null)
            this.validationHandler().append(new Erro("nome should not be null"));
        if (imposto.getAliquota() == null)
            this.validationHandler().append(new Erro("aliquota should not be null"));
        if (imposto.getTipoImposto() == null)
            this.validationHandler().append(new Erro("tipoImposto should not be null"));
    }

    public Imposto getImposto() { return imposto; }
}
