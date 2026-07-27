package com.hvs.webstore.back.domain.entity.webstore.avaliacao;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class AvaliacaoValidator extends Validator {

    private final Avaliacao avaliacao;

    public AvaliacaoValidator(ValidationHandler aHandler,
                              final Avaliacao avaliacao) {

        super(aHandler);
        this.avaliacao = avaliacao;
    }

    @Override
    public void validate() {

        if (avaliacao.getProduto() == null)
            this.validationHandler().append(new Erro("produto should not be null"));
        if (avaliacao.getUsuario() == null)
            this.validationHandler().append(new Erro("usuario should not be null"));
        if (avaliacao.getNota() == null)
            this.validationHandler().append(new Erro("nota should not be null"));
        else if (avaliacao.getNota() < 1 || avaliacao.getNota() > 5)
            this.validationHandler().append(new Erro("nota must be between 1 and 5"));
    }

    public Avaliacao getAvaliacao() { return avaliacao; }
}
