package com.hvs.webstore.back.domain.entity.webstore.endereco;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class EnderecoValidator extends Validator {

    private final Endereco endereco;

    public EnderecoValidator(ValidationHandler aHandler,
                             final Endereco endereco) {

        super(aHandler);
        this.endereco = endereco;
    }

    @Override
    public void validate() {

        if (endereco.getLogradouro() == null)
            this.validationHandler().append(new Erro("logradouro should not be null"));
    }

    public Endereco getEndereco() { return endereco; }
}
