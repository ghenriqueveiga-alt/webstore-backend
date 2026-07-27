package com.hvs.webstore.back.domain.entity.webstore.precopromocional;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class PrecoPromocionalValidator extends Validator {

    private final PrecoPromocional precoPromocional;

    public PrecoPromocionalValidator(ValidationHandler aHandler,
                                     final PrecoPromocional precoPromocional) {

        super(aHandler);
        this.precoPromocional = precoPromocional;
    }

    @Override
    public void validate() {

        if (precoPromocional.getPrecoPromocional() == null)
            this.validationHandler().append(new Erro("precoPromocional should not be null"));
        if (precoPromocional.getDataInicio() == null)
            this.validationHandler().append(new Erro("dataInicio should not be null"));
        if (precoPromocional.getDataFim() == null)
            this.validationHandler().append(new Erro("dataFim should not be null"));
        if (precoPromocional.getDataInicio() != null && precoPromocional.getDataFim() != null && !precoPromocional.getDataFim().isAfter(precoPromocional.getDataInicio()))
            this.validationHandler().append(new Erro("dataFim should be after dataInicio"));
    }

    public PrecoPromocional getPrecoPromocional() { return precoPromocional; }
}
