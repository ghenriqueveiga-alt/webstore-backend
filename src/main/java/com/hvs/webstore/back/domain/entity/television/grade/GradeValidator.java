package com.hvs.webstore.back.domain.entity.television.grade;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class GradeValidator extends Validator {

    private static final int MAX_LENGTH = 255;
    private static final int MIN_LENGTH = 4;
    private final Grade grade;

    public GradeValidator(ValidationHandler aHandler,
                          final Grade grade) {

        super(aHandler);
        this.grade = grade;
    }

    @Override
    public void validate() {

        validateNome();
        validateDescricao();
        validatePeriodoInicio();
        validatePeriodoFim();
        validateGradeAtiva();
    }

    private void validateNome() {

        final var nome = this.grade.getNome();

        if(nome == null) {
            this.validationHandler().append(new Erro("'name' cannot be null"));
        }

        if(nome != null && nome.isBlank()) {
            this.validationHandler().append(new Erro("'name' cannot be blank"));
        }

        assert nome != null;
        final int length = nome.trim().length();

        if(length < MIN_LENGTH || length > MAX_LENGTH) {
            this.validationHandler().append(new Erro("'name' must contain a minimum of 4 characters " +
                    "and a maximum of 255 characters"));
        }
    }

    private void validateDescricao() {

        final var descricao = this.grade.getDescricao();

        if(descricao == null) {
            this.validationHandler().append(new Erro("'description' cannot be null"));
        }

        if(descricao != null && descricao.isBlank()) {
            this.validationHandler().append(new Erro("'description' cannot be blank"));
        }

        assert descricao != null;
        final int length = descricao.trim().length();

        if(length < MIN_LENGTH || length > MAX_LENGTH) {
            this.validationHandler().append(new Erro("'description' must contain a minimum of 4 characters " +
                    "and a maximum of 255 characters"));
        }
    }

    private void validatePeriodoInicio() {

        final var periodoInicio = this.grade.getPeriodoInicio();

        if(periodoInicio == null) {
            this.validationHandler().append(new Erro("'start period' cannot be null"));
        }
    }

    private void validatePeriodoFim() {

        final var periodoFim = this.grade.getPeriodoFim();

        if(periodoFim == null) {
            this.validationHandler().append(new Erro("'end period' cannot be null"));
        }
    }

    private void validateGradeAtiva() {

        final var gradeAtiva = this.grade.getGradeAtiva();

        if(gradeAtiva == null) {
            this.validationHandler().append(new Erro("'active schedule' cannot be null"));
        }
    }

    public Grade getGrade() { return grade; }
}