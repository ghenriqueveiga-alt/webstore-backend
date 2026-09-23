package com.hvs.ws.back.domain.entity.bloco;

import com.hvs.ws.back.domain.validation.ValidationHandler;
import com.hvs.ws.back.domain.validation.Validator;
import com.hvs.ws.back.domain.validation.notification.Erro;

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
        validateDiaSemana();
        validateFaixaHorario();
        validateTipoBloco();
    }

    private void validateHoradaio() {

        final var horario = this.bloco.getHorario();

        if(horario == null) {
            this.validationHandler().append(new Erro("'time' cannot be null"));
        }
    }

    private void validateDiaSemana() {

        final var diaSemana = this.bloco.getDiaSemana();

        if(diaSemana == null) {
            this.validationHandler().append(new Erro("'day of week' cannot be null"));
        }
    }

    private void validateFaixaHorario() {

        final var faixaHorario = this.bloco.getFaixaHorario();

        if(faixaHorario == null) {
            this.validationHandler().append(new Erro("'time range' cannot be null"));
        }
    }

    private void validateTipoBloco() {

        final var tipoBloco = this.bloco.getTipoBloco();

        if(tipoBloco == null) {
            this.validationHandler().append(new Erro("'block type' cannot be null"));
        }
    }

    public Bloco getBloco() { return bloco; }
}