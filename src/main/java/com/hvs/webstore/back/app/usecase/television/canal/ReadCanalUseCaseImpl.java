package com.hvs.webstore.back.app.usecase.television.canal;

import com.hvs.webstore.back.app.command.television.canal.ReadCanalCommand;
import com.hvs.webstore.back.app.output.television.canal.ReadCanalOutput;
import com.hvs.webstore.back.domain.entity.television.canal.Canal;
import com.hvs.webstore.back.domain.entity.television.canal.CanalDomainGateway;
import com.hvs.webstore.back.domain.entity.television.canal.CanalId;
import com.hvs.webstore.back.domain.entity.television.canal.CanalUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadCanalUseCaseImpl extends ReadCanalUseCase {

    private final CanalDomainGateway gateway;

    public ReadCanalUseCaseImpl(CanalDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadCanalOutput> execute(ReadCanalCommand aCanalCommand) {

        Optional<Canal> canalDb;

        if (aCanalCommand.aId() != null) {
            canalDb = this.gateway.read(CanalId.from(aCanalCommand.aId()));
        } else {
            canalDb = this.gateway.readByUuid(CanalUuid.from(aCanalCommand.aUuid()));
        }

        if (canalDb.isPresent()) {
            return Try(canalDb::get).toEither().bimap(Notification::create, ReadCanalOutput::from);
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
}
