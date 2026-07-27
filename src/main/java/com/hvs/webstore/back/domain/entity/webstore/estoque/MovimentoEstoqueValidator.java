package com.hvs.webstore.back.domain.entity.webstore.estoque;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class MovimentoEstoqueValidator extends Validator {

    private final MovimentoEstoque movimento;

    public MovimentoEstoqueValidator(ValidationHandler aHandler,
                                     final MovimentoEstoque movimento) {

        super(aHandler);
        this.movimento = movimento;
    }

    @Override
    public void validate() {

        if (movimento.getEstoque() == null)
            this.validationHandler().append(new Erro("estoque should not be null"));
        if (movimento.getTipo() == null)
            this.validationHandler().append(new Erro("tipo should not be null"));
        if (movimento.getQuantidade() == null)
            this.validationHandler().append(new Erro("quantidade should not be null"));
    }

    public MovimentoEstoque getMovimento() { return movimento; }
}
