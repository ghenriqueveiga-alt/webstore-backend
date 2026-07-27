package com.hvs.webstore.back.domain.entity.webstore.formapagamento;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;

public class FormaPagamentoValidator extends Validator {

    private final FormaPagamento formaPagamento;

    public FormaPagamentoValidator(ValidationHandler aHandler,
                                   final FormaPagamento formaPagamento) {

        super(aHandler);
        this.formaPagamento = formaPagamento;
    }

    @Override
    public void validate() {

    }

    public FormaPagamento getFormaPagamento() { return formaPagamento; }
}
