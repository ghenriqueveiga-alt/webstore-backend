package com.hvs.webstore.back.domain.entity.webstore.pagamento;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class PagamentoValidator extends Validator {

    private final Pagamento pagamento;

    public PagamentoValidator(ValidationHandler aHandler,
                              final Pagamento pagamento) {

        super(aHandler);
        this.pagamento = pagamento;
    }

    @Override
    public void validate() {

        if (pagamento.getPedido() == null)
            this.validationHandler().append(new Erro("pedido should not be null"));
        if (pagamento.getValor() == null)
            this.validationHandler().append(new Erro("valor should not be null"));
        if (pagamento.getStatusTransacao() == null)
            this.validationHandler().append(new Erro("statusTransacao should not be null"));
    }

    public Pagamento getPagamento() { return pagamento; }
}
