package com.hvs.webstore.back.app.usecase.television.canal;

import com.hvs.webstore.back.app.command.television.canal.PatchCanalCommand;
import com.hvs.webstore.back.app.output.television.canal.PatchCanalOutput;
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

public class PatchCanalUseCaseImpl extends PatchCanalUseCase {

    private final CanalDomainGateway gateway;

    public PatchCanalUseCaseImpl(CanalDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchCanalOutput> execute(PatchCanalCommand aCanalCommand) {

        Optional<Canal> canalDb;

        if (aCanalCommand.aId() != null) {
            canalDb = this.gateway.read(CanalId.from(aCanalCommand.aId()));
        } else {
            canalDb = this.gateway.readByUuid(CanalUuid.from(aCanalCommand.aUuid()));
        }

        if (canalDb.isPresent()) {
            final var notification = Notification.create();
            final var canal = Canal.patch(aCanalCommand.aStatusDesc(),
                                          aCanalCommand.aNome(),
                                          aCanalCommand.aDescricao(),
                                          aCanalCommand.aLogotipoUrl(),
                                          aCanalCommand.aSite(),
                                          canalDb.get());
            canal.validate(notification);
            return notification.hasError() ? Left(notification) : patch(canal);
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
    private Either<Notification, PatchCanalOutput> patch(final Canal aCanal) {

        return Try(() -> this.gateway.patch(aCanal))
                .toEither().bimap(Notification::create, PatchCanalOutput::from);
    }
}
