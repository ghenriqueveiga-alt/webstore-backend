package com.hvs.webstore.back.app.usecase.webstore.notafiscal;

import com.hvs.webstore.back.app.command.webstore.notafiscal.CreateNotaFiscalCommand;
import com.hvs.webstore.back.app.output.webstore.notafiscal.CreateNotaFiscalOutput;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscal;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscalDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateNotaFiscalUseCaseImpl extends CreateNotaFiscalUseCase {

    private final NotaFiscalDomainGateway gateway;

    public CreateNotaFiscalUseCaseImpl(NotaFiscalDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateNotaFiscalOutput> execute(CreateNotaFiscalCommand aNotaFiscalCommand) {

        var notification = Notification.create();
        var notaFiscal = NotaFiscal.create(aNotaFiscalCommand.aPedidoId(),
                                           aNotaFiscalCommand.aChaveAcesso(),
                                           aNotaFiscalCommand.aNumero(),
                                           aNotaFiscalCommand.aSerie(),
                                           aNotaFiscalCommand.aTipoAmbienteCode(),
                                           aNotaFiscalCommand.aXml(),
                                           aNotaFiscalCommand.aDanfeUrl());
        notaFiscal.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(notaFiscal);
    }

    @Transactional
    private Either<Notification, CreateNotaFiscalOutput> create(NotaFiscal aNotaFiscal) {

        return Try(() -> gateway.create(aNotaFiscal))
                .toEither().bimap(Notification::create, CreateNotaFiscalOutput::from);
    }
}
