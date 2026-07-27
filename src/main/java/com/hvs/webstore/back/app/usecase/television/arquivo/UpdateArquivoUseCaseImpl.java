package com.hvs.webstore.back.app.usecase.television.arquivo;

import com.hvs.webstore.back.app.command.television.arquivo.UpdateArquivoCommand;
import com.hvs.webstore.back.app.output.television.arquivo.UpdateArquivoOutput;
import com.hvs.webstore.back.domain.entity.television.arquivo.Arquivo;
import com.hvs.webstore.back.domain.entity.television.arquivo.ArquivoDomainGateway;
import com.hvs.webstore.back.domain.entity.television.arquivo.ArquivoId;
import com.hvs.webstore.back.domain.entity.television.arquivo.ArquivoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateArquivoUseCaseImpl extends UpdateArquivoUseCase {

    private final ArquivoDomainGateway gateway;

    public UpdateArquivoUseCaseImpl(
            ArquivoDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateArquivoOutput> execute(UpdateArquivoCommand aIn) {

        Optional<Arquivo> arquivoDb;

        if (aIn.aId() != null) {

            arquivoDb = this.gateway.read(ArquivoId.from(aIn.aId()));
        } else {

            arquivoDb = this.gateway.readByUuid(ArquivoUuid.from(aIn.aUuid()));
        }

        if (arquivoDb.isPresent()) {

            final var notification = Notification.create();
            final var arquivo = Arquivo.update(arquivoDb.get().getId().getValue(),
                                               arquivoDb.get().getUuid().getValue(),
                                               aIn.aStatusDesc(),
                                               aIn.aNome(),
                                               aIn.aTipo(),
                                               aIn.aTamanho(),
                                               aIn.aCaminho(),
                                               aIn.aDuracao());
            arquivo.validate(notification);

            return notification.hasError() ? Left(notification) : update(arquivo);
        } else {

            String responseId;

            if (aIn.aId() != null) {

                responseId = String.valueOf(aIn.aId());
            } else {

                responseId = aIn.aUuid();
            }

            return Either.left(Notification
                    .create(new Error("The Archive with id: " + responseId + " could not be found.")));
        }
    }

    @Transactional
    private Either<Notification, UpdateArquivoOutput> update(final Arquivo aArquivo){

        return Try(() -> this.gateway.update(aArquivo))
                .toEither()
                .bimap(Notification::create, UpdateArquivoOutput::from);
    }
}
