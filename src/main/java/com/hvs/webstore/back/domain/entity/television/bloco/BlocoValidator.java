package com.hvs.webstore.back.domain.entity.television.bloco;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class BlocoValidator extends Validator {

    private final Bloco bloco;

    public BlocoValidator(ValidationHandler aHandler,
                          final Bloco bloco) {

        super(aHandler);
        this.bloco = bloco;
    }

    @Override
    public void validate() {

        validateHoradaio();
    }

    private void validateHoradaio() {

        final var horario = this.bloco.getHorario();

        if(horario == null) {
            this.validationHandler().append(new Erro("'time' cannot be null"));
        }
    }

    public Bloco getBloco() { return bloco; }
}