package com.hvs.webstore.back.domain.entity.webstore.tokenverificacao;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class TokenVerificacaoValidator extends Validator {

    private final TokenVerificacao token;

    public TokenVerificacaoValidator(ValidationHandler aHandler,
                                     final TokenVerificacao token) {

        super(aHandler);
        this.token = token;
    }

    @Override
    public void validate() {

        if (token.getToken() == null)
            this.validationHandler().append(new Erro("token should not be null"));
        if (token.getTipoToken() == null)
            this.validationHandler().append(new Erro("tipoToken should not be null"));
        if (token.getUsuario() == null)
            this.validationHandler().append(new Erro("usuario should not be null"));
        if (token.getExpiradoEm() == null)
            this.validationHandler().append(new Erro("expiradoEm should not be null"));
    }

    public TokenVerificacao getTokenVerificacao() { return token; }
}
