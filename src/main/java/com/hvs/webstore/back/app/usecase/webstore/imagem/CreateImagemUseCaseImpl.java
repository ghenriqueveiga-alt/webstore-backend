package com.hvs.webstore.back.app.usecase.webstore.imagem;

import com.hvs.webstore.back.app.command.webstore.imagem.CreateImagemCommand;
import com.hvs.webstore.back.app.output.webstore.imagem.CreateImagemOutput;
import com.hvs.webstore.back.domain.entity.webstore.imagem.Imagem;
import com.hvs.webstore.back.domain.entity.webstore.imagem.ImagemDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateImagemUseCaseImpl extends CreateImagemUseCase {

    private final ImagemDomainGateway gateway;

    public CreateImagemUseCaseImpl(ImagemDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateImagemOutput> execute(CreateImagemCommand aImagemCommand) {

        var notification = Notification.create();
        var imagem = Imagem.create(aImagemCommand.aNome(),
                                   aImagemCommand.aCaminho(),
                                   aImagemCommand.aExtensao(),
                                   aImagemCommand.aTamanho(),
                                   aImagemCommand.aResolucao(),
                                   null);
        imagem.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(imagem);
    }

    @Transactional
    private Either<Notification, CreateImagemOutput> create(Imagem aImagem) {

        return Try(() -> gateway.create(aImagem))
                .toEither().bimap(Notification::create, CreateImagemOutput::from);
    }
}
