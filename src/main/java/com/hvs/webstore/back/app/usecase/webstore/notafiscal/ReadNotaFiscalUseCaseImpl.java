package com.hvs.webstore.back.app.usecase.webstore.notafiscal;

import com.hvs.webstore.back.app.command.webstore.notafiscal.ReadNotaFiscalCommand;
import com.hvs.webstore.back.app.output.webstore.notafiscal.ReadNotaFiscalOutput;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscal;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscalDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscalId;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscalUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadNotaFiscalUseCaseImpl extends ReadNotaFiscalUseCase {

    private final NotaFiscalDomainGateway gateway;

    public ReadNotaFiscalUseCaseImpl(NotaFiscalDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadNotaFiscalOutput> execute(ReadNotaFiscalCommand aNotaFiscalCommand) {

        Optional<NotaFiscal> aNotaFiscalDB = aNotaFiscalCommand.aId() != null ?
                gateway.read(NotaFiscalId.from(aNotaFiscalCommand.aId())) : gateway.readByUuid(NotaFiscalUuid.from(aNotaFiscalCommand.aUuid()));

        if (aNotaFiscalDB.isPresent())
            return Try(aNotaFiscalDB::get).toEither().bimap(Notification::create, ReadNotaFiscalOutput::from);

        var aNotaFiscalId = aNotaFiscalCommand.aId() != null ? String.valueOf(aNotaFiscalCommand.aId()) : aNotaFiscalCommand.aUuid();

        return Either.left(Notification.create(new Error("NotaFiscal not found: " + aNotaFiscalId)));
    }
}
