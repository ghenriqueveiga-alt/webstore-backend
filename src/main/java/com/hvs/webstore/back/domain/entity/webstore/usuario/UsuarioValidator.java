package com.hvs.webstore.back.domain.entity.webstore.usuario;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class UsuarioValidator extends Validator {

    private final Usuario usuario;

    public UsuarioValidator(ValidationHandler aHandler,
                            final Usuario usuario) {
        super(aHandler);
        this.usuario = usuario;
    }

    @Override
    public void validate() {
        if (usuario.getNome() == null)
            this.validationHandler().append(new Erro("Nome should not be null"));
        if (usuario.getEmail() == null)
            this.validationHandler().append(new Erro("Email should not be null"));
    }

    public Usuario getUsuario() { return usuario; }
}
