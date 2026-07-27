package com.hvs.webstore.back.app.usecase.webstore.metaloja;

import com.hvs.webstore.back.app.command.webstore.metaloja.ReadAllMetaLojaCommand;
import com.hvs.webstore.back.app.output.webstore.metaloja.ReadAllMetaLojaOutput;
import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLojaDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import static io.vavr.API.Try;

public class ReadAllMetaLojaUseCaseImpl extends ReadAllMetaLojaUseCase {

    private final MetaLojaDomainGateway gateway;

    public ReadAllMetaLojaUseCaseImpl(MetaLojaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllMetaLojaOutput> execute(ReadAllMetaLojaCommand aMetaLojaCommand) {

        return Try(() -> gateway.readAll(aMetaLojaCommand.aQuery()))
                .toEither().bimap(Notification::create, ReadAllMetaLojaOutput::from);
    }
}
