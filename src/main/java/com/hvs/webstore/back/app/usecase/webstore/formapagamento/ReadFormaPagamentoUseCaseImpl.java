package com.hvs.webstore.back.app.usecase.webstore.formapagamento;

import com.hvs.webstore.back.app.command.webstore.formapagamento.ReadFormaPagamentoCommand;
import com.hvs.webstore.back.app.output.webstore.formapagamento.ReadFormaPagamentoOutput;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamento;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamentoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamentoId;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamentoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadFormaPagamentoUseCaseImpl extends ReadFormaPagamentoUseCase {

    private final FormaPagamentoDomainGateway gateway;

    public ReadFormaPagamentoUseCaseImpl(FormaPagamentoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadFormaPagamentoOutput> execute(ReadFormaPagamentoCommand aFormaPagamentoCommand) {

        Optional<FormaPagamento> aFormaPagamentoDB = aFormaPagamentoCommand.aId() != null ?
                gateway.read(FormaPagamentoId.from(aFormaPagamentoCommand.aId())) : gateway.readByUuid(FormaPagamentoUuid.from(aFormaPagamentoCommand.aUuid()));

        if (aFormaPagamentoDB.isPresent())
            return Try(aFormaPagamentoDB::get)
                    .toEither().bimap(Notification::create, ReadFormaPagamentoOutput::from);

        var aFormaPagamentoId = aFormaPagamentoCommand.aId() != null ? String.valueOf(aFormaPagamentoCommand.aId()) : aFormaPagamentoCommand.aUuid();

        return Either.left(Notification.create(new Error("FormaPagamento not found: " + aFormaPagamentoId)));
    }
}
