package com.hvs.webstore.back.domain.entity.webstore.logauditoria;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class LogAuditoriaValidator extends Validator {

    private final LogAuditoria logAuditoria;

    public LogAuditoriaValidator(ValidationHandler aHandler,
                                 final LogAuditoria logAuditoria) {

        super(aHandler);
        this.logAuditoria = logAuditoria;
    }

    @Override
    public void validate() {

        if (logAuditoria.getEntidadeNome() == null)
            this.validationHandler().append(new Erro("entidadeNome should not be null"));
        if (logAuditoria.getEntidadeId() == null)
            this.validationHandler().append(new Erro("entidadeId should not be null"));
        if (logAuditoria.getAcao() == null)
            this.validationHandler().append(new Erro("acao should not be null"));
    }

    public LogAuditoria getLogAuditoria() { return logAuditoria; }
}
