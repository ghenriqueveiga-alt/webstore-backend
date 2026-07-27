package com.hvs.webstore.back.domain.entity.webstore.notafiscal;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class NotaFiscalValidator extends Validator {

    private final NotaFiscal notaFiscal;

    public NotaFiscalValidator(ValidationHandler aHandler,
                               final NotaFiscal notaFiscal) {

        super(aHandler);
        this.notaFiscal = notaFiscal;
    }

    @Override
    public void validate() {

        if (notaFiscal.getPedido() == null)
            this.validationHandler().append(new Erro("pedidoId should not be null"));
        if (notaFiscal.getChaveAcesso() == null)
            this.validationHandler().append(new Erro("chaveAcesso should not be null"));
    }

    public NotaFiscal getNotaFiscal() { return notaFiscal; }
}
