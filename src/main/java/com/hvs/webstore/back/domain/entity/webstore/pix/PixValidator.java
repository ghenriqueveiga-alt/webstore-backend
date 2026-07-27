package com.hvs.webstore.back.domain.entity.webstore.pix;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class PixValidator extends Validator {

    private final Pix pix;

    public PixValidator(ValidationHandler aHandler,
                        final Pix pix) {

        super(aHandler);
        this.pix = pix;
    }

    @Override
    public void validate() {

        if (pix.getChavePix() == null)
            this.validationHandler().append(new Erro("chavePix should not be null"));
    }

    public Pix getPix() { return pix; }
}
