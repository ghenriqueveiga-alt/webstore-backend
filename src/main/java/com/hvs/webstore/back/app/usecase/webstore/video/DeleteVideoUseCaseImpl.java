package com.hvs.webstore.back.app.usecase.webstore.video;

import com.hvs.webstore.back.app.command.webstore.video.DeleteVideoCommand;
import com.hvs.webstore.back.app.output.webstore.video.DeleteVideoOutput;
import com.hvs.webstore.back.domain.entity.webstore.video.Video;
import com.hvs.webstore.back.domain.entity.webstore.video.VideoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.video.VideoId;
import com.hvs.webstore.back.domain.entity.webstore.video.VideoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteVideoUseCaseImpl extends DeleteVideoUseCase {

    private final VideoDomainGateway gateway;

    public DeleteVideoUseCaseImpl(VideoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteVideoOutput> execute(DeleteVideoCommand aVideoCommand) {

        Optional<Video> aVideoDB = aVideoCommand.aId() != null ?
                gateway.read(VideoId.from(aVideoCommand.aId())) : gateway.readByUuid(VideoUuid.from(aVideoCommand.aUuid()));

        if (aVideoDB.isEmpty())
            return Either.left(Notification.create(new Error("Video not found: " + (aVideoCommand.aId() != null ?
                    aVideoCommand.aId() : aVideoCommand.aUuid()))));

        return delete(aVideoDB.get());
    }

    @Transactional
    private Either<Notification, DeleteVideoOutput> delete(Video aVideo) {

        return Try(() -> {
            gateway.delete(aVideo);

            return DeleteVideoOutput.from(aVideo);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
