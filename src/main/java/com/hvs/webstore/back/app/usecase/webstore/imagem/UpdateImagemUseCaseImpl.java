package com.hvs.webstore.back.app.usecase.webstore.imagem;

import com.hvs.webstore.back.app.command.webstore.imagem.UpdateImagemCommand;
import com.hvs.webstore.back.app.output.webstore.imagem.UpdateImagemOutput;
import com.hvs.webstore.back.domain.entity.webstore.imagem.Imagem;
import com.hvs.webstore.back.domain.entity.webstore.imagem.ImagemDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.imagem.ImagemId;
import com.hvs.webstore.back.domain.entity.webstore.imagem.ImagemUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateImagemUseCaseImpl extends UpdateImagemUseCase {

    private final ImagemDomainGateway gateway;

    public UpdateImagemUseCaseImpl(ImagemDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateImagemOutput> execute(UpdateImagemCommand aImagemCommand) {

        Optional<Imagem> aImagemDB = aImagemCommand.aId() != null ?
                gateway.read(ImagemId.from(aImagemCommand.aId())) : gateway.readByUuid(ImagemUuid.from(aImagemCommand.aUuid()));

        if (aImagemDB.isEmpty())
            return Left(Notification.create(new Error("Imagem not found: " + (aImagemCommand.aId() != null ?
                    aImagemCommand.aId() : aImagemCommand.aUuid()))));

        var notification = Notification.create();
        var imagem = Imagem.update(aImagemDB.get().getId().getValue(),
                                   aImagemDB.get().getUuid().getValue(),
                                   aImagemCommand.aStatusCode(),
                                   aImagemCommand.aNome(),
                                   aImagemCommand.aCaminho(),
                                   aImagemCommand.aExtensao(),
                                   aImagemCommand.aTamanho(),
                                   aImagemCommand.aResolucao(),
                                   null);
        imagem.validate(notification);

        return notification.hasError() ? Left(notification) : update(imagem);
    }

    @Transactional
    private Either<Notification, UpdateImagemOutput> update(Imagem aImagem) {

        return Try(() -> gateway.update(aImagem))
                .toEither().bimap(Notification::create, UpdateImagemOutput::from);
    }
}
