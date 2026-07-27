package com.hvs.webstore.back.domain.entity.webstore.listadesejos;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class ItemListaDesejosValidator extends Validator {

    private final ItemListaDesejos itemListaDesejos;

    public ItemListaDesejosValidator(ValidationHandler aHandler,
                                     final ItemListaDesejos itemListaDesejos) {

        super(aHandler);
        this.itemListaDesejos = itemListaDesejos;
    }

    @Override
    public void validate() {
        if (itemListaDesejos.getProduto() == null)
            this.validationHandler().append(new Erro("produto should not be null"));
    }

    public ItemListaDesejos getItemListaDesejos() { return itemListaDesejos; }
}
