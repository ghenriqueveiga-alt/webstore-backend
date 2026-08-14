package com.hvs.webstore.back.app.usecase.television.genero;

import com.hvs.webstore.back.app.command.television.genero.UpdateGeneroCommand;
import com.hvs.webstore.back.app.output.television.genero.UpdateGeneroOutput;
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

public class UpdateGeneroUseCaseImpl extends UpdateGeneroUseCase {

    private final GeneroDomainGateway gateway;

    public UpdateGeneroUseCaseImpl(GeneroDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateGeneroOutput> execute(UpdateGeneroCommand aGeneroCommand) {

        Optional<Genero> generoDb;

        if (aGeneroCommand.aId() != null) {
            generoDb = this.gateway.read(GeneroId.from(aGeneroCommand.aId()));
        } else {
            generoDb = this.gateway.readByUuid(GeneroUuid.from(aGeneroCommand.aUuid()));
        }

        if (generoDb.isPresent()) {
            final var notification = Notification.create();
            final var genero = Genero.update(generoDb.get().getId().getValue(),
                                             generoDb.get().getUuid().getValue(),
                                             aGeneroCommand.aStatusDesc(),
                                             aGeneroCommand.aNome(),
                                             aGeneroCommand.aDescricao());
            genero.validate(notification);
            return notification.hasError() ? Left(notification) : update(genero);
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
    private Either<Notification, UpdateGeneroOutput> update(final Genero aGenero) {

        return Try(() -> this.gateway.update(aGenero))
                .toEither().bimap(Notification::create, UpdateGeneroOutput::from);
    }
}
