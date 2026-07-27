package com.hvs.webstore.back.app.usecase.webstore.video;

import com.hvs.webstore.back.app.command.webstore.video.ReadAllVideoCommand;
import com.hvs.webstore.back.app.output.webstore.video.ReadAllVideoOutput;
import com.hvs.webstore.back.domain.entity.webstore.video.VideoDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

public class ReadAllVideoUseCaseImpl extends ReadAllVideoUseCase {

    private final VideoDomainGateway gateway;

    public ReadAllVideoUseCaseImpl(VideoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllVideoOutput> execute(ReadAllVideoCommand aVideoCommand) {

        var videoPagination = gateway.readAll(aVideoCommand.aSearchQuery());
        var lista = videoPagination.aContent()
                .stream().filter(video -> video.getStatusCode().getDesc().equals("Active")).toList();

        if (!lista.isEmpty())
            return Try(() -> gateway.readAll(aVideoCommand.aSearchQuery())).toEither().bimap(Notification::create, ReadAllVideoOutput::from);

        return Either.left(Notification.create(new Error("No Video was found.")));
    }
}
