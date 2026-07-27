package com.hvs.webstore.back.domain.entity.television.corte;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class CorteValidator extends Validator {

    private final Corte corte;

    public CorteValidator(ValidationHandler aHandler,
                          final Corte corte) {

        super(aHandler);
        this.corte = corte;
    }

    @Override
    public void validate() {

        validateArquivo();
        validateTipo();
        validateDuracao();
        validateEpisodio();
    }

    private void validateArquivo() {

        final var arquivo = this.corte.getArquivo();

        if(arquivo == null) {
            this.validationHandler().append(new Erro("'archive' cannot be null"));
        }
    }

    private void validateTipo() {

        final var tipo = this.corte.getTipo().getDesc();

        if(tipo == null) {
            this.validationHandler().append(new Erro("'type' cannot be null"));
        }

        if(tipo != null && tipo.isBlank()) {
            this.validationHandler().append(new Erro("'type' cannot be blank"));
        }
    }

    private void validateDuracao() {

        final var duracao = this.corte.getDuracao();

        if(duracao == null) {
            this.validationHandler().append(new Erro("'duration' cannot be null"));
        }

        if(duracao != null && duracao.isBlank()) {
            this.validationHandler().append(new Erro("'duration' cannot be blank"));
        }
    }

    private void validateEpisodio() {

        final var episodio = this.corte.getEpisodio();

        if(episodio == null) {
            this.validationHandler().append(new Erro("'episode' cannot be null"));
        }
    }

    public Corte getCorte() { return corte; }
}