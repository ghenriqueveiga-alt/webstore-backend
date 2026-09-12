package com.hvs.webstore.back.app.usecase.television.programa;

import com.hvs.webstore.back.app.command.television.programa.PatchProgramaCommand;
import com.hvs.webstore.back.app.output.television.programa.PatchProgramaOutput;
import com.hvs.webstore.back.domain.entity.television.programa.Programa;
import com.hvs.webstore.back.domain.entity.television.programa.ProgramaDomainGateway;
import com.hvs.webstore.back.domain.entity.television.programa.ProgramaId;
import com.hvs.webstore.back.domain.entity.television.programa.ProgramaUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class PatchProgramaUseCaseImpl extends PatchProgramaUseCase {

    private final ProgramaDomainGateway gateway;

    public PatchProgramaUseCaseImpl(ProgramaDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchProgramaOutput> execute(PatchProgramaCommand aProgramaCommand) {

        Optional<Programa> programaDb;

        if (aProgramaCommand.aId() != null) {
            programaDb = this.gateway.read(ProgramaId.from(aProgramaCommand.aId()));
        } else {
            programaDb = this.gateway.readByUuid(ProgramaUuid.from(aProgramaCommand.aUuid()));
        }

        if (programaDb.isPresent()) {
            final var notification = Notification.create();
            final var programa = Programa.patch(aProgramaCommand.aStatusCode(),
                                                aProgramaCommand.aNome(),
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
                                                aProgramaCommand.aGeneroIds(),
                                                programaDb.get());
            programa.validate(notification);
            return notification.hasError() ? Left(notification) : patch(programa);
        } else {
            String responseId;

            if (aProgramaCommand.aId() != null) {
                responseId = String.valueOf(aProgramaCommand.aId());
            } else {
                responseId = aProgramaCommand.aUuid();
            }

            return Either.left(Notification.create(new Error("The Program with id: " + responseId + " could not be found.")));
        }
    }

    @Transactional
    private Either<Notification, PatchProgramaOutput> patch(final Programa aPrograma) {

        return Try(() -> this.gateway.patch(aPrograma))
                .toEither().bimap(Notification::create, PatchProgramaOutput::from);
    }
}
