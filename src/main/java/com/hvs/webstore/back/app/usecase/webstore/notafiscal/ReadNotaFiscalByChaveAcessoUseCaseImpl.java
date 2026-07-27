package com.hvs.webstore.back.app.usecase.webstore.notafiscal;

import com.hvs.webstore.back.app.command.webstore.notafiscal.ReadNotaFiscalByChaveAcessoCommand;
import com.hvs.webstore.back.app.output.webstore.notafiscal.ReadNotaFiscalOutput;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscal;
import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscalDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadNotaFiscalByChaveAcessoUseCaseImpl extends ReadNotaFiscalByChaveAcessoUseCase {

    private final NotaFiscalDomainGateway gateway;

    public ReadNotaFiscalByChaveAcessoUseCaseImpl(NotaFiscalDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadNotaFiscalOutput> execute(ReadNotaFiscalByChaveAcessoCommand aNotaFiscalCommand) {

        Optional<NotaFiscal> aNotaFiscalDB = gateway.readByChaveAcesso(aNotaFiscalCommand.aChaveAcesso());

        if (aNotaFiscalDB.isPresent())
            return Try(aNotaFiscalDB::get)
                    .toEither().bimap(Notification::create, ReadNotaFiscalOutput::from);

        return Either.left(Notification.create(new Error("NotaFiscal not found for chaveAcesso: " + aNotaFiscalCommand.aChaveAcesso())));
    }
}
