package com.hvs.webstore.back.domain.entity.webstore.permissao;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class PermissaoValidator extends Validator {

    private final Permissao permissao;

    public PermissaoValidator(ValidationHandler aHandler,
                              final Permissao permissao) {

        super(aHandler);
        this.permissao = permissao;
    }

    @Override
    public void validate() {

        if (permissao.getNome() == null)
            this.validationHandler().append(new Erro("nome should not be null"));
        if (permissao.getChave() == null)
            this.validationHandler().append(new Erro("chave should not be null"));
    }

    public Permissao getPermissao() { return permissao; }
}
