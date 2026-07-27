package com.hvs.webstore.back.app.usecase.webstore.metaloja;

import com.hvs.webstore.back.app.command.webstore.metaloja.DeleteMetaLojaCommand;
import com.hvs.webstore.back.app.output.webstore.metaloja.DeleteMetaLojaOutput;
import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLoja;
import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLojaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLojaId;
import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLojaUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteMetaLojaUseCaseImpl extends DeleteMetaLojaUseCase {

    private final MetaLojaDomainGateway gateway;

    public DeleteMetaLojaUseCaseImpl(MetaLojaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteMetaLojaOutput> execute(DeleteMetaLojaCommand aMetaLojaCommand) {

        Optional<MetaLoja> aMetaLojaDB = aMetaLojaCommand.aId() != null ?
                gateway.read(MetaLojaId.from(aMetaLojaCommand.aId())) : gateway.readByUuid(MetaLojaUuid.from(aMetaLojaCommand.aUuid()));

        if (aMetaLojaDB.isEmpty())
            return Either.left(Notification.create(new Error("MetaLoja not found: " + (aMetaLojaCommand.aId() != null ?
                    aMetaLojaCommand.aId() : aMetaLojaCommand.aUuid()))));

        return delete(aMetaLojaDB.get());
    }

    @Transactional
    private Either<Notification, DeleteMetaLojaOutput> delete(MetaLoja aMetaLoja) {

        return Try(() -> {
            gateway.delete(aMetaLoja);
            return DeleteMetaLojaOutput.from(aMetaLoja);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
