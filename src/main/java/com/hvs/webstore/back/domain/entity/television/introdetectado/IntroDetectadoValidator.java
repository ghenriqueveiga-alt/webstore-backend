package com.hvs.webstore.back.domain.entity.television.introdetectado;

import com.hvs.webstore.back.domain.Identifier;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class IntroDetectadoValidator extends Validator {

    private final IntroDetectado introDetectado;

    public IntroDetectadoValidator(final ValidationHandler aHandler,
                                   final IntroDetectado aIntroDetectado) {

        super(aHandler);
        this.introDetectado = aIntroDetectado;
    }

    @Override
    public void validate() {

        this.validateEpisodio();
        this.validateArquivo();
        this.validateInicio();
        this.validateDetectado();
    }

    private void validateEpisodio() {

        if (this.introDetectado.getEpisodio() == null) {
            this.validationHandler().append(new Erro("'episode' cannot be null"));
        }
    }

    private void validateArquivo() {

        if (this.introDetectado.getArquivo() == null) {
            this.validationHandler().append(new Erro("'archive' cannot be null"));
        }
    }

    private void validateInicio() {

        if (this.introDetectado.getInicio() == null) {
            this.validationHandler().append(new Erro("'start' cannot be null"));
        }
    }

    private void validateDetectado() {

        if (this.introDetectado.getDetectado() == null) {
            this.validationHandler().append(new Erro("'detected' cannot be null"));
        }
    }

    public IntroDetectado getIntroDetectado() {

        return this.introDetectado;
    }
}
