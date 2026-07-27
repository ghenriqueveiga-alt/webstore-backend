package com.hvs.webstore.back.domain.entity.webstore.categoria;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;

public class CategoriaValidator extends Validator {

    private final Categoria categoria;

    public CategoriaValidator(ValidationHandler aHandler,
                              final Categoria categoria) {

        super(aHandler);
        this.categoria = categoria;
    }

    @Override
    public void validate() {
    }

    public Categoria getCategoria() { return categoria; }
}
