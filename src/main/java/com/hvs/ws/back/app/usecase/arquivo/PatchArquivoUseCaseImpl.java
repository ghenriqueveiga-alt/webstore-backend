package com.hvs.ws.back.app.usecase.arquivo;

import com.hvs.ws.back.app.command.arquivo.PatchArquivoCommand;
import com.hvs.ws.back.app.output.arquivo.PatchArquivoOutput;
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

public class PatchArquivoUseCaseImpl extends PatchArquivoUseCase {

    private final ArquivoDomainGateway gateway;

    public PatchArquivoUseCaseImpl(
            ArquivoDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchArquivoOutput> execute(PatchArquivoCommand aIn) {

        Optional<Arquivo> arquivoDb;

        if (aIn.aId() != null) {

            arquivoDb = this.gateway.read(ArquivoId.from(aIn.aId()));
        } else {

            arquivoDb = this.gateway.readByUuid(ArquivoUuid.from(aIn.aUuid()));
        }

        if (arquivoDb.isPresent()) {

            final var notification = Notification.create();
            final var arquivo = Arquivo.patch(aIn.aStatusCode(),
                                              aIn.aNome(),
                                              aIn.aTipoCode(),
                                              aIn.aTamanho(),
                                              aIn.aCaminho() != null
                                                      ? aIn.aCaminho() : null,
                                              arquivoDb.get());
            arquivo.validate(notification);

            return notification.hasError() ? Left(notification) : patch(arquivo);
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
    private Either<Notification, PatchArquivoOutput> patch(final Arquivo aArquivo){

        return Try(() -> this.gateway.patch(aArquivo))
                .toEither()
                .bimap(Notification::create, PatchArquivoOutput::from);
    }
}
