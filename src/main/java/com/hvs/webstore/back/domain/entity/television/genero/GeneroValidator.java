package com.hvs.webstore.back.domain.entity.television.genero;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class GeneroValidator extends Validator {

    private static final int MAX_LENGTH = 255;
    private static final int MIN_LENGTH = 2;
    private final Genero genero;

    public GeneroValidator(ValidationHandler aHandler, final Genero genero) {
        super(aHandler);
        this.genero = genero;
    }

    @Override
    public void validate() {
        validateNome();
    }

    private void validateNome() {
        final var nome = this.genero.getNome();
        if (nome == null) {
            this.validationHandler().append(new Erro("'nome' cannot be null"));
        }
        if (nome != null && nome.isBlank()) {
            this.validationHandler().append(new Erro("'nome' cannot be blank"));
        }
        if (nome != null) {
            final int length = nome.trim().length();
            if (length < MIN_LENGTH || length > MAX_LENGTH) {
                this.validationHandler().append(new Erro("'nome' must contain a minimum of " + MIN_LENGTH +
                        " characters and a maximum of " + MAX_LENGTH + " characters"));
            }
        }
    }
}
