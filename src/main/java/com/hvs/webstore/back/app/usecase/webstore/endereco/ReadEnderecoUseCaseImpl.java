package com.hvs.webstore.back.app.usecase.webstore.endereco;

import com.hvs.webstore.back.app.command.webstore.endereco.ReadEnderecoCommand;
import com.hvs.webstore.back.app.output.webstore.endereco.ReadEnderecoOutput;
import com.hvs.webstore.back.domain.entity.webstore.endereco.Endereco;
import com.hvs.webstore.back.domain.entity.webstore.endereco.EnderecoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.endereco.EnderecoId;
import com.hvs.webstore.back.domain.entity.webstore.endereco.EnderecoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadEnderecoUseCaseImpl extends ReadEnderecoUseCase {

    private final EnderecoDomainGateway gateway;

    public ReadEnderecoUseCaseImpl(EnderecoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadEnderecoOutput> execute(ReadEnderecoCommand aEnderecoCommand) {

        Optional<Endereco> aEnderecoDB = aEnderecoCommand.aId() != null ?
                gateway.read(EnderecoId.from(aEnderecoCommand.aId())) : gateway.readByUuid(EnderecoUuid.from(aEnderecoCommand.aUuid()));

        if (aEnderecoDB.isPresent())
            return Try(aEnderecoDB::get)
                    .toEither().bimap(Notification::create, ReadEnderecoOutput::from);

        var aEnderecoId = aEnderecoCommand.aId() != null ? String.valueOf(aEnderecoCommand.aId()) : aEnderecoCommand.aUuid();

        return Either.left(Notification.create(new Error("Endereco not found: " + aEnderecoId)));
    }
}
