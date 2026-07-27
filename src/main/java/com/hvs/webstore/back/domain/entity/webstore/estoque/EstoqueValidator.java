package com.hvs.webstore.back.domain.entity.webstore.estoque;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class EstoqueValidator extends Validator {

    private final Estoque estoque;

    public EstoqueValidator(ValidationHandler aHandler,
                            final Estoque estoque) {

        super(aHandler);
        this.estoque = estoque;
    }

    @Override
    public void validate() {

        if (estoque.getProduto() == null)
            this.validationHandler().append(new Erro("produto should not be null"));
        if (estoque.getQuantidade() == null)
            this.validationHandler().append(new Erro("quantidade should not be null"));
        if (estoque.getQuantidadeMinima() == null)
            this.validationHandler().append(new Erro("quantidadeMinima should not be null"));
    }

    public Estoque getEstoque() { return estoque; }
}
