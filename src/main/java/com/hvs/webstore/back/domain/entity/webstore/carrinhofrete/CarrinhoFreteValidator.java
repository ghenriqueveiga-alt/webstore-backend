package com.hvs.webstore.back.domain.entity.webstore.carrinhofrete;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class CarrinhoFreteValidator extends Validator {

    private final CarrinhoFrete frete;

    public CarrinhoFreteValidator(ValidationHandler aHandler,
                                  final CarrinhoFrete frete) {

        super(aHandler);
        this.frete = frete;
    }

    @Override
    public void validate() {

        if (frete.getCarrinho() == null)
            this.validationHandler().append(new Erro("carrinhoId should not be null"));
        if (frete.getFrete() == null)
            this.validationHandler().append(new Erro("freteId should not be null"));
        if (frete.getTransportadora() == null)
            this.validationHandler().append(new Erro("transportadoraId should not be null"));
    }

    public CarrinhoFrete getFrete() { return frete; }
}
