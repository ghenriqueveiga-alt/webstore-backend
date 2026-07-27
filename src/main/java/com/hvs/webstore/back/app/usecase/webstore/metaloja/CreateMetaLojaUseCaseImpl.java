package com.hvs.webstore.back.app.usecase.webstore.metaloja;

import com.hvs.webstore.back.app.command.webstore.metaloja.CreateMetaLojaCommand;
import com.hvs.webstore.back.app.output.webstore.metaloja.CreateMetaLojaOutput;
import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLoja;
import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLojaDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateMetaLojaUseCaseImpl extends CreateMetaLojaUseCase {

    private final MetaLojaDomainGateway gateway;

    public CreateMetaLojaUseCaseImpl(MetaLojaDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateMetaLojaOutput> execute(CreateMetaLojaCommand aMetaLojaCommand) {

        var notification = Notification.create();
        var metaLoja = MetaLoja.create(aMetaLojaCommand.aChave(),
                                       aMetaLojaCommand.aValor(),
                                       aMetaLojaCommand.aDescricao(),
                                       aMetaLojaCommand.aTipoCode());
        metaLoja.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(metaLoja);
    }

    @Transactional
    private Either<Notification, CreateMetaLojaOutput> create(MetaLoja aMetaLoja) {

        return Try(() -> gateway.create(aMetaLoja))
                .toEither().bimap(Notification::create, CreateMetaLojaOutput::from);
    }
}
