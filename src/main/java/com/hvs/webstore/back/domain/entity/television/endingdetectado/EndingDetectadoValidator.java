package com.hvs.webstore.back.domain.entity.television.endingdetectado;

import com.hvs.webstore.back.domain.Identifier;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class EndingDetectadoValidator extends Validator {

    private final EndingDetectado endingDetectado;

    public EndingDetectadoValidator(final ValidationHandler aHandler,
                                    final EndingDetectado aEndingDetectado) {

        super(aHandler);
        this.endingDetectado = aEndingDetectado;
    }

    @Override
    public void validate() {

        this.validateEpisodio();
        this.validateArquivo();
        this.validateInicio();
        this.validateDetectado();
    }

    private void validateEpisodio() {

        if (this.endingDetectado.getEpisodio() == null) {
            this.validationHandler().append(new Erro("'episode' cannot be null"));
        }
    }

    private void validateArquivo() {

        if (this.endingDetectado.getArquivo() == null) {
            this.validationHandler().append(new Erro("'archive' cannot be null"));
        }
    }

    private void validateInicio() {

        if (this.endingDetectado.getInicio() == null) {
            this.validationHandler().append(new Erro("'start' cannot be null"));
        }
    }

    private void validateDetectado() {

        if (this.endingDetectado.getDetectado() == null) {
            this.validationHandler().append(new Erro("'detected' cannot be null"));
        }
    }

    public EndingDetectado getEndingDetectado() {

        return this.endingDetectado;
    }
}
