package com.hvs.webstore.back.domain.entity.webstore.anexo;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class AnexoValidator extends Validator {

    private final Anexo anexo;

    public AnexoValidator(ValidationHandler aHandler,
                          final Anexo anexo) {

        super(aHandler);
        this.anexo = anexo;
    }

    @Override
    public void validate() {

        if (anexo.getEntidadeNome() == null)
            this.validationHandler().append(new Erro("entidadeNome should not be null"));
        if (anexo.getEntidadeId() == null)
            this.validationHandler().append(new Erro("entidadeId should not be null"));
        if (anexo.getNome() == null)
            this.validationHandler().append(new Erro("nome should not be null"));
    }

    public Anexo getAnexo() { return anexo; }
}
