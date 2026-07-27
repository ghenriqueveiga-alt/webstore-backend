package com.hvs.webstore.back.app.usecase.webstore.estoque;

import com.hvs.webstore.back.app.command.webstore.estoque.MovimentarEstoqueCommand;
import com.hvs.webstore.back.app.output.webstore.estoque.MovimentarEstoqueOutput;
import com.hvs.webstore.back.domain.entity.webstore.estoque.Estoque;
import com.hvs.webstore.back.domain.entity.webstore.estoque.EstoqueDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.estoque.EstoqueUuid;
import com.hvs.webstore.back.domain.entity.webstore.estoque.MovimentoEstoque;
import com.hvs.webstore.back.domain.entity.webstore.estoque.TipoMovimento;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class MovimentarEstoqueUseCaseImpl extends MovimentarEstoqueUseCase {

    private final EstoqueDomainGateway gateway;

    public MovimentarEstoqueUseCaseImpl(EstoqueDomainGateway gateway) {

        this.gateway = gateway;
    }

    @Override
    public Either<Notification, MovimentarEstoqueOutput> execute(MovimentarEstoqueCommand aEstoqueCommand) {

        Optional<Estoque> estoqueDb = gateway.readByUuid(EstoqueUuid.from(aEstoqueCommand.aEstoqueUuid()));

        if (estoqueDb.isPresent()) {

            var estoque = estoqueDb.get();
            var tipo = TipoMovimento.findByCode(aEstoqueCommand.aTipo());

            if (tipo == null)
                return Either.left(Notification.create(new Error("The TipoMovimento with code: " + aEstoqueCommand.aTipo() + " is invalid.")));

            var notification = Notification.create();

            Integer novaQuantidade;
            switch (tipo) {
                case ENTRADA:
                    novaQuantidade = estoque.getQuantidade() + aEstoqueCommand.aQuantidade();
                    break;
                case SAIDA:
                    novaQuantidade = estoque.getQuantidade() - aEstoqueCommand.aQuantidade();
                    if (novaQuantidade < 0) {
                        return Either.left(Notification.create(new Error("Insufficient stock for saida. Current: " + estoque.getQuantidade())));
                    }
                    break;
                case AJUSTE:
                    novaQuantidade = aEstoqueCommand.aQuantidade();
                    break;
                default:
                    return Either.left(Notification.create(new Error("Unknown TipoMovimento: " + aEstoqueCommand.aTipo())));
            }

            var estoqueAtualizado = Estoque.update(estoque.getId().getValue(),
                                                   estoque.getUuid().getValue(),
                                                   estoque.getStatusCode().getCode(),
                                                   estoque.getProduto().getId().getValue(),
                                                   novaQuantidade,
                                                   estoque.getReservado(),
                                                   estoque.getQuantidadeMinima());

            var movimento = MovimentoEstoque.create(estoqueAtualizado.getId().getValue(),
                                                    aEstoqueCommand.aTipo(),
                                                    aEstoqueCommand.aQuantidade(),
                                                    aEstoqueCommand.aObservacao());

            return notification.hasError() ? API.Left(notification) : movimentar(estoqueAtualizado, movimento);
        } else {

            return Either.left(Notification.create(new Error("The Estoque with uuid: " + aEstoqueCommand.aEstoqueUuid() + " could not be found.")));
        }
    }

    @Transactional
    private Either<Notification, MovimentarEstoqueOutput> movimentar(Estoque aEstoque, MovimentoEstoque aMovimento) {

        return Try(() -> {
            gateway.update(aEstoque);
            return gateway.createMovimento(aMovimento);
        }).toEither().bimap(
                Notification::create, MovimentarEstoqueOutput::from);
    }
}
