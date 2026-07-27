package com.hvs.webstore.back.app.usecase.webstore.video;

import com.hvs.webstore.back.app.command.webstore.video.CreateVideoCommand;
import com.hvs.webstore.back.app.output.webstore.video.CreateVideoOutput;
import com.hvs.webstore.back.domain.entity.webstore.video.Video;
import com.hvs.webstore.back.domain.entity.webstore.video.VideoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateVideoUseCaseImpl extends CreateVideoUseCase {

    private final VideoDomainGateway gateway;

    public CreateVideoUseCaseImpl(VideoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateVideoOutput> execute(CreateVideoCommand aVideoCommand) {

        var notification = Notification.create();
        var video = Video.create(aVideoCommand.aNome(),
                                 aVideoCommand.aCaminho(),
                                 aVideoCommand.aExtensao(),
                                 aVideoCommand.aTamanho(),
                                 aVideoCommand.aDuracao(),
                                 aVideoCommand.aResolucao(),
                                 aVideoCommand.aProdutoId());
        video.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(video);
    }

    @Transactional
    private Either<Notification, CreateVideoOutput> create(Video aVideo) {

        return Try(() -> gateway.create(aVideo)).toEither().bimap(Notification::create, CreateVideoOutput::from);
    }
}
