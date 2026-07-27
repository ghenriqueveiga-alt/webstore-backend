package com.hvs.webstore.back.domain.entity.television.episodio;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class EpisodioValidator extends Validator {

    private static final int MAX_LENGTH = 255;
    private static final int MIN_LENGTH = 4;
    private final Episodio episodio;

    public EpisodioValidator(ValidationHandler aHandler,
                             final Episodio episodio) {

        super(aHandler);
        this.episodio = episodio;
    }

    @Override
    public void validate() {

        validateArquivo();
        validateTitulo();
        validateNumero();
        validateTemporada();
        validatePrograma();
    }

    private void validateArquivo() {

        final var arquivo = this.episodio.getArquivo();

        if(arquivo == null) {
            this.validationHandler().append(new Erro("'archive' cannot be null"));
        }
    }

    private void validateTitulo() {

        final var titulo = this.episodio.getTitulo();

        if(titulo == null) {
            this.validationHandler().append(new Erro("'title' cannot be null"));
        }

        if(titulo != null && titulo.isBlank()) {
            this.validationHandler().append(new Erro("'title' cannot be blank"));
        }

        assert titulo != null;
        final int length = titulo.trim().length();

        if(length < MIN_LENGTH || length > MAX_LENGTH) {
            this.validationHandler().append(new Erro("'title' must contain a minimum of 4 characters " +
                    "and a maximum of 255 characters"));
        }
    }

    private void validateNumero() {

        final var numero = this.episodio.getNumero();

        if(numero == null) {
            this.validationHandler().append(new Erro("'number' cannot be null"));
        }

        assert numero != null;
        final int length = numero.toString().length();

        if(length > MIN_LENGTH) {
            this.validationHandler().append(new Erro("'number' must contain a maximum of 4 characters"));
        }
    }

    private void validateTemporada() {

        final var temporada = this.episodio.getTemporada();

        if(temporada == null) {
            this.validationHandler().append(new Erro("'season' cannot be null"));
        }

        assert temporada != null;
        final int length = temporada.toString().length();

        if(length < MIN_LENGTH) {
            this.validationHandler().append(new Erro("'season' must contain a minimum of 4 characters"));
        }
    }

    private void validatePrograma() {

        final var programa = this.episodio.getPrograma();

        if(programa == null) {
            this.validationHandler().append(new Erro("'program' cannot be null"));
        }
    }

    public Episodio getEpisodio() { return episodio; }
}