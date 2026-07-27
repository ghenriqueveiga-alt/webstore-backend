package com.hvs.webstore.back.app.usecase.television.corte;

import com.hvs.webstore.back.app.command.television.corte.PatchCorteCommand;
import com.hvs.webstore.back.app.output.television.corte.PatchCorteOutput;
import com.hvs.webstore.back.domain.entity.television.corte.Corte;
import com.hvs.webstore.back.domain.entity.television.corte.CorteDomainGateway;
import com.hvs.webstore.back.domain.entity.television.corte.CorteId;
import com.hvs.webstore.back.domain.entity.television.corte.CorteUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class PatchCorteUseCaseImpl extends PatchCorteUseCase {

    private final CorteDomainGateway gateway;

    public PatchCorteUseCaseImpl(
            CorteDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchCorteOutput> execute(PatchCorteCommand aIn) {

        Optional<Corte> corteDb;

        if (aIn.aId() != null) {

            corteDb = this.gateway.read(CorteId.from(aIn.aId()));
        } else {

            corteDb = this.gateway.readByUuid(CorteUuid.from(aIn.aUuid()));
        }

        if (corteDb.isPresent()) {

            final var notification = Notification.create();
            final var corte = Corte.patch(aIn.aStatusDesc(),
                                          aIn.aArquivoId(),
                                          aIn.aTipoDesc(),
                                          aIn.aDuracao(),
                                          aIn.aEpisodioId(),
                                          corteDb.get());
            corte.validate(notification);

            return notification.hasError() ? Left(notification) : patch(corte);
        } else {

            String responseId;

            if (aIn.aId() != null) {

                responseId = String.valueOf(aIn.aId());
            } else {

                responseId = aIn.aUuid();
            }

            return Either.left(Notification
                    .create(new Error("The Cut with id: " + responseId + " could not be found.")));
        }
    }

    @Transactional
    private Either<Notification, PatchCorteOutput> patch(final Corte aCorte){

        return Try(() -> this.gateway.patch(aCorte))
                .toEither()
                .bimap(Notification::create, PatchCorteOutput::from);
    }
}
