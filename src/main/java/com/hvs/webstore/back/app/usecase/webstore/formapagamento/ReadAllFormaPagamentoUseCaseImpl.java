package com.hvs.webstore.back.app.usecase.webstore.formapagamento;

import com.hvs.webstore.back.app.command.webstore.formapagamento.ReadAllFormaPagamentoCommand;
import com.hvs.webstore.back.app.output.webstore.formapagamento.ReadAllFormaPagamentoOutput;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamento;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamentoDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

public class ReadAllFormaPagamentoUseCaseImpl extends ReadAllFormaPagamentoUseCase {

    private final FormaPagamentoDomainGateway gateway;

    public ReadAllFormaPagamentoUseCaseImpl(FormaPagamentoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllFormaPagamentoOutput> execute(ReadAllFormaPagamentoCommand aFormaPagamentoCommand) {

        Pagination<FormaPagamento> formaPagination = gateway.readAll(aFormaPagamentoCommand.aSearchQuery());
        List<FormaPagamento> lista = formaPagination.aContent()
                .stream().filter(forma -> forma.getStatusCode().getDesc().equals("Active")).toList();

        if (!lista.isEmpty()) {
            return Try(() -> gateway.readAll(aFormaPagamentoCommand.aSearchQuery())).toEither().bimap(
                    Notification::create, ReadAllFormaPagamentoOutput::from);
        } else {
            return Either.left(Notification.create(new Error("No FormaPagamento was found.")));
        }
    }
}
