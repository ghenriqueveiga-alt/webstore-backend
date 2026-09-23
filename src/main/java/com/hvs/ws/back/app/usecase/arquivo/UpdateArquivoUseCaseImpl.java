package com.hvs.ws.back.app.usecase.arquivo;

import com.hvs.ws.back.app.command.arquivo.UpdateArquivoCommand;
import com.hvs.ws.back.app.output.arquivo.UpdateArquivoOutput;
import com.hvs.ws.back.domain.entity.arquivo.Arquivo;
import com.hvs.ws.back.domain.entity.arquivo.ArquivoDomainGateway;
import com.hvs.ws.back.domain.entity.arquivo.ArquivoId;
import com.hvs.ws.back.domain.entity.arquivo.ArquivoUuid;
import com.hvs.ws.back.domain.validation.notification.Notification;
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
                                               aIn.aStatusCode(),
                                               aIn.aNome(),
                                               aIn.aTipoCode(),
                                               aIn.aTamanho(),
                                               aIn.aCaminho());
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
