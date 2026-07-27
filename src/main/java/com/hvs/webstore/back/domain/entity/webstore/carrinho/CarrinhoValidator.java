package com.hvs.webstore.back.domain.entity.webstore.carrinho;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class CarrinhoValidator extends Validator {

    private final Carrinho carrinho;

    public CarrinhoValidator(ValidationHandler aHandler,
                             final Carrinho carrinho) {

        super(aHandler);
        this.carrinho = carrinho;
    }

    @Override
    public void validate() {

        if (carrinho.getUsuario() == null)
            this.validationHandler().append(new Erro("usuario should not be null"));
    }

    public Carrinho getCarrinho() { return carrinho; }
}
