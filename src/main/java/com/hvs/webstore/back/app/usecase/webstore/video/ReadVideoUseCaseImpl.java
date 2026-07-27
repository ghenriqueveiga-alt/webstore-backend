package com.hvs.webstore.back.app.usecase.webstore.video;

import com.hvs.webstore.back.app.command.webstore.video.ReadVideoCommand;
import com.hvs.webstore.back.app.output.webstore.video.ReadVideoOutput;
import com.hvs.webstore.back.domain.entity.webstore.video.Video;
import com.hvs.webstore.back.domain.entity.webstore.video.VideoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.video.VideoId;
import com.hvs.webstore.back.domain.entity.webstore.video.VideoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadVideoUseCaseImpl extends ReadVideoUseCase {

    private final VideoDomainGateway gateway;

    public ReadVideoUseCaseImpl(VideoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadVideoOutput> execute(ReadVideoCommand aVideoCommand) {

        Optional<Video> aVideoDB = aVideoCommand.aId() != null ?
                gateway.read(VideoId.from(aVideoCommand.aId())) : gateway.readByUuid(VideoUuid.from(aVideoCommand.aUuid()));

        if (aVideoDB.isPresent())
            return Try(aVideoDB::get).toEither().bimap(Notification::create, ReadVideoOutput::from);

        var aVideoId = aVideoCommand.aId() != null ? String.valueOf(aVideoCommand.aId()) : aVideoCommand.aUuid();

        return Either.left(Notification.create(new Error("Video not found: " + aVideoId)));
    }
}
