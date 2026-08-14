package com.hvs.webstore.back.app.usecase.television.canal;

import com.hvs.webstore.back.app.command.television.canal.UpdateCanalCommand;
import com.hvs.webstore.back.app.output.television.canal.UpdateCanalOutput;
import com.hvs.webstore.back.domain.entity.television.canal.Canal;
import com.hvs.webstore.back.domain.entity.television.canal.CanalDomainGateway;
import com.hvs.webstore.back.domain.entity.television.canal.CanalId;
import com.hvs.webstore.back.domain.entity.television.canal.CanalUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateCanalUseCaseImpl extends UpdateCanalUseCase {

    private final CanalDomainGateway gateway;

    public UpdateCanalUseCaseImpl(CanalDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateCanalOutput> execute(UpdateCanalCommand aCanalCommand) {

        Optional<Canal> canalDb;

        if (aCanalCommand.aId() != null) {
            canalDb = this.gateway.read(CanalId.from(aCanalCommand.aId()));
        } else {
            canalDb = this.gateway.readByUuid(CanalUuid.from(aCanalCommand.aUuid()));
        }

        if (canalDb.isPresent()) {
            final var notification = Notification.create();
            final var canal = Canal.update(canalDb.get().getId().getValue(),
                                           canalDb.get().getUuid().getValue(),
                                           aCanalCommand.aStatusDesc(),
                                           aCanalCommand.aNome(),
                                           aCanalCommand.aDescricao(),
                                           aCanalCommand.aLogotipoUrl(),
                                           aCanalCommand.aSite());
            canal.validate(notification);
            return notification.hasError() ? Left(notification) : update(canal);
        } else {
            String responseId;

            if (aCanalCommand.aId() != null) {
                responseId = String.valueOf(aCanalCommand.aId());
            } else {
                responseId = aCanalCommand.aUuid();
            }

            return Either.left(Notification.create(new Error("The Canal with id: " + responseId + " could not be found.")));
        }
    }

    @Transactional
    private Either<Notification, UpdateCanalOutput> update(final Canal aCanal){

        return Try(() -> this.gateway.update(aCanal))
                .toEither().bimap(Notification::create, UpdateCanalOutput::from);
    }
}
