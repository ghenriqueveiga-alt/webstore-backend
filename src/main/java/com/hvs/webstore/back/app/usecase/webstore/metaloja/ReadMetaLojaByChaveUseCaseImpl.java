package com.hvs.webstore.back.app.usecase.webstore.metaloja;

import com.hvs.webstore.back.app.command.webstore.metaloja.ReadMetaLojaByChaveCommand;
import com.hvs.webstore.back.app.output.webstore.metaloja.ReadMetaLojaOutput;
import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLojaDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadMetaLojaByChaveUseCaseImpl extends ReadMetaLojaByChaveUseCase {

    private final MetaLojaDomainGateway gateway;

    public ReadMetaLojaByChaveUseCaseImpl(MetaLojaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadMetaLojaOutput> execute(ReadMetaLojaByChaveCommand aMetaLojaCommand) {

        var aMetaLojaDB = gateway.readByChave(aMetaLojaCommand.aChave());

        if (aMetaLojaDB.isPresent())
            return Try(aMetaLojaDB::get)
                    .toEither().bimap(Notification::create, ReadMetaLojaOutput::from);

        return Either.left(Notification.create(new Error("MetaLoja not found by chave: " + aMetaLojaCommand.aChave())));
    }
}
