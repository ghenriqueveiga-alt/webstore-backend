package com.hvs.webstore.back.app.usecase.television.genero;

import com.hvs.webstore.back.app.command.television.genero.PatchGeneroCommand;
import com.hvs.webstore.back.app.output.television.genero.PatchGeneroOutput;
import com.hvs.webstore.back.domain.entity.television.genero.Genero;
import com.hvs.webstore.back.domain.entity.television.genero.GeneroDomainGateway;
import com.hvs.webstore.back.domain.entity.television.genero.GeneroId;
import com.hvs.webstore.back.domain.entity.television.genero.GeneroUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class PatchGeneroUseCaseImpl extends PatchGeneroUseCase {

    private final GeneroDomainGateway gateway;

    public PatchGeneroUseCaseImpl(GeneroDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchGeneroOutput> execute(PatchGeneroCommand aGeneroCommand) {

        Optional<Genero> generoDb;

        if (aGeneroCommand.aId() != null) {
            generoDb = this.gateway.read(GeneroId.from(aGeneroCommand.aId()));
        } else {
            generoDb = this.gateway.readByUuid(GeneroUuid.from(aGeneroCommand.aUuid()));
        }

        if (generoDb.isPresent()) {
            final var notification = Notification.create();
            final var genero = Genero.patch(aGeneroCommand.aStatusDesc(),
                                            aGeneroCommand.aNome(),
                                            aGeneroCommand.aDescricao(),
                                            generoDb.get());
            genero.validate(notification);
            return notification.hasError() ? Left(notification) : patch(genero);
        } else {
            String responseId;

            if (aGeneroCommand.aId() != null) {
                responseId = String.valueOf(aGeneroCommand.aId());
            } else {
                responseId = aGeneroCommand.aUuid();
            }

            return Either.left(Notification.create(new Error("The Genero with id: " + responseId + " could not be found.")));
        }
    }

    @Transactional
    private Either<Notification, PatchGeneroOutput> patch(final Genero aGenero) {

        return Try(() -> this.gateway.patch(aGenero))
                .toEither().bimap(Notification::create, PatchGeneroOutput::from);
    }
}
