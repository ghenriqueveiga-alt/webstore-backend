package com.hvs.webstore.back.app.usecase.webstore.video;

import com.hvs.webstore.back.app.command.webstore.video.PatchVideoCommand;
import com.hvs.webstore.back.app.output.webstore.video.PatchVideoOutput;
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

public class PatchVideoUseCaseImpl extends PatchVideoUseCase {

    private final VideoDomainGateway gateway;

    public PatchVideoUseCaseImpl(VideoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchVideoOutput> execute(PatchVideoCommand aVideoCommand) {

        Optional<Video> aVideoDB = aVideoCommand.aId() != null ?
                gateway.read(VideoId.from(aVideoCommand.aId())) : gateway.readByUuid(VideoUuid.from(aVideoCommand.aUuid()));

        if (aVideoDB.isEmpty())
            return Left(Notification.create(new Error("Video not found: " + (aVideoCommand.aId() != null ?
                    aVideoCommand.aId() : aVideoCommand.aUuid()))));

        var notification = Notification.create();
        var video = Video.patch(aVideoCommand.aStatusCode(),
                                aVideoCommand.aNome(),
                                aVideoCommand.aCaminho(),
                                aVideoCommand.aExtensao(),
                                aVideoCommand.aTamanho(),
                                aVideoCommand.aDuracao(),
                                aVideoCommand.aResolucao(),
                                aVideoCommand.aProdutoId(),
                                aVideoDB.get());
        video.validate(notification);

        return notification.hasError() ? Left(notification) : patch(video);
    }

    @Transactional
    private Either<Notification, PatchVideoOutput> patch(Video aVideo) {

        return Try(() -> gateway.patch(aVideo))
                .toEither().bimap(Notification::create, PatchVideoOutput::from);
    }
}
