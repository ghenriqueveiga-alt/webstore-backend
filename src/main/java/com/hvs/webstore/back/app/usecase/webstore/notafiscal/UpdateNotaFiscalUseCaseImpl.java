package com.hvs.webstore.back.app.usecase.webstore.notafiscal;

import com.hvs.webstore.back.app.command.webstore.notafiscal.UpdateNotaFiscalCommand;
import com.hvs.webstore.back.app.output.webstore.notafiscal.UpdateNotaFiscalOutput;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscal;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscalDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscalId;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscalUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateNotaFiscalUseCaseImpl extends UpdateNotaFiscalUseCase {

    private final NotaFiscalDomainGateway gateway;

    public UpdateNotaFiscalUseCaseImpl(NotaFiscalDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateNotaFiscalOutput> execute(UpdateNotaFiscalCommand aNotaFiscalCommand) {

        Optional<NotaFiscal> aNotaFiscalDB = aNotaFiscalCommand.aId() != null ?
                gateway.read(NotaFiscalId.from(aNotaFiscalCommand.aId())) : gateway.readByUuid(NotaFiscalUuid.from(aNotaFiscalCommand.aUuid()));

        if (aNotaFiscalDB.isEmpty())
            return Left(Notification.create(new Error("NotaFiscal not found: " + (aNotaFiscalCommand.aId() != null ?
                    aNotaFiscalCommand.aId() : aNotaFiscalCommand.aUuid()))));

        var notification = Notification.create();
        var notaFiscal = NotaFiscal.update(aNotaFiscalDB.get().getId().getValue(),
                                           aNotaFiscalDB.get().getUuid().getValue(),
                                           aNotaFiscalCommand.aStatusCode(),
                                           aNotaFiscalCommand.aPedidoId(),
                                           aNotaFiscalCommand.aChaveAcesso(),
                                           aNotaFiscalCommand.aNumero(),
                                           aNotaFiscalCommand.aSerie(),
                                           aNotaFiscalCommand.aTipoAmbienteCode(),
                                           aNotaFiscalCommand.aXml(),
                                           aNotaFiscalCommand.aDanfeUrl(),
                                           aNotaFiscalCommand.aDataEmissao());
        notaFiscal.validate(notification);

        return notification.hasError() ? Left(notification) : update(notaFiscal);
    }
    @Transactional
    private Either<Notification, UpdateNotaFiscalOutput> update(NotaFiscal aNotaFiscal) {

        return Try(() -> gateway.update(aNotaFiscal))
                .toEither().bimap(Notification::create, UpdateNotaFiscalOutput::from);
    }
}
