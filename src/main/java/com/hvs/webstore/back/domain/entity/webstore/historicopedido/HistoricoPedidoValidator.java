package com.hvs.webstore.back.domain.entity.webstore.historicopedido;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class HistoricoPedidoValidator extends Validator {

    private final HistoricoPedido historicoPedido;

    public HistoricoPedidoValidator(ValidationHandler aHandler,
                                    final HistoricoPedido historicoPedido) {

        super(aHandler);
        this.historicoPedido = historicoPedido;
    }

    @Override
    public void validate() {

        if (historicoPedido.getPedido() == null)
            this.validationHandler().append(new Erro("pedidoId should not be null"));
        if (historicoPedido.getStatusNovo() == null)
            this.validationHandler().append(new Erro("statusNovo should not be null"));
    }

    public HistoricoPedido getHistoricoPedido() { return historicoPedido; }
}
