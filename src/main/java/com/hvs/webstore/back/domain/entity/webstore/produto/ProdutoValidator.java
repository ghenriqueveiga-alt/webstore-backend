package com.hvs.webstore.back.domain.entity.webstore.produto;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;

public class ProdutoValidator extends Validator {

    private final Produto produto;

    public ProdutoValidator(ValidationHandler aHandler,
                            final Produto produto) {

        super(aHandler);
        this.produto = produto;
    }

    @Override
    public void validate() {

    }

    public Produto getProduto() { return produto; }
}
