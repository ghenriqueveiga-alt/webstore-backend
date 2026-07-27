package com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class CategoriaHierarquiaValidator extends Validator {

    private final CategoriaHierarquia categoriaHierarquia;

    public CategoriaHierarquiaValidator(ValidationHandler aHandler,
                                        final CategoriaHierarquia categoriaHierarquia) {

        super(aHandler);
        this.categoriaHierarquia = categoriaHierarquia;
    }

    @Override
    public void validate() {

        if (categoriaHierarquia.getCategoria() == null)
            this.validationHandler().append(new Erro("categoria should not be null"));
        if (categoriaHierarquia.getNivel() == null || categoriaHierarquia.getNivel() < 0)
            this.validationHandler().append(new Erro("nivel should be >= 0"));
    }

    public CategoriaHierarquia getCategoriaHierarquia() { return categoriaHierarquia; }
}
