package com.hvs.webstore.back.app.usecase.webstore.notafiscal;

import com.hvs.webstore.back.app.command.webstore.notafiscal.ReadAllNotaFiscalCommand;
import com.hvs.webstore.back.app.output.webstore.notafiscal.ReadAllNotaFiscalOutput;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscalDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadAllNotaFiscalUseCaseImpl extends ReadAllNotaFiscalUseCase {

    private final NotaFiscalDomainGateway gateway;

    public ReadAllNotaFiscalUseCaseImpl(NotaFiscalDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllNotaFiscalOutput> execute(ReadAllNotaFiscalCommand aNotaFiscalCommand) {

        return Try(() -> gateway.readAll(aNotaFiscalCommand.aQuery()))
                .toEither().bimap(Notification::create, ReadAllNotaFiscalOutput::from);
    }
}
