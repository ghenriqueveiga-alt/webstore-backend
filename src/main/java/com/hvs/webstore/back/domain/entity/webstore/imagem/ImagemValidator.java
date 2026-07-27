package com.hvs.webstore.back.domain.entity.webstore.imagem;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;

public class ImagemValidator extends Validator {

    private final Imagem imagem;

    public ImagemValidator(ValidationHandler aHandler,
                           final Imagem imagem) {

        super(aHandler);
        this.imagem = imagem;
    }

    @Override
    public void validate() {

    }

    public Imagem getImagem() { return imagem; }
}