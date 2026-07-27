package com.hvs.webstore.back.domain.entity.webstore.preco;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;

public class PrecoValidator extends Validator {

    private final Preco preco;

    public PrecoValidator(ValidationHandler aHandler,
                          final Preco preco) {

        super(aHandler);
        this.preco = preco;
    }

    @Override
    public void validate() {

    }

    public Preco getPreco() { return preco; }
}
