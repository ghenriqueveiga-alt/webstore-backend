package com.hvs.webstore.back.domain.entity.webstore.cupom;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class CupomValidator extends Validator {

    private final Cupom cupom;

    public CupomValidator(ValidationHandler aHandler,
                          final Cupom cupom) {

        super(aHandler);
        this.cupom = cupom;
    }

    @Override
    public void validate() {

        if (cupom.getCodigo() == null)
            this.validationHandler().append(new Erro("codigo should not be null"));
        if (cupom.getTipoDesconto() == null)
            this.validationHandler().append(new Erro("tipoDesconto should not be null"));
        if (cupom.getValorDesconto() == null)
            this.validationHandler().append(new Erro("valorDesconto should not be null"));
        else if (cupom.getValorDesconto() <= 0)
            this.validationHandler().append(new Erro("valorDesconto must be positive"));
    }

    public Cupom getCupom() { return cupom; }
}
