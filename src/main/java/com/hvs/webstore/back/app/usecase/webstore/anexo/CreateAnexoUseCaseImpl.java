package com.hvs.webstore.back.app.usecase.webstore.anexo;

import com.hvs.webstore.back.app.command.webstore.anexo.CreateAnexoCommand;
import com.hvs.webstore.back.app.output.webstore.anexo.CreateAnexoOutput;
import com.hvs.webstore.back.domain.entity.webstore.anexo.Anexo;
import com.hvs.webstore.back.domain.entity.webstore.anexo.AnexoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateAnexoUseCaseImpl extends CreateAnexoUseCase {

    private final AnexoDomainGateway gateway;

    public CreateAnexoUseCaseImpl(AnexoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateAnexoOutput> execute(CreateAnexoCommand aAnexoCommand) {

        var notification = Notification.create();
        var anexo = Anexo.create(aAnexoCommand.aEntidadeNome(),
                                 aAnexoCommand.aEntidadeId(),
                                 aAnexoCommand.aNome(),
                                 aAnexoCommand.aTipo(),
                                 aAnexoCommand.aTamanho(),
                                 aAnexoCommand.aUrl());
        anexo.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(anexo);
    }

    @Transactional
    private Either<Notification, CreateAnexoOutput> create(Anexo aAnexo) {

        return Try(() -> gateway.create(aAnexo)).toEither().bimap(Notification::create, CreateAnexoOutput::from);
    }
}
