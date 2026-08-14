package com.hvs.webstore.back.domain.entity.television.programa;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class ProgramaValidator extends Validator {

    private static final int MAX_LENGTH = 255;
    private static final int MIN_LENGTH = 4;
    private final Programa programa;

    public ProgramaValidator(ValidationHandler aHandler,
                             final Programa programa) {

        super(aHandler);
        this.programa = programa;
    }

    @Override
    public void validate() {

        validateNome();
        validateEmProducao();
        validateTipo();
        validateTemporadas();
        validateLancamento();
        validateSinopse();
        validateEstudio();
        validateDiretor();
    }

    private void validateNome() {


        final var nome = this.programa.getNome();

        if(nome == null) {
            this.validationHandler().append(new Erro("'title' cannot be null"));
        }

        if(nome != null && nome.isBlank()) {
            this.validationHandler().append(new Erro("'title' cannot be blank"));
        }

        assert nome != null;
        final int length = nome.trim().length();

        if(length < MIN_LENGTH || length > MAX_LENGTH) {
            this.validationHandler().append(new Erro("'title' must contain a minimum of 4 characters " +
                    "and a maximum of 255 characters"));
        }
    }

    private void validateEmProducao() {

        final var emProducao = this.programa.getEmProducao();

        if(emProducao == null) {
            this.validationHandler().append(new Erro("'in production' cannot be null"));
        }

        assert emProducao != null;
        final int length = emProducao.toString().length();

        if(length < 1) {
            this.validationHandler().append(new Erro("'in production' must contain 0 or 1 characters"));
        }
    }

    private void validateTipo() {

        final var tipo = this.programa.getTipo().getDesc();

        if(tipo == null) {
            this.validationHandler().append(new Erro("'type' cannot be null"));
        }

        if(tipo != null && tipo.isBlank()) {
            this.validationHandler().append(new Erro("'type' cannot be blank"));
        }
    }

    private void validateTemporadas() {

        final var temporadas = this.programa.getTemporadas();

        if(temporadas == null) {
            this.validationHandler().append(new Erro("'season' cannot be null"));
        }

        assert temporadas != null;
        final int length = temporadas.toString().length();

        if(length < 1) {
            this.validationHandler().append(new Erro("'season' must contain 0 or 1 characters"));
        }
    }

    private void validateLancamento() {

        final var lancamento = this.programa.getLancamento();

        if(lancamento == null) {
            this.validationHandler().append(new Erro("'launch' cannot be null"));
        }
    }

    private void validateSinopse() {

        final var sinopse = this.programa.getSinopse();

        if(sinopse != null) {
            final int length = sinopse.trim().length();

            if(length > 2000) {
                this.validationHandler().append(new Erro("'synopsis' must contain a maximum of 2000 characters"));
            }
        }
    }

    private void validateEstudio() {

        final var estudio = this.programa.getEstudio();

        if(estudio != null) {
            final int length = estudio.trim().length();

            if(length > 255) {
                this.validationHandler().append(new Erro("'studio' must contain a maximum of 255 characters"));
            }
        }
    }

    private void validateDiretor() {

        final var diretor = this.programa.getDiretor();

        if(diretor != null) {
            final int length = diretor.trim().length();

            if(length > 255) {
                this.validationHandler().append(new Erro("'director' must contain a maximum of 255 characters"));
            }
        }
    }

    public Programa getPrograma() { return programa; }
}