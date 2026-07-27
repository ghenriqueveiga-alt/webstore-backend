package com.hvs.webstore.back.domain.entity.webstore.role;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class RoleValidator extends Validator {

    private final Role role;

    public RoleValidator(ValidationHandler aHandler,
                          final Role role) {

        super(aHandler);
        this.role = role;
    }

    @Override
    public void validate() {

        if (role.getNome() == null)
            this.validationHandler().append(new Erro("nome should not be null"));
    }

    public Role getRole() { return role; }
}
