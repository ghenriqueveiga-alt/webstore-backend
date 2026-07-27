package com.hvs.webstore.back.app.usecase.webstore.imagem;

import com.hvs.webstore.back.app.command.webstore.imagem.DeleteImagemCommand;
import com.hvs.webstore.back.app.output.webstore.imagem.DeleteImagemOutput;
import com.hvs.webstore.back.domain.entity.webstore.imagem.Imagem;
import com.hvs.webstore.back.domain.entity.webstore.imagem.ImagemDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.imagem.ImagemId;
import com.hvs.webstore.back.domain.entity.webstore.imagem.ImagemUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteImagemUseCaseImpl extends DeleteImagemUseCase {

    private final ImagemDomainGateway gateway;

    public DeleteImagemUseCaseImpl(ImagemDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteImagemOutput> execute(DeleteImagemCommand aImagemCommand) {

        Optional<Imagem> aImagemDB = aImagemCommand.aId() != null ?
                gateway.read(ImagemId.from(aImagemCommand.aId())) : gateway.readByUuid(ImagemUuid.from(aImagemCommand.aUuid()));

        if (aImagemDB.isEmpty())
            return Either.left(Notification.create(new Error("Imagem not found: " + (aImagemCommand.aId() != null ?
                    aImagemCommand.aId() : aImagemCommand.aUuid()))));

        return delete(aImagemDB.get());
    }

    @Transactional
    private Either<Notification, DeleteImagemOutput> delete(Imagem aImagem) {

        return Try(() -> {
            gateway.delete(aImagem);
            return DeleteImagemOutput.from(aImagem);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
