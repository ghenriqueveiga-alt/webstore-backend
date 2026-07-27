package com.hvs.webstore.back.app.usecase.webstore.imagem;

import com.hvs.webstore.back.app.command.webstore.imagem.ReadAllImagemCommand;
import com.hvs.webstore.back.app.output.webstore.imagem.ReadAllImagemOutput;
import com.hvs.webstore.back.domain.entity.webstore.imagem.Imagem;
import com.hvs.webstore.back.domain.entity.webstore.imagem.ImagemDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

public class ReadAllImagemUseCaseImpl extends ReadAllImagemUseCase {

    private final ImagemDomainGateway gateway;

    public ReadAllImagemUseCaseImpl(ImagemDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllImagemOutput> execute(ReadAllImagemCommand aImagemCommand) {

        Pagination<Imagem> imagemPagination = gateway.readAll(aImagemCommand.aSearchQuery());
        List<Imagem> lista = imagemPagination.aContent()
                .stream().filter(corte -> corte.getStatusCode().getDesc().equals("Active")).toList();

        if (!lista.isEmpty())
            return Try(() -> gateway.readAll(aImagemCommand.aSearchQuery())).toEither().bimap(
                    Notification::create, ReadAllImagemOutput::from);

        return Either.left(Notification.create(new Error("No Image was found.")));
    }
}
