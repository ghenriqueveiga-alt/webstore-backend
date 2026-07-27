package com.hvs.webstore.back.domain.entity.webstore.frete;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class FreteValidator extends Validator {

    private final Frete frete;

    public FreteValidator(ValidationHandler aHandler,
                          final Frete frete) {

        super(aHandler);
        this.frete = frete;
    }

    @Override
    public void validate() {

        if (frete.getCepOrigem() == null)
            this.validationHandler().append(new Erro("cepOrigem should not be null"));
        if (frete.getCepDestino() == null)
            this.validationHandler().append(new Erro("cepDestino should not be null"));
        if (frete.getTipoFrete() == null)
            this.validationHandler().append(new Erro("tipoFrete should not be null"));
        if (frete.getPeso() == null)
            this.validationHandler().append(new Erro("peso should not be null"));
        if (frete.getComprimento() == null)
            this.validationHandler().append(new Erro("comprimento should not be null"));
        if (frete.getLargura() == null)
            this.validationHandler().append(new Erro("largura should not be null"));
        if (frete.getAltura() == null)
            this.validationHandler().append(new Erro("altura should not be null"));
    }

    public Frete getFrete() { return frete; }
}
