package com.hvs.ws.back.app.usecase.arquivo;

import com.hvs.ws.back.app.command.arquivo.ReadArquivoCommand;
import com.hvs.ws.back.app.output.arquivo.ReadArquivoOutput;
import com.hvs.ws.back.domain.entity.arquivo.Arquivo;
import com.hvs.ws.back.domain.entity.arquivo.ArquivoDomainGateway;
import com.hvs.ws.back.domain.entity.arquivo.ArquivoId;
import com.hvs.ws.back.domain.entity.arquivo.ArquivoUuid;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadArquivoUseCaseImpl extends ReadArquivoUseCase {

    private final ArquivoDomainGateway gateway;

    public ReadArquivoUseCaseImpl(
            ArquivoDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadArquivoOutput> execute(ReadArquivoCommand aIn) {

        Optional<Arquivo> arquivoDb;

        if (aIn.aId() != null) {

            arquivoDb = this.gateway.read(ArquivoId.from(aIn.aId()));
        } else {

            arquivoDb = this.gateway.readByUuid(ArquivoUuid.from(aIn.aUuid()));
        }

        if (arquivoDb.isPresent()) {

            return Try(arquivoDb::get).toEither().bimap(Notification::create, ReadArquivoOutput::from);
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
}
