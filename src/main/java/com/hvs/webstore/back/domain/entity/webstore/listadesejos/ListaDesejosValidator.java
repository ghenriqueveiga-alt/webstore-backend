package com.hvs.webstore.back.domain.entity.webstore.listadesejos;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class ListaDesejosValidator extends Validator {

    private final ListaDesejos listaDesejos;

    public ListaDesejosValidator(ValidationHandler aHandler,
                                 final ListaDesejos listaDesejos) {

        super(aHandler);
        this.listaDesejos = listaDesejos;
    }

    @Override
    public void validate() {
        if (listaDesejos.getUsuario() == null)
            this.validationHandler().append(new Erro("usuario should not be null"));
    }

    public ListaDesejos getListaDesejos() { return listaDesejos; }
}
