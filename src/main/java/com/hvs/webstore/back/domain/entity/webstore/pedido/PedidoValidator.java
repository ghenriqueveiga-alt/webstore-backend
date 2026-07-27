package com.hvs.webstore.back.domain.entity.webstore.pedido;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;

public class PedidoValidator extends Validator {

    private final Pedido pedido;

    public PedidoValidator(ValidationHandler aHandler,
                           final Pedido pedido) {

        super(aHandler);
        this.pedido = pedido;
    }

    @Override
    public void validate() {

    }

    public Pedido getPedido() { return pedido; }
}
