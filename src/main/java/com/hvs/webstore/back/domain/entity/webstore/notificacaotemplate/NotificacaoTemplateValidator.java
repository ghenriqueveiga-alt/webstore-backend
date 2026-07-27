package com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class NotificacaoTemplateValidator extends Validator {

    private final NotificacaoTemplate notificacaoTemplate;

    public NotificacaoTemplateValidator(ValidationHandler aHandler,
                                        final NotificacaoTemplate notificacaoTemplate) {

        super(aHandler);
        this.notificacaoTemplate = notificacaoTemplate;
    }

    @Override
    public void validate() {

        if (notificacaoTemplate.getNome() == null)
            this.validationHandler().append(new Erro("nome should not be null"));
        if (notificacaoTemplate.getTipo() == null)
            this.validationHandler().append(new Erro("tipo should not be null"));
        if (notificacaoTemplate.getAssunto() == null)
            this.validationHandler().append(new Erro("assunto should not be null"));
        if (notificacaoTemplate.getCorpo() == null)
            this.validationHandler().append(new Erro("corpo should not be null"));
    }

    public NotificacaoTemplate getNotificacaoTemplate() { return notificacaoTemplate; }
}
