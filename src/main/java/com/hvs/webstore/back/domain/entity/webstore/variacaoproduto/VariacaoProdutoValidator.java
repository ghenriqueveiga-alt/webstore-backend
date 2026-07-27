package com.hvs.webstore.back.domain.entity.webstore.variacaoproduto;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;
import com.hvs.webstore.back.domain.validation.notification.Erro;

public class VariacaoProdutoValidator extends Validator {

    private final VariacaoProduto variacaoProduto;

    public VariacaoProdutoValidator(ValidationHandler aHandler,
                                    final VariacaoProduto variacaoProduto) {

        super(aHandler);
        this.variacaoProduto = variacaoProduto;
    }

    @Override
    public void validate() {

        if (variacaoProduto.getNome() == null)
            this.validationHandler().append(new Erro("nome should not be null"));
        if (variacaoProduto.getValor() == null)
            this.validationHandler().append(new Erro("valor should not be null"));
        if (variacaoProduto.getProduto() == null)
            this.validationHandler().append(new Erro("produto should not be null"));
    }

    public VariacaoProduto getVariacaoProduto() { return variacaoProduto; }
}
