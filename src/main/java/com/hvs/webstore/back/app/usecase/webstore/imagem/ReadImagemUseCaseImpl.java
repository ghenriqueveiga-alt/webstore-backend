package com.hvs.webstore.back.app.usecase.webstore.imagem;

import com.hvs.webstore.back.app.command.webstore.imagem.ReadImagemCommand;
import com.hvs.webstore.back.app.output.webstore.imagem.ReadImagemOutput;
import com.hvs.webstore.back.domain.entity.webstore.imagem.Imagem;
import com.hvs.webstore.back.domain.entity.webstore.imagem.ImagemDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.imagem.ImagemId;
import com.hvs.webstore.back.domain.entity.webstore.imagem.ImagemUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadImagemUseCaseImpl extends ReadImagemUseCase {

    private final ImagemDomainGateway gateway;

    public ReadImagemUseCaseImpl(ImagemDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadImagemOutput> execute(ReadImagemCommand aImagemCommand) {

        Optional<Imagem> aImagemDB = aImagemCommand.aId() != null ?
                gateway.read(ImagemId.from(aImagemCommand.aId())) : gateway.readByUuid(ImagemUuid.from(aImagemCommand.aUuid()));

        if (aImagemDB.isPresent())
            return Try(aImagemDB::get).toEither().bimap(Notification::create, ReadImagemOutput::from);

        var aImagemId = aImagemCommand.aId() != null ? String.valueOf(aImagemCommand.aId()) : aImagemCommand.aUuid();

        return Either.left(Notification.create(new Error("Imagem not found: " + aImagemId)));
    }
}
