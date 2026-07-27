package com.hvs.webstore.back.domain.entity.webstore.pedido;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class ItemPedidoValidator extends Validator {

    private final ItemPedido itemPedido;

    public ItemPedidoValidator(ValidationHandler aHandler,
                               final ItemPedido itemPedido) {

        super(aHandler);
        this.itemPedido = itemPedido;
    }

    @Override
    public void validate() {

        if (itemPedido.getProduto() == null)
            this.validationHandler().append(new Erro("produto should not be null"));
        if (itemPedido.getQuantidade() == null)
            this.validationHandler().append(new Erro("quantidade should not be null"));
        if (itemPedido.getPreco() == null)
            this.validationHandler().append(new Erro("preco should not be null"));
    }

    public ItemPedido getItemPedido() { return itemPedido; }
}
