package com.hvs.webstore.back.app.usecase.webstore.metaloja;

import com.hvs.webstore.back.app.command.webstore.metaloja.ReadMetaLojaCommand;
import com.hvs.webstore.back.app.output.webstore.metaloja.ReadMetaLojaOutput;
import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLoja;
import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLojaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLojaId;
import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLojaUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadMetaLojaUseCaseImpl extends ReadMetaLojaUseCase {

    private final MetaLojaDomainGateway gateway;

    public ReadMetaLojaUseCaseImpl(MetaLojaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadMetaLojaOutput> execute(ReadMetaLojaCommand aMetaLojaCommand) {

        Optional<MetaLoja> aMetaLojaDB = aMetaLojaCommand.aId() != null ?
                gateway.read(MetaLojaId.from(aMetaLojaCommand.aId())) : gateway.readByUuid(MetaLojaUuid.from(aMetaLojaCommand.aUuid()));

        if (aMetaLojaDB.isPresent())
            return Try(aMetaLojaDB::get).toEither().bimap(Notification::create, ReadMetaLojaOutput::from);

        var aMetaLojaId = aMetaLojaCommand.aId() != null ? String.valueOf(aMetaLojaCommand.aId()) : aMetaLojaCommand.aUuid();

        return Either.left(Notification.create(new Error("MetaLoja not found: " + aMetaLojaId)));
    }
}
