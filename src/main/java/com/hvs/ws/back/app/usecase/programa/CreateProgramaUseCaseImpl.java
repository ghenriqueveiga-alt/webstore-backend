package com.hvs.ws.back.app.usecase.programa;

import com.hvs.ws.back.app.command.programa.CreateProgramaCommand;
import com.hvs.ws.back.app.output.programa.CreateProgramaOutput;
import com.hvs.ws.back.domain.entity.programa.Programa;
import com.hvs.ws.back.domain.entity.programa.ProgramaDomainGateway;
import com.hvs.ws.back.domain.validation.notification.Notification;
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
                                             aProgramaCommand.aTipoCode(),
                                             aProgramaCommand.aTemporadas(),
                                             aProgramaCommand.aPartes(),
                                             aProgramaCommand.aEpisodioIds(),
                                             aProgramaCommand.aLancamento(),
                                             aProgramaCommand.aEncerramento(),
                                             aProgramaCommand.aBlocoIds(),
                                             aProgramaCommand.aSinopse(),
                                             aProgramaCommand.aClassificacaoEtariaCode(),
                                             aProgramaCommand.aEstudio(),
                                             aProgramaCommand.aCapaUrl(),
                                             aProgramaCommand.aRedeOriginal(),
                                             aProgramaCommand.aTipoExibicaoCode(),
                                             aProgramaCommand.aTituloAlternativo(),
                                             aProgramaCommand.aAudioIdiomas(),
                                             aProgramaCommand.aLegendasDisponiveis(),
                                             aProgramaCommand.aSiteOficial(),
                                             aProgramaCommand.aGeneroIds());
        programa.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(programa);
    }

    @Transactional
    private Either<Notification, CreateProgramaOutput> create(final Programa aPrograma){

        return Try(() -> this.gateway.create(aPrograma))
                .toEither().bimap(Notification::create, CreateProgramaOutput::from);
    }
}
