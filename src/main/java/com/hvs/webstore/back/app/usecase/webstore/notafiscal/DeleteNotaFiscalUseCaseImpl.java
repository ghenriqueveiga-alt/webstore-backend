package com.hvs.webstore.back.app.usecase.webstore.notafiscal;

import com.hvs.webstore.back.app.command.webstore.notafiscal.DeleteNotaFiscalCommand;
import com.hvs.webstore.back.app.output.webstore.notafiscal.DeleteNotaFiscalOutput;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscal;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscalDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscalId;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscalUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteNotaFiscalUseCaseImpl extends DeleteNotaFiscalUseCase {

    private final NotaFiscalDomainGateway gateway;

    public DeleteNotaFiscalUseCaseImpl(NotaFiscalDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteNotaFiscalOutput> execute(DeleteNotaFiscalCommand aNotaFiscalCommand) {

        Optional<NotaFiscal> aNotaFiscalDB = aNotaFiscalCommand.aId() != null ?
                gateway.read(NotaFiscalId.from(aNotaFiscalCommand.aId())) : gateway.readByUuid(NotaFiscalUuid.from(aNotaFiscalCommand.aUuid()));

        if (aNotaFiscalDB.isEmpty())
            return Either.left(Notification.create(new Error("NotaFiscal not found: " + (aNotaFiscalCommand.aId() != null ?
                    aNotaFiscalCommand.aId() : aNotaFiscalCommand.aUuid()))));

        return delete(aNotaFiscalDB.get());
    }

    @Transactional
    private Either<Notification, DeleteNotaFiscalOutput> delete(NotaFiscal aNotaFiscal) {

        return Try(() -> {
            gateway.delete(aNotaFiscal);
            return DeleteNotaFiscalOutput.from(aNotaFiscal);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
