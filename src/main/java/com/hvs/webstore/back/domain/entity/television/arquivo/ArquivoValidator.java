package com.hvs.webstore.back.domain.entity.television.arquivo;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class ArquivoValidator extends Validator {

    private static final int MAX_LENGTH = 255;
    private static final int MIN_LENGTH = 4;
    private final Arquivo arquivo;

    public ArquivoValidator(ValidationHandler aHandler,
                            final Arquivo arquivo) {

        super(aHandler);
        this.arquivo = arquivo;
    }

    @Override
    public void validate() {

        validateNome();
        validateTipo();
        validateTamanho();
        validateCaminho();
    }

    private void validateNome() {

        final var nome = this.arquivo.getNome();

        if (nome == null) {
            this.validationHandler().append(new Erro("'name' cannot be null"));
        }

        if(nome != null && nome.isBlank()) {
            this.validationHandler().append(new Erro("'name' cannot be blank"));
        }

        assert nome != null;
        final int length = nome.trim().length();

        if(length < MIN_LENGTH || length > MAX_LENGTH) {
            this.validationHandler().append(new Erro("'name' must contain a minimum of 3 characters and a maximum of 255 characters"));
        }
    }

    private void validateTipo() {

        final var tipo = this.arquivo.getTipo();

        if (tipo == null) {
            this.validationHandler().append(new Erro("'type' cannot be null"));
        }

        if (tipo != null && tipo.isBlank()) {
            this.validationHandler().append(new Erro("'type' cannot be blank"));
        }

        assert tipo != null;
        final int length = tipo.trim().length();

        if(length < MIN_LENGTH || length > MAX_LENGTH) {
            this.validationHandler().append(new Erro("'type' must contain a minimum of 3 characters and a maximum of 255 characters"));
        }
    }

    private void validateTamanho() {

        final var tamanho = this.arquivo.getTamanho();

        if (tamanho == null) {
            this.validationHandler().append(new Erro("'size' cannot be null"));
        }

        if (tamanho != null && tamanho < 0) {
            this.validationHandler().append(new Erro("'size' cannot be blank"));
        }

        assert tamanho != null;
        final long length = tamanho.toString().length();

        if(length < 1) {
            this.validationHandler().append(new Erro("'size' must contain a minimum of 1 characters"));
        }
    }

    private void validateCaminho() {

        final var caminho = this.arquivo.getTipo();

        if (caminho == null) {
            this.validationHandler().append(new Erro("'path' cannot be null"));
        }

        if (caminho != null && caminho.isBlank()) {
            this.validationHandler().append(new Erro("'path' cannot be blank"));
        }

        assert caminho != null;
        final int length = arquivo.getCaminho().length();

        if(length < MIN_LENGTH || length > MAX_LENGTH) {
            this.validationHandler().append(new Erro("'path' must contain a minimum of 3 characters and a maximum of 255 characters"));
        }
    }

    public Arquivo getArquivo() { return arquivo; }
}
