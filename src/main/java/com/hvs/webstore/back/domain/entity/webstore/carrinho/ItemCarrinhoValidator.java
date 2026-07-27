package com.hvs.webstore.back.domain.entity.webstore.carrinho;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class ItemCarrinhoValidator extends Validator {

    private final ItemCarrinho itemCarrinho;

    public ItemCarrinhoValidator(ValidationHandler aHandler,
                                 final ItemCarrinho itemCarrinho) {

        super(aHandler);
        this.itemCarrinho = itemCarrinho;
    }

    @Override
    public void validate() {

        if (itemCarrinho.getProduto() == null)
            this.validationHandler().append(new Erro("produto should not be null"));
        if (itemCarrinho.getQuantidade() == null)
            this.validationHandler().append(new Erro("quantidade should not be null"));
        if (itemCarrinho.getProduto() == null)
            this.validationHandler().append(new Erro("produto should not be null"));
    }

    public ItemCarrinho getItemCarrinho() { return itemCarrinho; }
}
