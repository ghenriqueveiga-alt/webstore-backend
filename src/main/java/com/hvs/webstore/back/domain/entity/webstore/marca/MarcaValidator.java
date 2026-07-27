package com.hvs.webstore.back.domain.entity.webstore.marca;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class MarcaValidator extends Validator {

    private final Marca marca;

    public MarcaValidator(ValidationHandler aHandler,
                          final Marca marca) {

        super(aHandler);
        this.marca = marca;
    }

    @Override
    public void validate() {
        if (marca.getNome() == null)
            this.validationHandler().append(new Erro("nome should not be null"));
    }

    public Marca getMarca() { return marca; }
}
