package com.hvs.webstore.back.app.usecase.webstore.formapagamento;

import com.hvs.webstore.back.app.command.webstore.formapagamento.DeleteFormaPagamentoCommand;
import com.hvs.webstore.back.app.output.webstore.formapagamento.DeleteFormaPagamentoOutput;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamento;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamentoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamentoId;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamentoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteFormaPagamentoUseCaseImpl extends DeleteFormaPagamentoUseCase {

    private final FormaPagamentoDomainGateway gateway;

    public DeleteFormaPagamentoUseCaseImpl(FormaPagamentoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteFormaPagamentoOutput> execute(DeleteFormaPagamentoCommand aFormaPagamentoCommand) {

        Optional<FormaPagamento> aFormaPagamentoDB = aFormaPagamentoCommand.aId() != null ?
                gateway.read(FormaPagamentoId.from(aFormaPagamentoCommand.aId())) : gateway.readByUuid(FormaPagamentoUuid.from(aFormaPagamentoCommand.aUuid()));

        if (aFormaPagamentoDB.isEmpty())
            return Either.left(Notification.create(new Error("FormaPagamento not found: " + (aFormaPagamentoCommand.aId() != null ?
                    aFormaPagamentoCommand.aId() : aFormaPagamentoCommand.aUuid()))));

        return delete(aFormaPagamentoDB.get());
    }

    @Transactional
    private Either<Notification, DeleteFormaPagamentoOutput> delete(FormaPagamento aFormaPagamento) {

        return Try(() -> {
            gateway.delete(aFormaPagamento);
            return DeleteFormaPagamentoOutput.from(aFormaPagamento);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
