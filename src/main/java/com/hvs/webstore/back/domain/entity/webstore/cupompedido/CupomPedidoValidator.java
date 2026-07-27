package com.hvs.webstore.back.domain.entity.webstore.cupompedido;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class CupomPedidoValidator extends Validator {

    private final CupomPedido cupomPedido;

    public CupomPedidoValidator(ValidationHandler aHandler,
                                final CupomPedido cupomPedido) {

        super(aHandler);
        this.cupomPedido = cupomPedido;
    }

    @Override
    public void validate() {

        if (cupomPedido.getCupom() == null)
            this.validationHandler().append(new Erro("cupom should not be null"));
        if (cupomPedido.getPedido() == null)
            this.validationHandler().append(new Erro("pedido should not be null"));
        if (cupomPedido.getValorDesconto() == null)
            this.validationHandler().append(new Erro("valorDesconto should not be null"));
    }

    public CupomPedido getCupomPedido() { return cupomPedido; }
}
