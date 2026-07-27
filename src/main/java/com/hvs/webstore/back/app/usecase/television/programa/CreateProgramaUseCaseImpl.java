package com.hvs.webstore.back.app.usecase.television.programa;

import com.hvs.webstore.back.app.command.television.programa.CreateProgramaCommand;
import com.hvs.webstore.back.app.output.television.programa.CreateProgramaOutput;
import com.hvs.webstore.back.domain.entity.television.programa.Programa;
import com.hvs.webstore.back.domain.entity.television.programa.ProgramaDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateProgramaUseCaseImpl extends CreateProgramaUseCase {

    private final ProgramaDomainGateway gateway;

    public CreateProgramaUseCaseImpl(ProgramaDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateProgramaOutput> execute(CreateProgramaCommand aProgramaCommand) {

        final var notification = Notification.create();
        final var programa = Programa.create(aProgramaCommand.aNome(),
                                             aProgramaCommand.aEmProducao(),
                                             aProgramaCommand.aTipoDesc(),
                                             aProgramaCommand.aTemporadas(),
                                             aProgramaCommand.aEpisodioIds(),
                                             aProgramaCommand.aLancamento(),
                                             aProgramaCommand.aEncerramento(),
                                             aProgramaCommand.aBlocoIds());
        programa.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(programa);
    }

    @Transactional
    private Either<Notification, CreateProgramaOutput> create(final Programa aPrograma){

        return Try(() -> this.gateway.create(aPrograma))
                .toEither().bimap(Notification::create, CreateProgramaOutput::from);
    }
}
