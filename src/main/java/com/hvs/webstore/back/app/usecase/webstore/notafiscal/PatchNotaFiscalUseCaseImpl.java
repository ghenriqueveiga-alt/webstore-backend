package com.hvs.webstore.back.app.usecase.webstore.notafiscal;

import com.hvs.webstore.back.app.command.webstore.notafiscal.PatchNotaFiscalCommand;
import com.hvs.webstore.back.app.output.webstore.notafiscal.PatchNotaFiscalOutput;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscal;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscalDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscalUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class PatchNotaFiscalUseCaseImpl extends PatchNotaFiscalUseCase {

    private final NotaFiscalDomainGateway gateway;

    public PatchNotaFiscalUseCaseImpl(NotaFiscalDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchNotaFiscalOutput> execute(PatchNotaFiscalCommand aNotaFiscalCommand) {

        Optional<NotaFiscal> aNotaFiscalDB = gateway.readByUuid(NotaFiscalUuid.from(aNotaFiscalCommand.aUuid()));

        if (aNotaFiscalDB.isEmpty())
            return Left(Notification.create(new Error("NotaFiscal not found: " + aNotaFiscalCommand.aUuid())));

        var notification = Notification.create();
        var notaFiscal = NotaFiscal.patch(aNotaFiscalCommand.aStatusCode(),
                                          aNotaFiscalCommand.aPedidoId(),
                                          aNotaFiscalCommand.aChaveAcesso(),
                                          aNotaFiscalCommand.aNumero(),
                                          aNotaFiscalCommand.aSerie(),
                                          aNotaFiscalCommand.aTipoAmbienteCode(),
                                          aNotaFiscalCommand.aXml(),
                                          aNotaFiscalCommand.aDanfeUrl(),
                                          aNotaFiscalDB.get());
        notaFiscal.validate(notification);

        return notification.hasError() ? Left(notification) : patch(notaFiscal);
    }
    @Transactional
    private Either<Notification, PatchNotaFiscalOutput> patch(NotaFiscal aNotaFiscal) {

        return Try(() -> gateway.patch(aNotaFiscal))
                .toEither().bimap(Notification::create, PatchNotaFiscalOutput::from);
    }
}
