package com.hvs.webstore.back.app.usecase.webstore.endereco;

import com.hvs.webstore.back.app.command.webstore.endereco.DeleteEnderecoCommand;
import com.hvs.webstore.back.app.output.webstore.endereco.DeleteEnderecoOutput;
import com.hvs.webstore.back.domain.entity.webstore.endereco.Endereco;
import com.hvs.webstore.back.domain.entity.webstore.endereco.EnderecoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.endereco.EnderecoId;
import com.hvs.webstore.back.domain.entity.webstore.endereco.EnderecoUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteEnderecoUseCaseImpl extends DeleteEnderecoUseCase {

    private final EnderecoDomainGateway gateway;

    public DeleteEnderecoUseCaseImpl(EnderecoDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteEnderecoOutput> execute(DeleteEnderecoCommand aEnderecoCommand) {

        Optional<Endereco> aEnderecoDB = aEnderecoCommand.aId() != null ?
                gateway.read(EnderecoId.from(aEnderecoCommand.aId())) : gateway.readByUuid(EnderecoUuid.from(aEnderecoCommand.aUuid()));

        if (aEnderecoDB.isEmpty())
            return Either.left(Notification.create(new Error("Endereco not found: " + (aEnderecoCommand.aId() != null ?
                    aEnderecoCommand.aId() : aEnderecoCommand.aUuid()))));

        return delete(aEnderecoDB.get());
    }

    @Transactional
    private Either<Notification, DeleteEnderecoOutput> delete(Endereco aEndereco) {

        return Try(() -> {
            gateway.delete(aEndereco);
            return DeleteEnderecoOutput.from(aEndereco);
        }).toEither().bimap(Notification::create, r -> r);
    }
}
