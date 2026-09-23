package com.hvs.ws.back.app.usecase.bloco;

import com.hvs.ws.back.app.command.bloco.CreateBlocoCommand;
import com.hvs.ws.back.app.output.bloco.CreateBlocoOutput;
import com.hvs.ws.back.domain.entity.bloco.Bloco;
import com.hvs.ws.back.domain.entity.bloco.BlocoDomainGateway;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateBlocoUseCaseImpl extends CreateBlocoUseCase {

    private final BlocoDomainGateway gateway;

    public CreateBlocoUseCaseImpl(
            BlocoDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateBlocoOutput> execute(CreateBlocoCommand aIn) {

        final var notification = Notification.create();
        final var bloco = Bloco.create(aIn.aProgramaId(),
                                       aIn.aHorario(),
                                       aIn.aGradeId(),
                                       aIn.aDiaSemanaCode(),
                                       aIn.aFaixaHorarioCode(),
                                       aIn.aTipoBlocoCode());
        bloco.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(bloco);
    }

    @Transactional
    private Either<Notification, CreateBlocoOutput> create(final Bloco aBloco){

        return Try(() -> this.gateway.create(aBloco))
                .toEither()
                .bimap(Notification::create, CreateBlocoOutput::from);
    }
}
