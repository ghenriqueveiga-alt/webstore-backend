package com.hvs.webstore.back.app.usecase.webstore.metaloja;

import com.hvs.webstore.back.app.command.webstore.metaloja.UpdateMetaLojaCommand;
import com.hvs.webstore.back.app.output.webstore.metaloja.UpdateMetaLojaOutput;
import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLoja;
import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLojaDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLojaId;
import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLojaUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateMetaLojaUseCaseImpl extends UpdateMetaLojaUseCase {

    private final MetaLojaDomainGateway gateway;

    public UpdateMetaLojaUseCaseImpl(MetaLojaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateMetaLojaOutput> execute(UpdateMetaLojaCommand aMetaLojaCommand) {

        Optional<MetaLoja> aMetaLojaDB = aMetaLojaCommand.aId() != null ?
                gateway.read(MetaLojaId.from(aMetaLojaCommand.aId())) : gateway.readByUuid(MetaLojaUuid.from(aMetaLojaCommand.aUuid()));

        if (aMetaLojaDB.isEmpty())
            return Left(Notification.create(new Error("MetaLoja not found: " + (aMetaLojaCommand.aId() != null ?
                    aMetaLojaCommand.aId() : aMetaLojaCommand.aUuid()))));

        var notification = Notification.create();
        var metaLoja = MetaLoja.update(aMetaLojaDB.get().getId().getValue(),
                                       aMetaLojaDB.get().getUuid().getValue(),
                                       aMetaLojaCommand.aStatusCode(),
                                       aMetaLojaCommand.aChave(),
                                       aMetaLojaCommand.aValor(),
                                       aMetaLojaCommand.aDescricao(),
                                       aMetaLojaCommand.aTipoCode());
        metaLoja.validate(notification);

        return notification.hasError() ? Left(notification) : update(metaLoja);
    }
    @Transactional
    private Either<Notification, UpdateMetaLojaOutput> update(MetaLoja aMetaLoja) {

        return Try(() -> gateway.update(aMetaLoja))
                .toEither().bimap(Notification::create, UpdateMetaLojaOutput::from);
    }
}
