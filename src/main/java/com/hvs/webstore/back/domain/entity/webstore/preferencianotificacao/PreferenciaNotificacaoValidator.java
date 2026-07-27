package com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class PreferenciaNotificacaoValidator extends Validator {

    private final PreferenciaNotificacao preferenciaNotificacao;

    public PreferenciaNotificacaoValidator(ValidationHandler aHandler,
                                           final PreferenciaNotificacao preferenciaNotificacao) {

        super(aHandler);
        this.preferenciaNotificacao = preferenciaNotificacao;
    }

    @Override
    public void validate() {

        if (preferenciaNotificacao.getUsuario() == null)
            this.validationHandler().append(new Erro("usuario should not be null"));
        if (preferenciaNotificacao.getTipo() == null)
            this.validationHandler().append(new Erro("tipo should not be null"));
    }

    public PreferenciaNotificacao getPreferenciaNotificacao() { return preferenciaNotificacao; }
}
