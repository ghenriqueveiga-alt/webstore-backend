package com.hvs.webstore.back.app.usecase.webstore.video;

import com.hvs.webstore.back.app.command.webstore.video.UpdateVideoCommand;
import com.hvs.webstore.back.app.output.webstore.video.UpdateVideoOutput;
import com.hvs.webstore.back.domain.entity.webstore.video.Video;
import com.hvs.webstore.back.domain.entity.webstore.video.VideoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.video.VideoId;
import com.hvs.webstore.back.domain.entity.webstore.video.VideoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateVideoUseCaseImpl extends UpdateVideoUseCase {

    private final VideoDomainGateway gateway;

    public UpdateVideoUseCaseImpl(VideoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateVideoOutput> execute(UpdateVideoCommand aVideoCommand) {

        Optional<Video> aVideoDB = aVideoCommand.aId() != null ?
                gateway.read(VideoId.from(aVideoCommand.aId())) : gateway.readByUuid(VideoUuid.from(aVideoCommand.aUuid()));

        if (aVideoDB.isEmpty())
            return Left(Notification.create(new Error("Video not found: " + (aVideoCommand.aId() != null ?
                    aVideoCommand.aId() : aVideoCommand.aUuid()))));

        var notification = Notification.create();
        var video = Video.update(aVideoDB.get().getId().getValue(),
                                 aVideoDB.get().getUuid().getValue(),
                                 aVideoCommand.aStatusCode(),
                                 aVideoCommand.aNome(),
                                 aVideoCommand.aCaminho(),
                                 aVideoCommand.aExtensao(),
                                 aVideoCommand.aTamanho(),
                                 aVideoCommand.aDuracao(),
                                 aVideoCommand.aResolucao(),
                                 aVideoCommand.aProdutoId());
        video.validate(notification);

        return notification.hasError() ? Left(notification) : update(video);
    }

    @Transactional
    private Either<Notification, UpdateVideoOutput> update(Video aVideo) {

        return Try(() -> gateway.update(aVideo))
                .toEither().bimap(Notification::create, UpdateVideoOutput::from);
    }
}
