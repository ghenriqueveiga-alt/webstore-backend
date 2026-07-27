package com.hvs.webstore.back.domain.entity.webstore.metaloja;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class MetaLojaValidator extends Validator {

    private final MetaLoja metaLoja;

    public MetaLojaValidator(ValidationHandler aHandler,
                             final MetaLoja metaLoja) {

        super(aHandler);
        this.metaLoja = metaLoja;
    }

    @Override
    public void validate() {

        if (metaLoja.getChave() == null)
            this.validationHandler().append(new Erro("chave should not be null"));
        if (metaLoja.getValor() == null)
            this.validationHandler().append(new Erro("valor should not be null"));
    }

    public MetaLoja getMetaLoja() { return metaLoja; }
}
